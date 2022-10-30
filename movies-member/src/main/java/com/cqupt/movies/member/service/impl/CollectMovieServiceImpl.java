package com.cqupt.movies.member.service.impl;

import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cqupt.movies.member.dao.CollectMovieDao;
import com.cqupt.movies.member.entity.CollectMovieEntity;
import com.cqupt.movies.member.service.CollectMovieService;


@Service("collectMovieService")
public class CollectMovieServiceImpl extends ServiceImpl<CollectMovieDao, CollectMovieEntity> implements CollectMovieService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CollectMovieEntity> page = this.page(
                new Query<CollectMovieEntity>().getPage(params),
                new QueryWrapper<CollectMovieEntity>()
        );

        return new PageUtils(page);
    }



    @Override
    public List<CollectMovieEntity> getCollectMovieEntityByMemberId(Long memberId) {

        List<CollectMovieEntity> entities = this.getBaseMapper().selectList(new QueryWrapper<CollectMovieEntity>().eq("member_id", memberId));
        return entities;
    }

}