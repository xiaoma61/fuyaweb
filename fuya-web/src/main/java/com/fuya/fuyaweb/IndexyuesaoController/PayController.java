package com.fuya.fuyaweb.IndexyuesaoController;

import com.fuya.fuyadao.entity.EMPLOYERINFORMATION;
import com.fuya.fuyadao.entity.ORDERS;
import com.fuya.fuyadao.entity.SERVICECONTENT;
import com.fuya.fuyadao.entity.YUESAOOTHERPROVE;
import com.fuya.fuyaservice.EMPLOYERINFORMATIONService;
import com.fuya.fuyaservice.ORDERSService;
import com.fuya.fuyaservice.SERVICECONTENTService;
import com.fuya.fuyautil.TimeUtil;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.SimpleFormatter;

@Controller
public class PayController {


    @Autowired
    ORDERSService ordersService;
    @Autowired
    SERVICECONTENTService servicecontentService;
    @Autowired
    EMPLOYERINFORMATIONService employerinformationService;




    //订单提交
    @RequiresRoles("users")
    @RequestMapping("/fuyayusao/order/add")
    @ResponseBody
    public JSONObject addorder(HttpServletRequest request,
                               @RequestParam(name = "toid")int toid,
                               @RequestParam(name = "childbirth",defaultValue = "2019-11-10")String childbirth, @RequestParam(name = "starttime",defaultValue = "2020-10-10")String starttime,
                               @RequestParam(name = "fate",defaultValue = "10")int fate,
                               @RequestParam(name = "others",defaultValue = "学习")String others, @RequestParam(name = "handsel",defaultValue = "100000")String handsel,
                               @RequestParam(name = "handsel",defaultValue = "2")int handselstatus, @RequestParam(name = "sum",defaultValue = "100000")String sum,
                               @RequestParam(name = "sumstatus",defaultValue = "1")int sumstatus,
                               @RequestParam(name = "name",defaultValue = "xiaoma")String name,@RequestParam(name = "area",defaultValue = "地带你")String area,
                               @RequestParam(name = "address",defaultValue = "第哦啊")String address,
                               @RequestParam(name = "phone",defaultValue = "第哦啊")String phone,@RequestParam(name = "type",defaultValue = "1")int type,
                               @RequestParam(name = "idcard",defaultValue = "4405101999")String idcard

                               ) throws ParseException {
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");


        //生成订单号码
        Date date=new Date();
        ORDERS orders=new ORDERS();
        orders.setFROMID(id);
        orders.setTOID(toid);

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        String dateS=simpleDateFormat.format(new Date());
        orders.setCONTRACTNUMBER(dateS);
        ordersService.save(orders);


        EMPLOYERINFORMATION employerinformation=new EMPLOYERINFORMATION();
        employerinformation.setTYPE(type);
        employerinformation.setPHONE(phone);
        employerinformation.setNAME(name);
        employerinformation.setAREA(area);
        employerinformation.setADDRESS(address);
        employerinformation.setIDCARD(idcard);
        employerinformation.setODERID(orders.getORDERSID());
        employerinformationService.save(employerinformation);





        SERVICECONTENT servicecontent=new SERVICECONTENT();
        java.sql.Date cbirth=TimeUtil.stringtodate(childbirth);
        servicecontent.setCHILDBIRTH(cbirth);
        servicecontent.setFATE(fate);
        servicecontent.setHANDSEL(handsel);
        servicecontent.setHANDSELSTATUS(handselstatus);
        servicecontent.setORDERID(orders.getORDERSID());
        servicecontent.setOTHERS(others);
        java.sql.Date sbirth=TimeUtil.stringtodate(starttime);
        servicecontent.setSTARTTIME(sbirth);
        servicecontent.setSTATUS(1);
        servicecontent.setSUM(sum);
        servicecontent.setSUMSTATUS(sumstatus);
        servicecontentService.save(servicecontent);

        //添加
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);
    }



}
