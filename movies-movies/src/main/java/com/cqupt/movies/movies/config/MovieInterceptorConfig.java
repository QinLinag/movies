package com.cqupt.movies.movies.config;


import com.cqupt.movies.movies.interceptor.MovieInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MovieInterceptorConfig implements WebMvcConfigurer {
    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MovieInterceptor()).addPathPatterns("/**");
    }
}
