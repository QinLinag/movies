package com.cqupt.movies.celebrities.feign;


import com.cqupt.movies.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("movies-movies")
public interface MoviesFeignService {

    @RequestMapping("/movies/movie/list/byids")
    R listByIds(@RequestParam("ids") List<Long> ids);


    @GetMapping("/movies/infomovie/list/infomovies/bymovieids")
    R listByMovieIds(@RequestParam("movieIds") List<Long> movieIds);

}
