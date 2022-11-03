package com.cqupt.movies.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.common.utils.PageUtils;
import com.cqupt.movies.member.entity.KeenMovieEntity;

import java.util.Map;

/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-11-03 10:03:41
 */
public interface KeenMovieService extends IService<KeenMovieEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

