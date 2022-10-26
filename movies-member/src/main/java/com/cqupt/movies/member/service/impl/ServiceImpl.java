package com.cqupt.movies.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqupt.common.utils.PageUtils;
import com.cqupt.common.utils.Query;

import com.cqupt.movies.member.dao.Dao;
import com.cqupt.movies.member.entity.Entity;
import com.cqupt.movies.member.service.Service;


@Service("Service")
public class ServiceImpl extends ServiceImpl<Dao, Entity> implements Service {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Entity> page = this.page(
                new Query<Entity>().getPage(params),
                new QueryWrapper<Entity>()
        );

        return new PageUtils(page);
    }

}