package com.cqupt.movies.celebrities.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.cqupt.movies.celebrities.exception.CelebrityMovieException;
import com.cqupt.movies.celebrities.feign.MoviesFeignService;
import com.cqupt.movies.celebrities.vo.MoviesVo;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.Query;
import com.cqupt.movies.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cqupt.movies.celebrities.dao.CelebrityMovieDao;
import com.cqupt.movies.celebrities.entity.CelebrityMovieEntity;
import com.cqupt.movies.celebrities.service.CelebrityMovieService;


@Service("celebrityMovieService")
public class CelebrityMovieServiceImpl extends ServiceImpl<CelebrityMovieDao, CelebrityMovieEntity> implements CelebrityMovieService {

    @Autowired
    MoviesFeignService moviesFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CelebrityMovieEntity> page = this.page(
                new Query<CelebrityMovieEntity>().getPage(params),
                new QueryWrapper<CelebrityMovieEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CelebrityMovieEntity>  listByCelebId(Long celebId) {
        List<CelebrityMovieEntity> celebrityMoviesEntities = this.getBaseMapper().selectList(new QueryWrapper<CelebrityMovieEntity>().eq("celebrity_id", celebId));
        return celebrityMoviesEntities;
    }

    @Override
    public List<MoviesVo> listMoviesByCelebId(Long celebId) {
        //这里面保存了明星和电影的关联
        List<CelebrityMovieEntity> celebrityMovieEntities=listByCelebId(celebId);
        if (celebrityMovieEntities!=null&&celebrityMovieEntities.size()>0) {
            List<Long> ids = celebrityMovieEntities.stream().map((item) -> {
                return item.getMovieMid();
            }).collect(Collectors.toList());
            try {
                R r = moviesFeignService.listByIds(ids);
                if (r.getCode() == 0) {
                    //远程查询电影成功
                    List<MoviesVo> moviesEntities = r.getData("data", new TypeReference<List<MoviesVo>>() {
                    });
                    return moviesEntities;
                } else {
                    return null;
                }
            }catch (CelebrityMovieException e){
                throw e;
            }
        }else {
            return null;
        }
    }

}