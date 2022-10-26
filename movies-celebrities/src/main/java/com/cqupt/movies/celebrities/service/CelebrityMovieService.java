package com.cqupt.movies.celebrities.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.common.utils.PageUtils;
import com.cqupt.movies.celebrities.entity.CelebrityMovieEntity;

import java.util.Map;

/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-10-26 08:28:21
 */
public interface CelebrityMovieService extends IService<CelebrityMovieEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

