package com.cqupt.movies.celebrities.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.movies.celebrities.entity.CelebrityMovieEntity;
import com.cqupt.movies.celebrities.entity.Entity;
import com.cqupt.movies.celebrities.vo.MoviesVo;
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
public interface CelebrityMovieService extends IService<CelebrityMovieEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //根据名人id，查询出名人和其参演的所有电影的实例，
    List<CelebrityMovieEntity>  listByCelebId(Long celebId);

    //通过明星的id查询其出演的电影
    List<MoviesVo> listMoviesByCelebId(Long celebId);


    //查询出明星所出演的电影中，点赞最多的几部电影，
    List<MoviesVo> listMostThumbMovieByCelebId(Long celebId,Long front);

    //查询出明星所出演的电影中，看过最多的几部电影，
    List<MoviesVo> listMostWatchedMovieByCelebId(Long celebId, Long front);

    //查询出明星所出演的电影中，想看最多的几部电影，
    List<MoviesVo> listMostKeenMovieByCelebId(Long celebId, Long front);
}

