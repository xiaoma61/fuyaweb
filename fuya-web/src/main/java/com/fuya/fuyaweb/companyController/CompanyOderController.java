package com.fuya.fuyaweb.companyController;

import com.fuya.fuyadao.entity.COMPANYYUESAO;
import com.fuya.fuyadao.entity.YUESOBASICINFO;
import com.fuya.fuyadao.model.PROVEINFOANDBAISINFO;
import com.fuya.fuyadao.model.YUESAOORDERSERIVECONTENT;
import com.fuya.fuyaservice.*;
import com.fuya.fuyautil.TypeUtil;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class CompanyOderController {
    @Autowired
    YUESOBASICINFOService yuesobasicinfoService;
    @Autowired
    PROVEINFOService proveinfoService;
    @Autowired
    SKILLService skillService;
    @Autowired
    YUESAOOTHERPROVEService yuesaootherproveService;
    @Autowired
    COMPANYYUESAOService companyyuesaoService;
    //个人信息
    @RequiresRoles("company")
    @RequestMapping("/companys/orders")
    @ResponseBody
    public JSONObject orderslist(HttpServletRequest request){

        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");
        List<COMPANYYUESAO> companyyuesaoList=companyyuesaoService.findByRealCOMPANYID(id);
        List<PROVEINFOANDBAISINFO>objectLists=new ArrayList<>();
        for (COMPANYYUESAO companyyuesao : companyyuesaoList){

            List<PROVEINFOANDBAISINFO>objectList=proveinfoService.findPROVEINFOByAndYUESAOBASICINFOByUSERSID(companyyuesao.getYUESAOID());
            YUESOBASICINFO yuesobasicinfo=yuesobasicinfoService.findByUSERSID(companyyuesao.getYUESAOID());
            YUESAOORDERSERIVECONTENT yuesaoorderserivecontent=new YUESAOORDERSERIVECONTENT();
            yuesaoorderserivecontent.setName(yuesobasicinfo.getNAME());
            yuesaoorderserivecontent.setObjectList(objectList);
            yuesaoorderserivecontent.setType(String.valueOf(TypeUtil.getTypeUtil(yuesobasicinfo.getTYPE())));
            yuesaoorderserivecontent.setUserid(yuesobasicinfo.getUSERSID());

            objectLists.addAll(objectList);

        }
        //查找
//        List<PROVEINFOANDBAISINFO> objectList=proveinfoService.findPROVEINFOByAndYUESAOBASICINFOByUSERSID(id);
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",objectLists);
        return JSONObject.fromObject(msg);
    }
    //开始时间
    //员工名称


}
