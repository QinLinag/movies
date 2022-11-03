package com.cqupt.movies.celebrities.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.movies.celebrities.entity.Entity;
import com.cqupt.movies.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-10-26 08:28:21
 */
public interface CelebritiesService extends IService<Entity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Entity> listByName(String name);

    //查询出明星一共出演了多少部电影
    Long countMoviesByCelebId(Long id);

}

