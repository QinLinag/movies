package com.cqupt.movies.movies.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.movies.entity.InfoMovieStatusEntity;
import com.cqupt.movies.movies.vo.InfoMovieVo;

import java.util.Map;

/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-11-07 14:26:53
 */
public interface InfoMovieStatusService extends IService<InfoMovieStatusEntity> {

    PageUtils queryPage(Map<String, Object> params);

    InfoMovieStatusEntity getByMemberIdAndMovieId(InfoMovieVo infoMovieVo);
}

