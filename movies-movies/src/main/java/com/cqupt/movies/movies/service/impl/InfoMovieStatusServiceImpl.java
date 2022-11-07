package com.cqupt.movies.movies.service.impl;

import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.Query;
import com.cqupt.movies.movies.vo.InfoMovieVo;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cqupt.movies.movies.dao.InfoMovieStatusDao;
import com.cqupt.movies.movies.entity.InfoMovieStatusEntity;
import com.cqupt.movies.movies.service.InfoMovieStatusService;


@Service("infoMovieStatusService")
public class InfoMovieStatusServiceImpl extends ServiceImpl<InfoMovieStatusDao, InfoMovieStatusEntity> implements InfoMovieStatusService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InfoMovieStatusEntity> page = this.page(
                new Query<InfoMovieStatusEntity>().getPage(params),
                new QueryWrapper<InfoMovieStatusEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public InfoMovieStatusEntity getByMemberIdAndMovieId(InfoMovieVo infoMovieVo) {
        InfoMovieStatusEntity infoMovieStatusEntity = this.baseMapper.selectOne(new QueryWrapper<InfoMovieStatusEntity>().eq("member_id", infoMovieVo.getMemberId()).eq("movie_id", infoMovieVo.getMovieId()));
        return infoMovieStatusEntity;
    }

}