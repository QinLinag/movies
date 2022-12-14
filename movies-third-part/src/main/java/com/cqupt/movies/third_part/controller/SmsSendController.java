package com.cqupt.movies.third_part.controller;


import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.third_part.vo.SmsVo;
import com.cqupt.movies.third_part.component.SmsSendComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/thirdpart/sms")
public class SmsSendController {

    @Autowired
    SmsSendComponent smsSendComponent;


    @GetMapping("/sendcode")
    public R sendCode(@RequestBody SmsVo smsVo){
        try {
            smsSendComponent.sendSmsCode(smsVo);
        } catch (Exception e) {
            return R.error(1,"短信发送失败");
        }
        return R.ok();
    }


    //腾讯云的
//    @PostMapping(value = "/sendCode")
//    public void sendCode(@RequestBody SmsVo sms){
//        int appId=1400330563;
//        String appKey="";
//        int templateId=308731;
//        String smsSign="CNXFS";
//        try {
//            String[] params={sms.getCode(),Integer.toString(sms.getMin())};
//            SmsSingleSender ssender=new SmsSingleSender(appId,appKey);
//            SmsSingleSenderResult result=ssender.sendWithParam("86",sms.getPhoneNum(),templateId,
//                    params,smsSign,"","");
//            System.out.println(result);
//        }catch (JSONException e){
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        } catch (HTTPException e) {
//            e.printStackTrace();
//        }
//    }



}





