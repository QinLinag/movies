package com.cqupt.movies.movies.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.common.utils.PageUtils;
import com.cqupt.common.utils.Query;

import com.cqupt.movies.movies.dao.InfoMovieDao;
import com.cqupt.movies.movies.entity.InfoMovieEntity;
import com.cqupt.movies.movies.service.InfoMovieService;


@Service("infoMovieService")
public class InfoMovieServiceImpl extends ServiceImpl<InfoMovieDao, InfoMovieEntity> implements InfoMovieService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InfoMovieEntity> page = this.page(
                new Query<InfoMovieEntity>().getPage(params),
                new QueryWrapper<InfoMovieEntity>()
        );

        return new PageUtils(page);
    }

}