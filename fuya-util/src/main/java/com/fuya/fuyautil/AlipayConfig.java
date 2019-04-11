package com.fuya.fuyautil;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092800613491";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCz6HMRXnYv8WeLho0kvEAr7tyA0SM24uxGesuIMNzVsm4FMw8eXmJRaCbQn9y2WfRNGDVBmoCYJDlL2ouf02dzCtXvbgFYyRPmkqEgehf3Uacud6lWUE4hpdhP/T4kffnNFTXDfVx8f7xxeMvAJL6IE1j6L1OiYCgUURA9x8Gw2Bdc3D3fWs0LDjzdNRaEA6dR9Xn1PoxcJHbAu8IpxcoCXpcyNPQp5qPYZlyn9VWRP72G+Ef9Y7HnI9ByIdA485vB/+0JW96qSsAsEEMFSR94HEIfWXh+7DWTPC61lm2iSScbDVmQGnTQAbRFtdTyahFA6UN0u4DBbY9pah8vKTURAgMBAAECggEAYb+yhYVlgbcQI2b/2S3PUA+ADx2SnIe/CKw/WcfytMk1re2IEWAG22RTcpbpPMqzShAVH4fqxr8nwmcD5drfIkqI4oZKhQr7nROeKB8uVlovE7hAcX1Adle9p/hYg0tPJnaXt5etYJems6CGU+ei/hxFA+oi7t5/6EoDIQ4f1w0nEr4Jdke8kMgG6PW7oUzDtLOnHYuf2K1cZOrJTrBPDBE+T2HBGPhZSMxKMr9bTyjhJi3BI4NHnR4k5N2Y9D3MzHqp+jfflrTrvsYl2u+6DL3uj+dfk7ah3HDsYsN6hXLz8I5B2Z83ldDf2gnAXMIMql2dIgatsb8mmyrrzve84QKBgQDxXQzZ1c9fwQQENXDMU8p6Zlxc4cbWz5+cGLPxrKVjZq8ufsZ5uiwHl9xQuDFK9NpbOpuh36KBVsLfets6eQ17PpIifWsIietVgFEnWcH7rF1iFZIQ96XOHoylq1P4eME/nLBSIKHk/RfiaTRYPg3ZxHX8J1keFGI3JVQuDRN4GwKBgQC+0VvQBQzCEBbRwOzOE92TSWjyKT8EmfComg9rTxpSCzR7Y4rjJueDdG9z/QplftxSt+xToWAGLCVXUj8uYbKzcsDarF2zHCRGAdVGkYxlN0yRmmMLTzXqKmng5ZDez5hmsSIsed+ezJVYxV+JMi+h24GpMhhAGzVixkoAIYqyQwKBgHqN8eGGJEcNnbl5XS4S+cPOx+VNqwYV5IiBLpVvQYdIc3kbXDIg6wxr+q1t6XGTBHDAQJggEsazxD1vh3OYVdUXf2JN5pQuW7VNc2eUtt/QtJYCN+mvpZwUfPv+E6QdxJpFOG5rLWk1bfRD0RVhZO0HSZVgcWrJlNlFdgnwD8uzAoGBAJmms6uZpWBO3FEibIy0amNDZYI5vEjUPHlHNMtP8v3FkAkbaqbmmN29UFGxnEGGGZtyBpaBB3Vw7tCVpdKPUPo2mRx8Vyb2ri7BIx+j681E7h8Almhw7BUW+hLiR/liaHGoiMAPkwWn70qABCHRi5Fl086OTACy6BYVXVklqS1JAoGBAMg89C5N+PZFrp1ccP5dZsWxCsnB33bSpYa13iLBKiIS32zg5jWf4l/Af1idtzcrC2dsK1TNnTPsiLIAw1zrtBKr5nMv1Y5GWOo+K3jwiuNm57etekLxjjNLA6vYxKqwBeqQwhMCgHO3EeGDD/BtkkB5pYtvtSyyW8+vXfTLTNuk";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs+hzEV52L/Fni4aNJLxAK+7cgNEjNuLsRnrLiDDc1bJuBTMPHl5iUWgm0J/ctln0TRg1QZqAmCQ5S9qLn9NncwrV724BWMkT5pKhIHoX91GnLnepVlBOIaXYT/0+JH35zRU1w31cfH+8cXjLwCS+iBNY+i9TomAoFFEQPcfBsNgXXNw931rNCw483TUWhAOnUfV59T6MXCR2wLvCKcXKAl6XMjT0Keaj2GZcp/VVkT+9hvhH/WOx5yPQciHQOPObwf/tCVveqkrALBBDBUkfeBxCH1l4fuw1kzwutZZtokknGw1ZkBp00AG0RbXU8moRQOlDdLuAwW2PaWofLyk1EQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

