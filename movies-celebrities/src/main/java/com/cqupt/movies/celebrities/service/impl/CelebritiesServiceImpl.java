package com.cqupt.movies.celebrities.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.movies.celebrities.service.CelebritiesService;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.cqupt.movies.celebrities.dao.Dao;
import com.cqupt.movies.celebrities.entity.Entity;


@Service("Service")
public class CelebritiesServiceImpl extends ServiceImpl<Dao, Entity> implements CelebritiesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Entity> page = this.page(
                new Query<Entity>().getPage(params),
                new QueryWrapper<Entity>()
        );

        return new PageUtils(page);
    }


    //模糊查询电影，通过名字   中文名或英文名
    @Override
    public List<Entity> listByName(String name) {
        List<Entity> entities = this.getBaseMapper().selectList(new QueryWrapper<Entity>().like("name", name).or().like("english_name", name));
        return entities;

    }

}