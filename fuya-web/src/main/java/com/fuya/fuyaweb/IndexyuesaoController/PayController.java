package com.fuya.fuyaweb.IndexyuesaoController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.fuya.fuyadao.entity.EMPLOYERINFORMATION;
import com.fuya.fuyadao.entity.ORDERS;
import com.fuya.fuyadao.entity.SERVICECONTENT;
import com.fuya.fuyaservice.EMPLOYERINFORMATIONService;
import com.fuya.fuyaservice.ORDERSService;
import com.fuya.fuyaservice.SERVICECONTENTService;
import com.fuya.fuyautil.AlipayConfig;
import com.fuya.fuyautil.TimeUtil;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

                               ) throws ParseException, AlipayApiException {
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
        servicecontent.setSTATUS(2);
        servicecontent.setSUM(sum);
        servicecontent.setSUMSTATUS(sumstatus);
        servicecontentService.save(servicecontent);


        AlipayClient alipayClient=new DefaultAlipayClient(AlipayConfig.gatewayUrl,AlipayConfig.app_id,AlipayConfig.merchant_private_key,"json",AlipayConfig.charset,AlipayConfig.alipay_public_key,AlipayConfig.sign_type);
        AlipayTradeAppPayRequest alipayTradeAppPayRequest=new AlipayTradeAppPayRequest();
        alipayTradeAppPayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayTradeAppPayRequest.setNotifyUrl(AlipayConfig.notify_url);
        String out_trade_no = String.valueOf(orders.getORDERSID());
        String total_amount= String.valueOf(fate);
        String subject="月嫂:"+orders.getORDERSID();
        String timeout_express = "1c";
        alipayTradeAppPayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");


        String result = alipayClient.pageExecute(alipayTradeAppPayRequest).getBody();



        //添加
        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",result);
        return JSONObject.fromObject(msg);
    }

    @RequiresRoles("users")
    @RequestMapping("/fuyayusao/order/refund")
    @ResponseBody
    public JSON refund(HttpServletResponse response, HttpSession session, @RequestParam(name = "orderid")int orderid, @RequestParam(name = "fate")int fate) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
        aliPayRequest.setReturnUrl(AlipayConfig.return_url);
        aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);
        String total_amount= String.valueOf(fate);
        String out_trade_no = String.valueOf(orderid);
        String subject="月嫂:"+orderid;
        aliPayRequest.setBizContent("{\"out_trade_no\":\"" + orderid + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result = alipayClient.pageExecute(aliPayRequest).getBody();



        Map<String,Object> msg=new HashMap<>();
        msg.put("msg",result);
        return JSONObject.fromObject(msg);

    }


}
