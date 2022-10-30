package com.cqupt.movies.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.member.entity.CollectMovieEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-10-26 08:29:08
 */
public interface CollectMovieService extends IService<CollectMovieEntity> {

    PageUtils queryPage(Map<String, Object> params);


    //根据用户id查询出对应的用户id和收藏的电影的所有实例
    List<CollectMovieEntity> getCollectMovieEntityByMemberId(Long memberId);
}

