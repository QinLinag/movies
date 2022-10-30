package com.cqupt.movies.auth_server.feign;


import com.cqupt.movies.auth_server.vo.SmsVo;
import com.cqupt.movies.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("movies-third-part")
public interface ThirdPartFeignService {

    @PostMapping(value = "/sendCode")
    void sendCode(@RequestBody SmsVo sms);
}
