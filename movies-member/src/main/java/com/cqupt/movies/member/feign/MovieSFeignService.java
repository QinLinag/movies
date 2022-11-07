package com.cqupt.movies.member.feign;


import com.cqupt.movies.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("movies-movies")
public interface MovieSFeignService {

    @RequestMapping("/movies/movie/list/byids")
    R listByIds(@RequestParam("ids") List<Long> ids);


    @PostMapping("/movies/infomoviestatus/list/statusid")
    R getById(@RequestParam("statusid") Long statusid);

}
