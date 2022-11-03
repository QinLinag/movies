package com.cqupt.movies.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.common.utils.PageUtils;
import com.cqupt.common.utils.Query;

import com.cqupt.movies.member.dao.BadMovieDao;
import com.cqupt.movies.member.entity.BadMovieEntity;
import com.cqupt.movies.member.service.BadMovieService;


@Service("badMovieService")
public class BadMovieServiceImpl extends ServiceImpl<BadMovieDao, BadMovieEntity> implements BadMovieService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BadMovieEntity> page = this.page(
                new Query<BadMovieEntity>().getPage(params),
                new QueryWrapper<BadMovieEntity>()
        );

        return new PageUtils(page);
    }

}