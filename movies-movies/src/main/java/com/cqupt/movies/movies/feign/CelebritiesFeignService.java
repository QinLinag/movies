package com.cqupt.movies.movies.feign;


import com.cqupt.movies.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("movies-celebrities")
public interface CelebritiesFeignService {

    @RequestMapping("/celebrities/info/{id}")
    R info(@PathVariable("id") Long id);
}
