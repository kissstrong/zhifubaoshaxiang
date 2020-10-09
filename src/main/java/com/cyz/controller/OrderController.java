package com.cyz.controller;
import com.alipay.api.AlipayApiException;
import com.cyz.bean.AlipayBean;
import com.cyz.service.Alipay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author cyz
 * @date 2020-10-09 10:37
 */
@Controller
public class OrderController {
    @Autowired
    Alipay alipay;
    @RequestMapping("/alipay1")
    @ResponseBody
    public String alipay(AlipayBean alipayBean) throws AlipayApiException {
        return alipay.pay(alipayBean);
    }
    @RequestMapping("/return_url")
    @ResponseBody
    public String ok(){
        return "ok";
    }
}
