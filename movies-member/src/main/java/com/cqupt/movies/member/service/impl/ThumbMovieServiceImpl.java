package com.cqupt.movies.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.common.utils.PageUtils;
import com.cqupt.common.utils.Query;

import com.cqupt.movies.member.dao.ThumbMovieDao;
import com.cqupt.movies.member.entity.ThumbMovieEntity;
import com.cqupt.movies.member.service.ThumbMovieService;


@Service("thumbMovieService")
public class ThumbMovieServiceImpl extends ServiceImpl<ThumbMovieDao, ThumbMovieEntity> implements ThumbMovieService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThumbMovieEntity> page = this.page(
                new Query<ThumbMovieEntity>().getPage(params),
                new QueryWrapper<ThumbMovieEntity>()
        );

        return new PageUtils(page);
    }

}