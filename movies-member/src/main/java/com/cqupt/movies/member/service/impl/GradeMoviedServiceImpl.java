package com.cqupt.movies.member.service.impl;

import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.Query;
import com.cqupt.movies.member.feign.MovieSFeignService;
import com.cqupt.movies.member.vo.InfoMovieVo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cqupt.movies.member.dao.GradeMoviedDao;
import com.cqupt.movies.member.entity.GradeMoviedEntity;
import com.cqupt.movies.member.service.GradeMoviedService;
import org.springframework.transaction.annotation.Transactional;


@RabbitListener(queues = "memberinfo.release.memberinfo.queue")   //监听
@Service("gradeMoviedService")
public class GradeMoviedServiceImpl extends ServiceImpl<GradeMoviedDao, GradeMoviedEntity> implements GradeMoviedService {

    @Autowired
    MovieSFeignService movieSFeignService;






    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GradeMoviedEntity> page = this.page(
                new Query<GradeMoviedEntity>().getPage(params),
                new QueryWrapper<GradeMoviedEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public GradeMoviedEntity getByMemberIdAndMovieId(InfoMovieVo infoMovieVo) {
        GradeMoviedEntity gradeMoviedEntity = this.baseMapper.selectOne(new QueryWrapper<GradeMoviedEntity>().eq("member_id", infoMovieVo.getMemberId()).eq("movie_id", infoMovieVo.getMovieId()));
        if (gradeMoviedEntity!=null) {
            return gradeMoviedEntity;
        }else {
            return null;
        }

    }

    @Transactional
    @Override
    public void saveGradeMovie(GradeMoviedEntity gradeMoviedEntity) {
        this.baseMapper.insert(gradeMoviedEntity);
    }

}