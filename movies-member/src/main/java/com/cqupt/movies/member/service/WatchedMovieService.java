package com.cqupt.movies.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.member.entity.WatchedMovieEntity;
import com.cqupt.movies.member.vo.InfoMovieVo;

import java.util.Map;

/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-11-03 10:03:42
 */
public interface WatchedMovieService extends IService<WatchedMovieEntity> {

    PageUtils queryPage(Map<String, Object> params);


    //通过用户id和电影id查询用户已看该电影信息
    WatchedMovieEntity getByMemberIdAndMovieId(InfoMovieVo infoMovieVo);

    R deleteByMemberIdAndMovieId(InfoMovieVo infoMovieVo);
}

