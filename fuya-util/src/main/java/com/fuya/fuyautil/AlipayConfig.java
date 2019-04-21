package com.fuya.fuyautil;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.io.FileWriter;
import java.io.IOException;



public class AlipayConfig {

    public static String app_id = "2016092800613491";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCOGMXOnvwY2jmJ1HsZpxpXI1VyYHK6V6EJerlfTE3C2+AQdZUYVyngfMwog+yb2DMPF32pjBkF0o8/HAvDoP7JOKTDf/gmq94YcBZtfvWVp98VehOakxK+RD79JuZD+eZv55fXaQf58j9h9KhBB59nUwY4qTZslam32bocShVHQjsGd0JMEBxm3kmXSnPjCOVeBDQ1e5yd5cNZ6HqnjqlcqAhUk5AXuHaFZxiexsrGmnOAI187yAaMNffNOYxPaoMS9ZBtTVzl4vtmXds1aWofzfpZVzlH5x02E6E91B/Gs8Fx98a3gyZAkodTbfX9heGzSP0O71q3mpo8g3Xuukd/AgMBAAECggEBAIsUIh+vKo3koeaOqwIbtUNOmSZ+0iwi8sKFdQSaeY3ciX1yqMCPJ7TKUUTiguaygJ8n3d3h+ufhBEW/3J/YZyhqIZIcIbUwCQygQMNhPVwCV+38c02a4ACMaZ57wWsNIIetBMhYy+7PNUxaG4iyvklyUKeKr3MuNVR2bZucwyDfcaYotZmVBebhBLZSAqwCFuK2OP3bjQMPXd6t2pyPowDgcSuEiDO0it3FnA4SdZkgBwMXQI4NpYOwF0GPsjzvohqNBlioat6Bm71lczOFm7ybA+VmJCGsBvzD02wWL3Z8stmpqxaTv+zlxhcZPPCPgdTSKxYSBI08uNjt9rIDd4kCgYEA1Pr3hY+KLOYvBpY0GQ7X+QtiMKBoEOsccCNz4B7hkwtbT86B4FcOCv2f1O/cHzKsFYFWfuIdAxrgVcQVlZby1/Jnf0LY2NueVH9AjqKGKOCtV7/P5O11C1Tb7+0Ho0Eo2jWW+0B8cDpO07c+AaUzMsXYFs0TMm+TUGpmNag2ImUCgYEAqsx58HlaVh5bD+ga43ihqgSOrMxy384tySU1KYc8AU+lSbcE9eJ/gcllU7RJVURebsbdsAzrzOakbj8WIcO37kMn8mOK8V9p0ZskTgrlhlI8t6+wtapjSOJLvQyki/S76S+c6mLK7rWe0LJcGjAl8W99iQHwvX+OvLW5LOEmMhMCgYBnBWK3u2AWKd3zbLKUsGZt9ECxztWn4WJnJ+8EKUvqCeNIblbKgrGrRVWL9d5fRO6SlHaXd56bAuQi+G+MZXDgXkQL/fTQqSMkMk2y8RC+MzgGKyVP0kD8kza0Ds7T/E/bhXKb5GyNCNdad9fJifSDCcpl9RrlGa2Dh4QbTlupNQKBgBj017xzfuemd9fTKlqifbzB/bbyCLb7AWWqsiHnSLVIVfZ3b5oeBlOIge3BIhX7b3DwTeQzfyDpco1JWoqEdpOx7FxWfy7VqjieWqesn+0Ihyav3TouM2Oq7TYqasCoeeyQsHTvCofmsdX8uch50JF+oPEr/2kxAkJAq1YLb/FRAoGAQCVMZ3GRXus0fTzyqKZN25a0zPR7YxNmrLMxFVnnzP1F6q3DJv3czchKGpn3Ky3BatT5oSYomgUzmvUzGSeR2Wgc59w4VTPMKjFRSDTB2n3X2PyRkZBsFv2pNT2HIxGmxP31dV7MDzNGxuJaY8xfHvvVU+ZQVZB0HulYYOeaR3I=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1TgY2SK2XKaw/0BTDVDD81M5qPAS94ylMVjAfR9Mc7DqSBzkHOfhTcMvbpKjLH/OLolpsbXMf1y2AV067tyPUoWN1ymJ9EcwYsUVLpILYJl8LqzEMY+A/i/QiGI7Cy8HNm/IH5bQqLs6abyoXzqEcETUrkaw5XLnVm2ApIgnFjonoxqKnaMzVPNJNXRk8q9NGCUVJxt/G0SQoD1sC0VeLU58hKkB5jX4azyXY3AXMuWtDHDYxh9rChPg806MyQpmajrEQg+VMddmbcpdCeTTLtTkCC2XovZytkBzfFRN581VUDpkSLWO+HZFJcwEeffQxq79ZmMBn+rIsuiAGuylXQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "https://campus.gbdev.cn:8060/fuyaweb/order/synCallBack";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "https://campus.gbdev.cn:8060/fuyaweb/order/synCallBack";
    // 签名方式
    public static String signtype = "RSA2";
    // 字符编码格式
    public static String charset = "utf-8";
    public static String format = "json";
    // 支付宝网关
    public static String url = "https://openapi.alipaydev.com/gateway.do";
}

