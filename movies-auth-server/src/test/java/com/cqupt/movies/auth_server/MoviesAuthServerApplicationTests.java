package com.cqupt.movies.auth_server;

import com.cqupt.movies.auth_server.feign.MemberFeignService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MoviesAuthServerApplicationTests {

    @Autowired
    MemberFeignService memberFeignService;

    @Test
    void contextLoads() {
        System.out.println(memberFeignService);
    }

}
