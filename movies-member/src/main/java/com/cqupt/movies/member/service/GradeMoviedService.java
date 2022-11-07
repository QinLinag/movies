package com.cqupt.movies.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.member.entity.GradeMoviedEntity;
import com.cqupt.movies.member.vo.InfoMovieVo;

import java.util.Map;

/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-11-06 14:49:44
 */
public interface GradeMoviedService extends IService<GradeMoviedEntity> {

    PageUtils queryPage(Map<String, Object> params);


    GradeMoviedEntity getByMemberIdAndMovieId(InfoMovieVo infoMovieVo);

    void saveGradeMovie(GradeMoviedEntity gradeMoviedEntity);
}

