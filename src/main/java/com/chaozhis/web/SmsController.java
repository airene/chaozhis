package com.chaozhis.web;


import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import com.chaozhis.AppConstants;
import com.chaozhis.utils.ToolUtils;
import com.chaozhis.utils.WebConf;
import com.chaozhis.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping(value = "/sms")
public class SmsController {


    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 注册发送短信<br>
     * 逻辑：注册业务是不需要登录的，要控制短信发送数量。控制方法 同IP发30个，同手机号88秒发一条。
     * TODO 逻辑要重新写一下, 安全 ,返回处理....
     */
    // fangying
    @RequestMapping(value = "send", method = {RequestMethod.POST})
    @ResponseBody
    public JSONObject sendSms(HttpServletRequest request) {
        String phone = WebUtils.getParamValue(request, "phone");

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", WebConf.getValue("ossKey"), WebConf.getValue("ossSecret"));
        try {
            String smsValiCode = ToolUtils.generateFourRandom();
            String remoteIp = WebUtils.getRemortIp();
            String canIpSms = redisTemplate.opsForValue().get(AppConstants.USER_IP_CAN_SEND_SMS + remoteIp);
            String canPhoneSms = redisTemplate.opsForValue().get(AppConstants.USER_MOBILE_CAN_SEND_SMS + phone);
            int ipSentCount = 0;
            if (canIpSms != null) {
                ipSentCount = Integer.parseInt(canIpSms);
            }

            if (ipSentCount < 5 && canPhoneSms == null) {
                DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms", "sms.aliyuncs.com");
                IAcsClient client = new DefaultAcsClient(profile);
                SingleSendSmsRequest aliRequest = new SingleSendSmsRequest();
                aliRequest.setSignName("平路");
                aliRequest.setTemplateCode("SMS_41480047");
                aliRequest.setParamString("{\"vali_code\":\"" + smsValiCode + "\"}");
                aliRequest.setRecNum(phone);
                SingleSendSmsResponse httpResponse = client.getAcsResponse(aliRequest);
                redisTemplate.opsForValue().set(AppConstants.USER_MOBILE_VALICODE + phone, smsValiCode, AppConstants.FIVE_MIN, TimeUnit.SECONDS);
                redisTemplate.opsForValue().set(AppConstants.USER_MOBILE_CAN_SEND_SMS + phone, "yes", AppConstants.ALMOST_TWO_MINUTES, TimeUnit.SECONDS);
                redisTemplate.opsForValue().set(AppConstants.USER_IP_CAN_SEND_SMS + remoteIp, (ipSentCount + 1) + "", AppConstants.ONE_DAY, TimeUnit.SECONDS);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String resultStr = "";
        JSONObject jsonObj = new JSONObject();
        if (resultStr.contains("ok")) {
            jsonObj.put("msg", "短信发送成功！");
            jsonObj.put("flag", "true");
        } else {
            jsonObj.put("msg", "短信发送失败！");
            jsonObj.put("flag", "false");
        }
        return jsonObj;
    }
}