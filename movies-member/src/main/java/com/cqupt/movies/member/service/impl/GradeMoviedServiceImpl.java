package com.cqupt.movies.member.service.impl;

import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cqupt.movies.member.dao.GradeMoviedDao;
import com.cqupt.movies.member.entity.GradeMoviedEntity;
import com.cqupt.movies.member.service.GradeMoviedService;


@Service("gradeMoviedService")
public class GradeMoviedServiceImpl extends ServiceImpl<GradeMoviedDao, GradeMoviedEntity> implements GradeMoviedService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GradeMoviedEntity> page = this.page(
                new Query<GradeMoviedEntity>().getPage(params),
                new QueryWrapper<GradeMoviedEntity>()
        );

        return new PageUtils(page);
    }

}