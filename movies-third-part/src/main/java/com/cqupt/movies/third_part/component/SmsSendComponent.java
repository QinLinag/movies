package com.cqupt.movies.third_part.component;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;
import com.cqupt.movies.common.utils.HttpUtils;
import com.cqupt.movies.third_part.vo.SmsVo;
import lombok.Data;
import org.apache.http.HttpResponse;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
@Component
@ConfigurationProperties(prefix = "spring.alicloud.sms")
public class SmsSendComponent {

//    private String host;
//    private String path;
//    private String skin;
//    private String sign;
//    private String appcode;
//    public void     sendSmsCode1(String phone,String code){
//        String method="GET";
//        String appcode="93asdflasjdflojadfojasodlfjasdjfl";
//        HashMap<String, String> headers = new HashMap<>();
//        headers.put("Authorization","APPCODE"+appcode);
//        HashMap<String, String> querys = new HashMap<>();
//        querys.put("code","6379");
//        querys.put("phone","13372761079");
//        querys.put("sking",skin);
//        querys.put("sign",sign);
//
//        try {
//            HttpResponse response= HttpUtils.doGet(host,path,method,headers,querys);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }


    public void     sendSmsCode(SmsVo smsVo) throws Exception {
        Config config = new Config().setAccessKeyId("LTAI5tESKFmH7uFFkfFfNwFN")
                .setAccessKeySecret("7D5RuG29udw2H6hdGMp9bVerKPeTvp");

        config.endpoint="dysmsapi.aliyuncs.com";
        Client client = new Client(config);
        SendSmsRequest sendSmsRequest=new SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                .setPhoneNumbers(smsVo.getPhoneNum())
                .setTemplateParam("{\"code\":\""+smsVo.getCode()+"\"}");
       client.sendSms(sendSmsRequest);

    }
























}
