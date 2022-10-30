package com.cqupt.movies.movies.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.movies.entity.InfoMovieEntity;

import java.util.Map;

/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-10-26 00:43:48
 */
public interface InfoMovieService extends IService<InfoMovieEntity> {

    PageUtils queryPage(Map<String, Object> params);


    //按照电影id查找电影的信息
    InfoMovieEntity getByMid(Long mid);
}

