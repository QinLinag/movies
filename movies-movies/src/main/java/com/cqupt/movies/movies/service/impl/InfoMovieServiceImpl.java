package com.cqupt.movies.movies.service.impl;

import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

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

    @Override
    public InfoMovieEntity getByMid(Long mid) {

        InfoMovieEntity entity = this.getBaseMapper().selectOne(new QueryWrapper<InfoMovieEntity>().eq("mid", mid));
        return entity;
    }

    @Override
    public List<InfoMovieEntity> listByMovieIds(List<Long> movieIds) {
        List<InfoMovieEntity> infoMovieEntities = this.baseMapper.selectList(new QueryWrapper<InfoMovieEntity>().in(true, "mid", movieIds));
        return infoMovieEntities;

    }

}