package com.fuya.fuyaweb.companyController;

import com.fuya.ActiveMQ.service.ProductService;
import com.fuya.fuyadao.entity.RECRUIT;
import com.fuya.fuyadao.model.RECRUITmodel;
import com.fuya.fuyaservice.RECRUITService;
import com.fuya.fuyasolr.SearchResult.SearchResult;
import com.fuya.fuyasolr.Solr.service.RECRUITSolrService;
import com.fuya.fuyautil.TimeUtil;
import com.fuya.fuyautil.TotalPages;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Queue;
import javax.jms.Topic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CompanyrecruitController {
    @Autowired
    RECRUITService recruitService;
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;
    @Autowired
    private ProductService productService;

    @Autowired
    private RECRUITSolrService recruitSolrService;
    //添加招聘信息
    @RequiresRoles("company")
    @RequestMapping("/companys/recruit/add")
    @ResponseBody
    public JSONObject add(@RequestParam(name = "nums",defaultValue = "1")int nums, @RequestParam(name = "highlight",defaultValue = "无")String hightlight,
                          @RequestParam(name = "linkman",defaultValue ="马小姐" )String linkman,
                          @RequestParam(name="phone",defaultValue = "0000")String phone, @RequestParam(name = "describe",defaultValue = "无")String describe,
                          @RequestParam(name = "workbackgound",defaultValue = "很好的公司")String workbackgound,
                          @RequestParam(name = "starttime",defaultValue = "2018-01-09")String starttime,
                          @RequestParam(name = "endtime",defaultValue = "2018-01-12")String endtime, @RequestParam(name = "position",defaultValue = "程序员")String position,
                          @RequestParam(name = "salary",defaultValue = "工资")String salary, @RequestParam(name = "education",defaultValue = "教育水平")String education,
                          @RequestParam(name = "workarea",defaultValue = "北京")String workarea, HttpServletRequest request) throws ParseException {
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");

        RECRUIT recruit=new RECRUIT();
        recruit.setUSERSID(id);
        recruit.setNUMS(String.valueOf(nums));
        recruit.setHIGHLIGHT(hightlight);
        recruit.setLINKMAN(linkman);
        recruit.setPHONE(phone);
        recruit.setDESCRIBE(describe);
        recruit.setWORKBACKGROUND(workbackgound);
        recruit.setTIME(TimeUtil.getsqldate(new Date()));
        recruit.setSTARTTIME(TimeUtil.stringtodate(starttime));
        recruit.setENDTIME(TimeUtil.stringtodate(endtime));
        recruit.setPOSITION(position);
        recruit.setSALARY(salary);
        recruit.setEDUCATION(education);
        recruit.setWORKAREA(workarea);
        recruitService.save(recruit);
        productService.sendMessage(this.topic,"recruit-add:"+recruit.getRECRUITID());

        Map<String,String> msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);
    }
    //招聘信息列表
    @RequiresRoles("company")
    @RequestMapping("/companys/recruits")
    @ResponseBody
    public JSONObject recruitlist(@RequestParam(name = "start",defaultValue = "0")int start,@RequestParam(name = "rows",defaultValue = "10")int rows
            , HttpServletRequest request) throws IOException, SolrServerException {
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        SearchResult searchResult= recruitSolrService.SearchRECRUITByUserid(id,start,rows);
        searchResult.setTotalPage(TotalPages.GetIMage(searchResult.getObjects().size(),rows));
        return JSONObject.fromObject(searchResult);


    }
    //显示详细信息
    @RequiresRoles("company")
    @RequestMapping("/companys/recruit/id")
    @ResponseBody
    public JSONObject recruitlist(@RequestParam(name = "id")int id) throws IOException, SolrServerException {
        SearchResult searchResult= recruitSolrService.SearchRECRUITByid(id);
        RECRUITmodel recruiTmodel= (RECRUITmodel) searchResult.getObjects().get(0);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",recruiTmodel);
        return JSONObject.fromObject(msg);
    }
    //删除
    @RequiresRoles("company")
    @RequestMapping("/companys/recruit/delete")
    @ResponseBody
    public JSONObject recruitdelete(@RequestParam(name = "id")int id) throws IOException, SolrServerException {

        recruitSolrService.DeleteByRECRUITID(id);
        //数据库删除
        recruitService.delete(id);
        Map<String,String> msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);
    }


    //修改
    @RequiresRoles("company")
    @RequestMapping("/companys/recruit/update")
    @ResponseBody
    public JSONObject recruitupdate(@RequestParam(name = "nums",defaultValue = "1")String nums, @RequestParam(name = "highlight",defaultValue = "无")String hightlight,
                                    @RequestParam(name = "linkman",defaultValue ="马小姐" )String linkman,
                                    @RequestParam(name="phone",defaultValue = "0000")String phone, @RequestParam(name = "describe",defaultValue = "无")String describe,
                                    @RequestParam(name = "workbackgound",defaultValue = "很对对对司")String workbackgound,
                                    @RequestParam(name = "starttime",defaultValue = "2018-01-09")String starttime,
                                    @RequestParam(name = "endtime",defaultValue = "2018-01-12")String endtime, @RequestParam(name = "position",defaultValue = "程序员")String position,
                                    @RequestParam(name = "salary",defaultValue = "工资")String salary, @RequestParam(name = "education",defaultValue = "教育水平")String education,
                                    @RequestParam(name = "workarea",defaultValue = "北京")String workarea,
                                    @RequestParam(name = "id")int id,
                                    HttpServletRequest request) throws IOException, SolrServerException, ParseException {



        //数据库删除
        recruitService.updateRECRUITbyid(nums,describe,education,TimeUtil.stringtodate(endtime),workbackgound,hightlight,linkman,phone,position,salary,TimeUtil.stringtodate(starttime),
                TimeUtil.getsqldate(new Date()),workarea,id);
        recruitSolrService.UpdateByRECRUITID(id);
        Map<String,String> msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);
    }
    //搜索发布时间
    //搜索公司
    //搜索职位


}
