package com.cqupt.movies.movies;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MoviesMoviesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviesMoviesApplication.class);
    }

}
