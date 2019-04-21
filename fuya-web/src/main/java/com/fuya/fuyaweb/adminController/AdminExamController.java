package com.fuya.fuyaweb.adminController;

import com.fuya.Configuration.DateJsonValueProcessor;
import com.fuya.fuyadao.entity.ARTICLE;
import com.fuya.fuyadao.entity.CHOOSE;
import com.fuya.fuyadao.entity.PROBLEM;
import com.fuya.fuyadao.entity.SERVICECONTENT;
import com.fuya.fuyadao.model.AdminProblemAnswer;
import com.fuya.fuyaservice.CHOOSEService;
import com.fuya.fuyaservice.PROBLEMService;
import com.fuya.fuyautil.JpaPageHelperUtil;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class AdminExamController {
    @Autowired
    CHOOSEService chooseService;
    @Autowired
    PROBLEMService problemService;
    //添加
    @RequiresRoles("admin")
    @RequestMapping("/admin/problem/add")
    @ResponseBody
    public JSONObject problemadd(@RequestBody String text
            /*@RequestParam(name = "title",defaultValue = "title")String title,@RequestParam(name = "choosetype",defaultValue = "多选")String choosetype,@RequestParam(name = "subjectmater",defaultValue = "语音")String subjectmater,
                                 @RequestParam(name = "choosea",defaultValue = "a")
                                         String choosea,@RequestParam(name = "chooseb",defaultValue = "b")String chooseb,@RequestParam(name = "choosec",defaultValue = "c")String choosec,
                                 @RequestParam(name = "choosed",defaultValue = "d")String choosed,@RequestParam(name = "ansewer",defaultValue = "1")String answer*/
            ){

        JSONObject jsonObject = JSONObject.fromObject(text);
        JSONObject chooseObject = jsonObject.getJSONObject("CHOOSE");
        CHOOSE choose= (CHOOSE) JSONObject.toBean(chooseObject ,CHOOSE.class);
        JSONObject  PROBLEMObject = jsonObject.getJSONObject("PROBLEM");
        PROBLEM problem= (PROBLEM ) JSONObject.toBean( PROBLEMObject,PROBLEM.class);
        chooseService.save(choose);
        problem.setCHOOSEID(choose.getCHOOSEID());
        problem.setTIME(new Date());
        problem.setSUBJECTMATTER("1");
        problemService.save(problem);

  /*  *//* *//**//*   CHOOSE choose=new CHOOSE();
        choose.setACHOOSE(choosea);
        choose.setBCHOOSE(chooseb);
        choose.setCCHOOSE(choosec);
        choose.setDCHOOSE(choosed);
        choose.setANSWER(answer);*//**//*
        chooseService.save(choose);*//*

        PROBLEM problem= (PROBLEM ) JSONObject.toBean(object,PROBLEM.class);
     *//*   PROBLEM problem=new PROBLEM();*//*
     *//*   problem.setCHOOSETYPE(choosetype);
        problem.setSUBJECTMATTER(subjectmater);
        problem.setTITLE(title);*//*
        problem.setTIME(new Date());
        problem.setCHOOSEID(choose.getCHOOSEID());
        problemService.save(problem);*/
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);

    }
    /*@RequiresRoles("admin")
    @RequestMapping("/admin/choose/add")
    @ResponseBody
    public JSONObject problemadd(@RequestBody CHOOSE choose){



     *//*   CHOOSE choose=new CHOOSE();
        choose.setACHOOSE(choosea);
        choose.setBCHOOSE(chooseb);
        choose.setCCHOOSE(choosec);
        choose.setDCHOOSE(choosed);
        choose.setANSWER(answer);*//*
        chooseService.save(choose);
*//*
        PROBLEM problem= (PROBLEM ) JSONObject.toBean(object,PROBLEM.class);
        *//**//*   PROBLEM problem=new PROBLEM();*//**//*
     *//**//*   problem.setCHOOSETYPE(choosetype);
        problem.setSUBJECTMATTER(subjectmater);
        problem.setTITLE(title);*//**//*
        problem.setTIME(new Date());
        problem.setCHOOSEID(choose.getCHOOSEID());
        problemService.save(problem);*//*
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);

    }*/
    //列表
    @RequiresRoles("admin")
    @RequestMapping("/admin/problemlist")
    @ResponseBody
    public JSONObject problem(@RequestParam(name = "start",defaultValue = "0")int start,@RequestParam(name = "rows",defaultValue = "10")int rows){
//        PageHelper.startPage(start,rows);
//        PageInfo<PROBLEM>problemAnswerPageInfo=problemService.findALL(start,rows);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        Page<PROBLEM> problemList=problemService.findALL(start,rows);
//        PageInfo<PROBLEM>problemAnswerPageInfo=new PageInfo<>(problemList);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg", problemList);
        return JSONObject.fromObject(msg,jsonConfig);

    }
//    //时间查询
//    @RequiresRoles("admin")
//    @RequestMapping("/admin/problem/time")
//    @ResponseBody
//    public JSONObject problemtime(){
//
//    }
//    @RequiresRoles("admin")
//    @RequestMapping("/admin/problem/keywordtime")
//    @ResponseBody
//    public JSONObject problemkeywordtime(){
//
//    }

//    //题目查询
    @RequiresRoles("admin")
    @RequestMapping("/admin/problem/title")
    @ResponseBody
    public JSONObject probletitle(@RequestParam(name = "start",defaultValue = "0")int start,@RequestParam(name = "rows",defaultValue = "10")int rows,@RequestParam(name = "title")String title){
        List<PROBLEM> problemList=problemService.findByTITLELike(title);
        PageInfo problemAnswerPageInfo= JpaPageHelperUtil.SetStartPage(problemList,start+1,rows);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",problemAnswerPageInfo);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        return JSONObject.fromObject(msg,jsonConfig);

    }
    //    //查看
    @RequiresRoles("admin")
    @RequestMapping("/admin/problem/id")
    @ResponseBody
    public JSONObject problemid(@RequestParam(name = "id")int id){
        AdminProblemAnswer adminProblemAnswerList=chooseService.findByCHOOSEID(id);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",adminProblemAnswerList);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        return JSONObject.fromObject(msg,jsonConfig);
    }
    //    //删除
    @RequiresRoles("admin")
    @RequestMapping("/admin/problem/delete")
    @ResponseBody
    public JSONObject problemdelete(@RequestParam(name = "problemid")int problemid,@RequestParam(name = "chooseid")int chooseid){

        problemService.deleteAllByPROMBLEIDandCHOOSEID(chooseid,problemid);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","sussess");
        return JSONObject.fromObject(msg);

    }


//
//    //修改
    @RequiresRoles("admin")
    @RequestMapping("/admin/problem/update")
    @ResponseBody
    public JSONObject problemupdate(@RequestParam(name = "title",defaultValue = "title11")String title,@RequestParam(name = "choosetype",defaultValue = "多选")String choosetype,@RequestParam(name = "subjectmater",defaultValue = "语音")String subjectmater,
                                    @RequestParam(name = "choosea",defaultValue = "a")
                                            String choosea,@RequestParam(name = "chooseb",defaultValue = "b")String chooseb,@RequestParam(name = "choosec",defaultValue = "c")String choosec,
                                    @RequestParam(name = "choosed",defaultValue = "f")String choosed,@RequestParam(name = "ansewer",defaultValue = "1")String answer,@RequestParam(name = "problemid")int problemid,@RequestParam(name = "chooseid")int chooseid){

        problemService.update(title,choosetype,subjectmater,problemid);
        chooseService.update(choosea,chooseb,choosec,choosed,answer,chooseid);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","sussess");
        return JSONObject.fromObject(msg);

    }





}
