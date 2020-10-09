package com.cyz.service;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.cyz.bean.AlipayBean;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.UUID;
/**
 * @author cyz
 * @date 2020-10-09 10:36
 */
@Component
public class Alipay {

    /**
     * 支付接口
     * @param alipayBean
     * @return
     * @throws AlipayApiException
     */

    public String pay(AlipayBean alipayBean) throws AlipayApiException {
        // 1、获得初始化的AlipayClient
        //serverUrl固定
        String serverUrl = "https://openapi.alipaydev.com/gateway.do";
        //appId在沙箱页面有
        String appId = "2016102500758734";
        //私钥自己生成的
        String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCkoW/KIhRJOMziO3kfF9yR7JbBaHFuwFkx/lU7s/9W+Bzv7T+BKUoA4SZPrIXQI8iI7Iu1dI2atzCU+lV4e4XF6MBJM55KmKf+IjNXC+4UhljBHzZvcP4V8m0l6vJ7J/1ue9x/ppZ2jNd2y8T1ykJSH/655H9PodSUrEB5U637kvydBg4P81y4FwvnqhCeHjl7HZcmfi5+jquahAwQtU69LmPm0bAjDvcenWhSrJx0BL1MHW+zVD0/0Gjb2BFUC5B2FAzc5+RYmrdMyKMs7Es3Z90xpdUBnTYBJ82vC25spn8r5dwf3TbvKludqm1asCehzwwuU0sqCxvjax0g15vHAgMBAAECggEAMw/bY390eU2DxruZkrpM8HnIPI7PeAFy6N2wxAbNMMVd/FDCvKX4zRMIVff1vRwMK+PqZUqUTSN6jwqiDe+KC1tooIvBEfQNTvZ+pAYQPw94YdXzhVcvh9EcZ4Fa2jYKFgWW24To8DY48iC7o1yre1tvH2suzZ0tGTvf94Gr2fY38uf4K8JDM1jxvPvO7R13uKw7SzhpfLRp9ijnaAz11n5BmvNLpM+SeOMPCvNiICFT1C6ijuKulkYmIsjD8U+aj/Q5KEOHPAc4vUGrlHf1bJDOMq+Rr+EoBPvRkjTvNlujM4pwm4BBjEnmsfDUp1Fa6uugn13AQHg9+RYHv7rNmQKBgQD9aJdy3mbik95HQl4/1sYn5vT0WomgURbAtG0Fvgk2/3Tga+aIQUVAN3LXTW/uGKo7jRdGZHI5Zt5NhC5YbUHLr5GpagS9JcaBxf3ysK3+sZw6/4TNVUCFnPPlswY6FxnK7UCF+lyAuWo2yJhSdj7I8Ds6EO4Lwe3RI9uNBnppZQKBgQCmUG4Dabzzx7Qp901NgvDJYAUSFb9ss3iDgbd9zbl02yfmRzVguvhidDAr9yOODyQZ03IiPH4TMz+n8ZOZEA1WaAs1j/iU/J5Fg9bu0wW9f2HVaOdU7q87YMRAq/QPAGpdlSJLDUYClhO7JQyU05EQ9aEGbkUf/ogvXVbneJ+zuwKBgDdwpIGrCpNTinPIn1oUIPIl4z16eVWrp/CtCJUTCG0R4n4e3Sa+MOpltyhHds9+ce/pZEgwPZU1XjlTS9hovtZTKOkJmtPO6ekGahKoeMiaDvYRZPqPRjh1KB7+1cPrvebZlR46sfYvmn81z61q1T2c4ThI7+t+mAsDb0shObLJAoGAWquEnqW51qmsRijF4xkczpxjh02F8+wQKpnAgHx0vAlywi9WIWstWPeA/6cXoi4M/2twUMvr6+nHrXadghfnPZExcz0PR6JFYx6SQdeXzIyyuJcdbwdFTzjdJWjZgIg4dNaGQKZ24ZgSH7f+GPESqE1RQWFCnEO22SOo6j6vylsCgYA1iBRLDybDlHqFUiofMXpp8NHnqPbJ6RcNS4ppWnE392SR4hH45aevT8KF62RcX3oLFqxTvkZMFSHqZEhKEPdYRci90qpU5l1i7HQro60bE4WDRw661yYqeoaLDQrCXjgBjdNSKTPZSVIYBwsVGAsjXDbsTRkWILRgoxcd7fhBDw==";
        ///格式都为json 固定
        String format = "json";
        //编码格式为utf-8 固定
        String charset = "utf-8";
        //公钥自己生成，也可以在沙箱网站看到
        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm9NjpbsoKNiuek58KFRgdlUXS2Qd+YbFGtZTN376BRy0L3hWUdeGiUgq02pzc+0aaD2IoRYsdYvSJaq9XOyjbuM0Z9i8NJMafU1OqYQVHa7A+jDEVQFLUibGTExE1t8stGFGKOSZ/fZlrrVzBf74evuYeUfatD2385V/h1gJ1jJ68zwrrNqArAcNyIeNql43ZmJHTnkgHabXINqT1XGgL419hSRIoRYgf7Hl3PK9EAbe+FChg115CNW1Vw4EJ5Oc3h7Y45a2Wcr3fIyN2mDWUZ56Z0w0uh2URn2p0UBefvRJg8KcMtn9WkOhaiwMHu1FAT+QyR/ZKYyf7qAxGlEsUQIDAQAB";
        //秘钥类型 基本固定
        String signType = "RSA2";
        //支付成功发下面的请求  return_url为网页重定向通知，是由客户的浏览器触发的一个通知，若客户去网银支付，也会受银行接口影响，由于各种影响因素特别多，所以该种类型的通知支付宝不保证其到达率。
        //买家付款成功后,会跳到 return_url所在的页面,这个页面可以展示给客户看,这个页面只有付款成功才会跳转,并且只跳转一次..
        String returnUrl = "http://localhost:8080/return_url";
        // notify_url为服务器通知，支付宝可以保证99.9999%的通知到达率，前提是您的网络通畅。
        String notifyUrl = "http://localhost:8080/ok.html";

        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
        // 2、设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(returnUrl);
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(notifyUrl);
        //账户订单号
        String out_trade_no = new Date().getTime()+ UUID.randomUUID().toString();
        alipayBean.setOut_trade_no(out_trade_no);
        // 封装参数
        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
        // 3、请求支付宝进行付款，并获取支付结果
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        // 返回付款信息
        return result;
    }
}

