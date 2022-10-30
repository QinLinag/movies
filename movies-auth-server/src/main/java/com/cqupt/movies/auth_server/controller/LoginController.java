package com.cqupt.movies.auth_server.controller;


import com.alibaba.fastjson.TypeReference;
import com.cqupt.movies.auth_server.feign.MemberFeignService;
import com.cqupt.movies.auth_server.feign.ThirdPartFeignService;
import com.cqupt.movies.auth_server.vo.SmsVo;
import com.cqupt.movies.auth_server.vo.UserLoginVo;
import com.cqupt.movies.auth_server.vo.UserRegistVo;
import com.cqupt.movies.common.constant.AuthServerConstant;
import com.cqupt.movies.common.exception.BizCodeEnum;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.common.vo.MemberRespVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/authserver")
public class LoginController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private MemberFeignService memberFeignService;

    @Autowired
    private ThirdPartFeignService thirdPartFeignService;

    /**
     * 手机号发送短信，
     * */
    @GetMapping("/sms/sendcode")
    public R sendCode(@RequestParam("phone") String phone){

        //这个短信被创造后会先保存在redis中，  然后每次发送短信时判断一下redis中是否还有这个用户的短信，防止重复刷
        String redisCode = redisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone);
        if (!StringUtils.isEmpty(redisCode)) {  //redis中已经有该用户发送的短信了，
            long time = Long.parseLong(redisCode.split("_")[1]);   //根据redis存放的值规则取出时间
            if(System.currentTimeMillis()-time<60000){    //判断是否大于60秒了
                //60秒内不能发
                return R.error(BizCodeEnum.SMS_CODE_EXCEPTION.getCode(),BizCodeEnum.SMS_CODE_EXCEPTION.getMsg());
            }
        }

        //如果没有或者过期了
        String code = UUID.randomUUID().toString().substring(0, 5);
        String substring = code+"_"+System.currentTimeMillis();
        //先保存到redis中，
        redisTemplate.opsForValue().set(AuthServerConstant.SMS_CODE_CACHE_PREFIX+phone,substring,10, TimeUnit.MINUTES);

        //远程调用发送短信的服务
        SmsVo smsVo = new SmsVo();
        smsVo.setCode(code);
        smsVo.setMin(5);
        smsVo.setPhoneNum(phone);
        thirdPartFeignService.sendCode(smsVo);
        return R.ok();
    }


    @GetMapping("/regist")
    public String regist(@RequestParam("vo") UserRegistVo vo, RedirectAttributes redirectAttributes){
        String redisCode = redisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
        String code = vo.getCode();

        if (!StringUtils.isEmpty(redisCode)){
            if (code.equals(redisCode.split("_")[0])){
                //验证通过，删除验证；
                redisTemplate.delete(AuthServerConstant.SMS_CODE_CACHE_PREFIX+vo.getPhone());
                //验证通过调用远程用户服务进行注册
                R r = memberFeignService.regist(vo);
                if (r.getCode()==0){
                    //注册成功  定向到登入界面
                    return "redirect:http://movies/login.html";
                }else{
                    //注册失败
                    Map<String,String> errors=new HashMap<>();
                    errors.put("msg",r.getData("data",new TypeReference<String>(){}));
                    redirectAttributes.addFlashAttribute("errors",errors);
                    return "redirect:http://movies/auth/regist.html";
                }

            }else {
                //验证失败
                Map<String,String> errors=new HashMap<>();
                errors.put("msg","验证码错误");
                redirectAttributes.addFlashAttribute("errors",errors);
                //重定向页面到注册页面
                return "redirect:http://movies/auth/regist.html";
            }
        }else {
            //验证失效
            Map<String,String> errors=new HashMap<>();
            errors.put("msg","验证码失效");
            redirectAttributes.addFlashAttribute("errors",errors);
            //重定向页面到注册页面
            return "redirect:http://movies/auth/regist.html";
        }
    }


    @PostMapping("/login")
    public String login(@RequestParam("vo") UserLoginVo vo, RedirectAttributes redirectAttributes, HttpSession session) {

        //远程调用登入服务
        R login = memberFeignService.login(vo);
        if (login.getCode()==0){
            //登入成功
            MemberRespVo data = login.getData("data", new TypeReference<MemberRespVo>() {
            });
            //将用户信息放到session中
            session.setAttribute(AuthServerConstant.LOGIN_USER,data);
            return "redirect:http://movies.html";
        }else {
            //登入失败，
            Map<String,String> errors=new HashMap<>();
            errors.put("msg",login.getData("data",new TypeReference<String>(){}));
            redirectAttributes.addFlashAttribute("errors",errors);
            return "redirect:http://movies/login.html";
        }

    }





































}
