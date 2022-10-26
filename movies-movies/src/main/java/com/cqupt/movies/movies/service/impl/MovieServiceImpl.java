package com.cqupt.movies.movies.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.common.utils.PageUtils;
import com.cqupt.common.utils.Query;

import com.cqupt.movies.movies.dao.MovieDao;
import com.cqupt.movies.movies.entity.MovieEntity;
import com.cqupt.movies.movies.service.MovieService;


@Service("movieService")
public class MovieServiceImpl extends ServiceImpl<MovieDao, MovieEntity> implements MovieService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MovieEntity> page = this.page(
                new Query<MovieEntity>().getPage(params),
                new QueryWrapper<MovieEntity>()
        );

        return new PageUtils(page);
    }

}