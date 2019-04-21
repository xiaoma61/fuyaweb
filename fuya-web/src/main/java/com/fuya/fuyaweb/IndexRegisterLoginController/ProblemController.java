package com.fuya.fuyaweb.IndexRegisterLoginController;

import com.fuya.Redis.Util.RedisUtil;
import com.fuya.fuyadao.model.ExamChooseCheck;
import com.fuya.fuyadao.model.PROBLEMmodel;
import com.fuya.fuyaservice.CHOOSEService;
import com.fuya.fuyaservice.PROBLEMService;
import com.fuya.fuyautil.EntityUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class ProblemController {
    @Autowired
    PROBLEMService problemService;
    @Autowired
    CHOOSEService chooseService;
    @Autowired
    RedisUtil redisUtil;
    private static  final String KEY="examMsgkey";
    //拿到考题
    @RequestMapping("/Register/problem")
    @ResponseBody
    public JSONObject exam(@RequestParam(name = "type")String type, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        //选择20道
        List<Object[]>problemList=problemService.findByNum(20);
        List<PROBLEMmodel>probleMmodelList=EntityUtils.castEntity(problemList ,PROBLEMmodel.class,new PROBLEMmodel());
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",probleMmodelList);
        return JSONObject.fromObject(msg);

    }
//    //对答案返回分数
//@RequestBody String jsonParam
    @RequestMapping("/Register/check")
    @ResponseBody
    public JSONObject examcheck(@RequestBody String jsonParam,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        int sum=0;
        JSONArray msgjson = JSONArray.fromObject(jsonParam);

        String answerS="answer";
        for (int i = 0; i < msgjson.size(); i++){
            JSONObject jsonObject = msgjson.getJSONObject(i);
            String chooseid= answerS+ jsonObject.get("chooseid");
            String answer= (String) jsonObject.get("answer");
            //从redis中提取
            if (redisUtil.hexists(KEY,chooseid)){
                if (answer.equals(redisUtil.hGet(KEY,chooseid))){
                    sum+=2;
                }
            }else {
                List<Object[]>chooseList=chooseService.findAnswer();
                for (Object[] o:chooseList){
                    ExamChooseCheck ec=new ExamChooseCheck();
                    ec.setANSWER((String) o[0]);
                    ec.setCHOOSEID((Integer) o[1]);
                    redisUtil.hset(KEY, answerS+String.valueOf(ec.getCHOOSEID()),ec.getANSWER());
                    if (redisUtil.hexists(KEY,chooseid)){
                        if (answer.equals(redisUtil.hGet(KEY,chooseid))){
                            sum+=2;
                        }
                    }
                }

                /*List<ExamChooseCheck>examChooseCheckList=EntityUtils.castEntity( chooseList,ExamChooseCheck.class,new ExamChooseCheck());
                for (ExamChooseCheck ec:examChooseCheckList){
                    redisUtil.hset(KEY, answerS+String.valueOf(ec.getCHOOSEID()),ec.getANSWER());
                }*/


            }


        }


        Map<String,Object> msg1=new HashMap<>();
        msg1.put("msg",sum);
        return JSONObject.fromObject(msg1);

    }
}
