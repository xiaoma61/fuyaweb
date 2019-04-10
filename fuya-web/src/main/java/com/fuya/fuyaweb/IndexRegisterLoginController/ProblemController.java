package com.fuya.fuyaweb.IndexRegisterLoginController;

import com.fuya.Redis.Util.RedisUtil;
import com.fuya.fuyadao.entity.CHOOSE;
import com.fuya.fuyadao.entity.ExamChooseCheck;
import com.fuya.fuyadao.entity.PROBLEM;
import com.fuya.fuyadao.model.AdminProblemAnswer;
import com.fuya.fuyaservice.CHOOSEService;
import com.fuya.fuyaservice.PROBLEMService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProblemController {
    @Autowired
    PROBLEMService problemService;
    @Autowired
    CHOOSEService chooseService;
    @Autowired
    RedisUtil redisUtil;
    //拿到考题
    @RequestMapping("/exam")
    @ResponseBody
    public JSONObject exam(@RequestParam(name = "type")String type){

        //选择20道
        List<PROBLEM>problemList=problemService.findByNum(20);

        List<AdminProblemAnswer>adminProblemAnswerList=new ArrayList<>();
        for (PROBLEM problem:problemList){

            List<Object>objectList=chooseService.findObjectByCHOOSEID(problem.getCHOOSEID());
            for (int i=0;i<objectList.size();i++){
                Object[]objects=(Object[])objectList.get(i);
                CHOOSE choose=new CHOOSE();
                choose.setACHOOSE(objects[0].toString());
                choose.setBCHOOSE(objects[1].toString());
                choose.setCCHOOSE(objects[2].toString());
                choose.setDCHOOSE(objects[3].toString());
                AdminProblemAnswer adminProblemAnswer=new AdminProblemAnswer(problem,choose);
                adminProblemAnswerList.add(adminProblemAnswer);
            }

        }
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",adminProblemAnswerList);
        return JSONObject.fromObject(msg);

    }
//    //对答案返回分数
//@RequestBody String jsonParam
    @RequestMapping("/exam/check")
    @ResponseBody
    public JSONObject examcheck(@RequestBody String jsonParam){

        int sum=0;

//        List<ExamChooseCheck>examChooseChecks=new ArrayList<>();
//        //测试数据
//        for (int i=0;i<10;i++){
//            ExamChooseCheck examChooseCheck=new ExamChooseCheck();
//            examChooseCheck.setAnswer(i+"");
//            examChooseCheck.setChooseid(i);
//            examChooseChecks.add(examChooseCheck);
//
//        }
//        Map<String,Object> msg=new HashMap<>();
//        msg.put("msg",examChooseChecks);

        JSONObject jsonObject=JSONObject.fromObject(jsonParam);
        String json=jsonObject.getString("msg");
        System.out.println(json);
        JSONArray msgjson= JSONArray.fromObject(json);
        String answerS="answer";
        for (Object object:msgjson){
            ExamChooseCheck examChooseCheck= (ExamChooseCheck) JSONObject.toBean(JSONObject.fromObject(object),ExamChooseCheck.class);
            //从redis中提取

            if (redisUtil.hasKey(answerS+examChooseCheck.getChooseid())){
                String answer=redisUtil.get(answerS+examChooseCheck.getChooseid());
                if (answer.equals(examChooseCheck.getAnswer())){
                    sum+=2;
                }
            }else {
                List<Object>chooseList=chooseService.findAnswer();
                for (int i=0;i<chooseList.size();i++){
                    Object[]objects=(Object[])chooseList.get(i);
                    redisUtil.set(answerS+objects[1].toString(),objects[0].toString());

                }
                if (redisUtil.hasKey(answerS+examChooseCheck.getChooseid())){
                    String answer=redisUtil.get(answerS+examChooseCheck.getChooseid());
                    if (answer.equals(examChooseCheck.getAnswer())){
                        sum+=2;
                    }
                }

            }

//            JSONObject chooseObject=JSONObject.fromObject(chooseList);
//            redisUtil.set("answer",chooseObject.toString());


            System.out.println(examChooseCheck.getAnswer());

        }
        System.out.println(json);
//        String json=jsonObject.toString();
//        System.out.println(json);
//        JSONObject jsonObject1=JSONObject.fromObject(json);
//        String answerjson=jsonObject1.toString();
//        System.out.println(answerjson);
//        //chooseid ,answer
//        //选择20道
//        JSONObject jsons=JSONObject.fromObject(json);

//        ExamChooseCheck examChooseCheck= (ExamChooseCheck) JSONObject.toBean(jsonObject,ExamChooseCheck.class);

        //id 和答案

        Map<String,Object> msg1=new HashMap<>();
        msg1.put("msg",sum);
        return JSONObject.fromObject(msg1);

    }
}
