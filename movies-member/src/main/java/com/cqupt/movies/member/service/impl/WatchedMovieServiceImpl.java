package com.cqupt.movies.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.common.utils.PageUtils;
import com.cqupt.common.utils.Query;

import com.cqupt.movies.member.dao.WatchedMovieDao;
import com.cqupt.movies.member.entity.WatchedMovieEntity;
import com.cqupt.movies.member.service.WatchedMovieService;


@Service("watchedMovieService")
public class WatchedMovieServiceImpl extends ServiceImpl<WatchedMovieDao, WatchedMovieEntity> implements WatchedMovieService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WatchedMovieEntity> page = this.page(
                new Query<WatchedMovieEntity>().getPage(params),
                new QueryWrapper<WatchedMovieEntity>()
        );

        return new PageUtils(page);
    }

}