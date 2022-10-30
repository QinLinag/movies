package com.cqupt.movies.celebrities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class MoviesCelebritiesApplicationTests {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        String k1 = redisTemplate.opsForValue().get("k1");
        System.out.println(k1+"  1654987465165");

    }

}
