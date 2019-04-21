package com.fuya.fuyaweb.IndexyuesaoController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.fuya.ActiveMQ.service.ProductService;
import com.fuya.fuyadao.entity.EMPLOYERINFORMATION;
import com.fuya.fuyadao.entity.ORDERS;
import com.fuya.fuyadao.entity.SERVICECONTENT;
import com.fuya.fuyaservice.EMPLOYERINFORMATIONService;
import com.fuya.fuyaservice.ORDERSService;
import com.fuya.fuyaservice.SERVICECONTENTService;
import com.fuya.fuyautil.Alipay;
import com.fuya.fuyautil.AlipayConfig;
import com.fuya.fuyautil.TimeUtil;
import com.fuya.fuyautil.uuidUtil;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Queue;
import javax.jms.Topic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
public class PayController {


    @Autowired
    ORDERSService ordersService;
    @Autowired
    SERVICECONTENTService servicecontentService;
    @Autowired
    EMPLOYERINFORMATIONService employerinformationService;
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;
    @Autowired
    private ProductService productService;



    //订单提交

    /**
     *
     * @param request
     * @param toid
     * @param childbirth
     * @param starttime
     * @param fate
     * @param others
     * @param handsel
     * @param handselstatus：支付状态，1 为未支付
     * @param sum
     * @param sumstatus：
     * @param name
     * @param area
     * @param address
     * @param phone
     * @param type
     * @param idcard
     * @param response
     * @throws ParseException
     * @throws AlipayApiException
     * @throws IOException
     */
    @RequiresRoles("users")
    @RequestMapping("/fuyayusao/order/add")
    @ResponseBody
    @CrossOrigin
    public JSONObject addorder(HttpServletRequest request,
                               @RequestParam(name = "toid")int toid,
                               @RequestParam(name = "childbirth",defaultValue = "2019-11-10")String childbirth, @RequestParam(name = "starttime",defaultValue = "2020-10-10")String starttime,
                               @RequestParam(name = "fate",defaultValue = "10")int fate,
                               @RequestParam(name = "others",defaultValue = "学习")String others, @RequestParam(name = "handsel",defaultValue = "100000")String handsel,
                               @RequestParam(name = "handselstatus",defaultValue = "1")int handselstatus, @RequestParam(name = "sum",defaultValue = "100000")String sum,
                               @RequestParam(name = "sumstatus",defaultValue = "1")int sumstatus,
                               @RequestParam(name = "name",defaultValue = "洪诗仙")String name,@RequestParam(name = "area",defaultValue = "广东广州")String area,
                               @RequestParam(name = "address",defaultValue = "广东广州越秀区黄花岗教师公寓2号")String address,
                               @RequestParam(name = "phone",defaultValue = "131444051089")String phone,@RequestParam(name = "type",defaultValue = "1")int type,
                               @RequestParam(name = "idcard",defaultValue = "44051019990820082X")String idcard,
                               @RequestParam(name = "CONTRACTNUMBER",defaultValue = "440510")String CONTRACTNUMBER,
                               HttpServletResponse response

                               ) throws ParseException, AlipayApiException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("id");

        //生成订单号码
        Date date=new Date();
        ORDERS orders=new ORDERS();
        orders.setFROMID(id);
        orders.setTOID(toid);

      /*  SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        String dateS=simpleDateFormat.format(new Date());*/
        orders.setCONTRACTNUMBER(CONTRACTNUMBER);
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
        Date currentTime = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date childdate = simpleDateFormat1.parse(childbirth);
        java.sql.Date cbirth=TimeUtil.stringtodate(childbirth);
        servicecontent.setCHILDBIRTH(childdate);
        servicecontent.setFATE(fate);
        servicecontent.setHANDSEL(handsel);
        servicecontent.setHANDSELSTATUS(handselstatus);
        servicecontent.setORDERID(orders.getORDERSID());
        servicecontent.setOTHERS(others);
    /*    java.sql.Date sbirth=TimeUtil.stringtodate(starttime);*/
        Date sbirth = simpleDateFormat1.parse(starttime);
        servicecontent.setSTARTTIME(sbirth);
        servicecontent.setSTATUS(2);
        servicecontent.setSUM(sum);
        servicecontent.setSUMSTATUS(sumstatus);
        servicecontentService.save(servicecontent);
        Map<String,Object>map=new HashMap<>();
        map.put("employerinformation",employerinformation);
        map.put("servicecontent",servicecontent);

        String Msg=productService.sendMessage(topic,JSONObject.fromObject(map).toString());

        Map<String,Object> msg=new HashMap<>();
        msg.put("msg","success");
        return JSONObject.fromObject(msg);



        //添加
    /*    Map<String,Object> msg=new HashMap<>();
        msg.put("msg",result);
        return JSONObject.fromObject(msg);*/
    }
    @RequiresRoles("users")
    @RequestMapping("/order/pay")
    @CrossOrigin
    public void pay(HttpServletResponse response, HttpSession session, @RequestParam(name = "CONTRACTNUMBER")String CONTRACTNUMBER, @RequestParam(name = "fate")int fate) throws AlipayApiException, IOException {
        String result = Alipay.AlipayUtil(CONTRACTNUMBER,fate);
        response.setContentType("text/html;charset=" + "UTF-8");
        response.getWriter().write(result); // 直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();

    }

    @RequestMapping("/order/synCallBack")
    public JSONObject synCallBack(@RequestParam Map<String, String> params,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> msg=new HashMap<>();

        // 2.验签操作,参考支付宝Demo的return_url.jsp
        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.public_key, AlipayConfig.charset,
                    AlipayConfig.signtype); // 调用SDK验证签名


            if (!signVerified) {
                msg.put("msg","error");
                return JSONObject.fromObject(msg);
            }
            // 商户订单号
            String outTradeNo = params.get("out_trade_no");
            // 支付宝交易号
            String tradeNo = params.get("trade_no");
            // 付款金额
            String totalAmount = params.get("total_amount");

            JSONObject data = new JSONObject();
            data.put("outTradeNo", outTradeNo);
            data.put("tradeNo", tradeNo);
            data.put("totalAmount", totalAmount);
            return data;

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return JSONObject.fromObject(msg);
    }


    @RequiresRoles("users")
    @RequestMapping("order/refund")
    @ResponseBody
    @CrossOrigin
    public void refund(HttpServletResponse response, HttpSession session, @RequestParam(name = "CONTRACTNUMBER")String CONTRACTNUMBER, @RequestParam(name = "fate")int fate) throws AlipayApiException, IOException {

            response.setHeader("Access-Control-Allow-Origin", "*");
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.url, AlipayConfig.app_id, AlipayConfig.private_key, "json", AlipayConfig.charset, AlipayConfig.public_key, AlipayConfig.signtype);
        //设置请求参数

        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        String total_amount= String.valueOf(fate);
        String out_trade_no = CONTRACTNUMBER;
        String subject="支付测试";
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + CONTRACTNUMBER + "\","
                + "\"refund_amount\":\"" + total_amount + "\"}"
               );
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        response.setContentType("text/html;charset=" + "UTF-8");
        response.getWriter().write(result); // 直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();

     /*   Map<String,Object> msg=new HashMap<>();
        msg.put("msg",result);
        return JSONObject.fromObject(msg);*/

    }


}
