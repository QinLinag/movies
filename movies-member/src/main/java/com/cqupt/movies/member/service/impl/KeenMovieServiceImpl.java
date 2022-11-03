package com.cqupt.movies.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.common.utils.PageUtils;
import com.cqupt.common.utils.Query;

import com.cqupt.movies.member.dao.KeenMovieDao;
import com.cqupt.movies.member.entity.KeenMovieEntity;
import com.cqupt.movies.member.service.KeenMovieService;


@Service("keenMovieService")
public class KeenMovieServiceImpl extends ServiceImpl<KeenMovieDao, KeenMovieEntity> implements KeenMovieService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<KeenMovieEntity> page = this.page(
                new Query<KeenMovieEntity>().getPage(params),
                new QueryWrapper<KeenMovieEntity>()
        );

        return new PageUtils(page);
    }

}