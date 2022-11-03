package com.cqupt.movies.movies.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.movies.entity.MovieEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-10-26 00:43:48
 */
public interface MovieService extends IService<MovieEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<MovieEntity> listByName(String name);

    List<MovieEntity> listAllMovies();

    List<MovieEntity> listByTags(List<Integer> tags);
}

