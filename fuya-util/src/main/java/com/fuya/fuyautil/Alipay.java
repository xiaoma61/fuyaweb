package com.fuya.fuyautil;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class Alipay {



    public static String AlipayUtil(int id,int fate) throws AlipayApiException {

        String app_id = AlipayConfig.app_id;
        String private_key = AlipayConfig.private_key;
        String notify_url = AlipayConfig.notify_url;
        String return_url = AlipayConfig.return_url;
        String url = AlipayConfig.url;
        String charset = AlipayConfig.charset;
        String format = AlipayConfig.format;
        String public_key = AlipayConfig.public_key;
        String signtype = AlipayConfig.signtype;

        AlipayClient client = new DefaultAlipayClient(url, app_id, private_key, format, charset, public_key, signtype);
        // 支付请求
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);
        AlipayTradePayModel model = new AlipayTradePayModel();


        String productCode = "FAST_INSTANT_TRADE_PAY";

        model.setOutTradeNo(UUID.randomUUID().toString());
        model.setSubject("支付测试");
        model.setTotalAmount(String.valueOf(fate));
        model.setBody("支付测试，共10元");
        model.setProductCode(productCode);
        alipayRequest.setBizModel(model);


        // 调用SDK生成表单, 并直接将完整的表单html输出到页面
        String result = client.pageExecute( alipayRequest).getBody();
        /*String result = alipayClient.pageExecute(alipayTradeAppPayRequest).getBody();*/
        return result;

    }
}
