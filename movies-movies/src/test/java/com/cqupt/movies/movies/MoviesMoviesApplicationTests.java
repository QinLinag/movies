package com.cqupt.movies.movies;

import com.cqupt.movies.movies.entity.MovieEntity;
import com.cqupt.movies.movies.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class MoviesMoviesApplicationTests {

    @Autowired
    MovieService movieService;

    @Test
    void contextLoads() {
        List<MovieEntity> entities = movieService.listAllMovies();  //先查出所有的电影
        MovieEntity movieEntity = entities.get(1);
        String[] split = movieEntity.getTags().split(",");
        System.out.println(movieEntity.getMid());
        for (String s : split) {
            System.out.println(s);
        }
    }

}
