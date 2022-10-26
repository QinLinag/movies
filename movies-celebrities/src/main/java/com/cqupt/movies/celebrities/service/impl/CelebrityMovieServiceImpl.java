package com.cqupt.movies.celebrities.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.common.utils.PageUtils;
import com.cqupt.common.utils.Query;

import com.cqupt.movies.celebrities.dao.CelebrityMovieDao;
import com.cqupt.movies.celebrities.entity.CelebrityMovieEntity;
import com.cqupt.movies.celebrities.service.CelebrityMovieService;


@Service("celebrityMovieService")
public class CelebrityMovieServiceImpl extends ServiceImpl<CelebrityMovieDao, CelebrityMovieEntity> implements CelebrityMovieService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CelebrityMovieEntity> page = this.page(
                new Query<CelebrityMovieEntity>().getPage(params),
                new QueryWrapper<CelebrityMovieEntity>()
        );

        return new PageUtils(page);
    }

}