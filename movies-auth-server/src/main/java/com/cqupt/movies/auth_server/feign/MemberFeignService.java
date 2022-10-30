package com.cqupt.movies.auth_server.feign;


import com.cqupt.movies.auth_server.vo.UserLoginVo;
import com.cqupt.movies.auth_server.vo.UserRegistVo;
import com.cqupt.movies.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("movies-member")
public interface MemberFeignService {

    @PostMapping("/member/regist")
    R regist(@RequestBody UserRegistVo vo);

    @PostMapping("/member/login")
    R login(@RequestBody UserLoginVo vo);

}
