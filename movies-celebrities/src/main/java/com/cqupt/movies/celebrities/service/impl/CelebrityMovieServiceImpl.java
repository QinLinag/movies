package com.cqupt.movies.celebrities.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.cqupt.movies.celebrities.exception.CelebrityMovieException;
import com.cqupt.movies.celebrities.feign.MoviesFeignService;
import com.cqupt.movies.celebrities.vo.InfoMovieVo;
import com.cqupt.movies.celebrities.vo.MoviesVo;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.Query;
import com.cqupt.movies.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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



    @Override
    public List<MoviesVo> listMostThumbMovieByCelebId(Long celebId, Long front) {
        //先查出指定明星参演的电影，
        List<CelebrityMovieEntity> celebrityMovieEntities = this.baseMapper.selectList(new QueryWrapper<CelebrityMovieEntity>().eq("celebrity_id", celebId));
        List<Long> movieIds = celebrityMovieEntities.stream().map(item -> {
            return item.getMovieMid();
        }).collect(Collectors.toList());

        //远程调用，  查询出该演员参演的所有电影，
        R r = moviesFeignService.listByIds(movieIds);

        if (r.getCode() == 0) {
            if (celebrityMovieEntities.size() < front) {    //需要列出来的电影数如果大于该演员本身参演的电影数，那么就把参演了的电影全部返回
                return r.getData("data",new TypeReference<List<MoviesVo>>(){});
            }else {
                List<Long> frontMovieIds = selectFrontThumbIds(movieIds, front);
                if (frontMovieIds==null){   //frontMovieIds这个函数中的远程调用异常，
                    return null;
                }
                R r1 = moviesFeignService.listByIds(frontMovieIds);
                    List<MoviesVo> frontMovies = r1.getData("data", new TypeReference<List<MoviesVo>>() {
                    });
                    return frontMovies;
            }
        }else {  //远程调用异常，直接返回空，
            return null;
        }
    }

    @Override
    public List<MoviesVo> listMostWatchedMovieByCelebId(Long celebId, Long front) {
        //先查出指定明星参演的电影，
        List<CelebrityMovieEntity> celebrityMovieEntities = this.baseMapper.selectList(new QueryWrapper<CelebrityMovieEntity>().eq("celebrity_id", celebId));
        List<Long> movieIds = celebrityMovieEntities.stream().map(item -> {
            return item.getMovieMid();
        }).collect(Collectors.toList());

        //远程调用，  查询出该演员参演的所有电影，
        R r = moviesFeignService.listByIds(movieIds);

        if (r.getCode() == 0) {
            if (celebrityMovieEntities.size() < front) {    //需要列出来的电影数如果大于该演员本身参演的电影数，那么就把参演了的电影全部返回
                return r.getData("data",new TypeReference<List<MoviesVo>>(){});
            }else {
                List<Long> frontMovieIds = selectFrontWatchedIds(movieIds, front);
                if (frontMovieIds==null){   //frontMovieIds这个函数中的远程调用异常，
                    return null;
                }
                R r1 = moviesFeignService.listByIds(frontMovieIds);
                List<MoviesVo> frontMovies = r1.getData("data", new TypeReference<List<MoviesVo>>() {
                });
                return frontMovies;
            }
        }else {  //远程调用异常，直接返回空，
            return null;
        }
    }

    @Override
    public List<MoviesVo> listMostKeenMovieByCelebId(Long celebId, Long front) {
        //先查出指定明星参演的电影，
        List<CelebrityMovieEntity> celebrityMovieEntities = this.baseMapper.selectList(new QueryWrapper<CelebrityMovieEntity>().eq("celebrity_id", celebId));
        List<Long> movieIds = celebrityMovieEntities.stream().map(item -> {
            return item.getMovieMid();
        }).collect(Collectors.toList());

        //远程调用，  查询出该演员参演的所有电影，
        R r = moviesFeignService.listByIds(movieIds);

        if (r.getCode() == 0) {
            if (celebrityMovieEntities.size() < front) {    //需要列出来的电影数如果大于该演员本身参演的电影数，那么就把参演了的电影全部返回
                return r.getData("data",new TypeReference<List<MoviesVo>>(){});
            }else {
                List<Long> frontMovieIds = selectFrontKeenIds(movieIds, front);
                if (frontMovieIds==null){   //frontMovieIds这个函数中的远程调用异常，
                    return null;
                }
                R r1 = moviesFeignService.listByIds(frontMovieIds);
                List<MoviesVo> frontMovies = r1.getData("data", new TypeReference<List<MoviesVo>>() {
                });
                return frontMovies;
            }
        }else {  //远程调用异常，直接返回空，
            return null;
        }
    }


    //返回一个点赞最多的电影id集合
    private List<Long> selectFrontThumbIds(List<Long> movieIds,Long front){
        //远程调用，查询出这些电影对应的信息，   用于对比电影的点赞多少，
        R movieInfoR = moviesFeignService.listByMovieIds(movieIds);
        if (movieInfoR.getCode()==0){
            List<InfoMovieVo> infoMovieVos = movieInfoR.getData("data", new TypeReference<List<InfoMovieVo>>() {
            });

            //按照infoMovieVos中的点赞多少排序，
            List<InfoMovieVo> sortedInfoMovieVos = sortByThumb(infoMovieVos);

            List<Long> frontMovieIds=new ArrayList<>();   //存放前面点赞高的电影的id，

            for (int i = 0; i < front; i++) {   //循环front次，取出前面最多的几个电影
                InfoMovieVo infoMovieVo = sortedInfoMovieVos.get(i);
                Long mid = infoMovieVo.getMid();
                frontMovieIds.add(mid);
            }
            return frontMovieIds;
        }else {   //远程调用异常，
            return null;
        }
    }

    //返回一个看过最多的电影id集合
    private List<Long> selectFrontWatchedIds(List<Long> movieIds,Long front){
        //远程调用，查询出这些电影对应的信息，   用于对比电影的点赞多少，
        R movieInfoR = moviesFeignService.listByMovieIds(movieIds);
        if (movieInfoR.getCode()==0){
            List<InfoMovieVo> infoMovieVos = movieInfoR.getData("data", new TypeReference<List<InfoMovieVo>>() {
            });

            //按照infoMovieVos中的点赞多少排序，
            List<InfoMovieVo> sortedInfoMovieVos = sortByWatched(infoMovieVos);

            List<Long> frontMovieIds=new ArrayList<>();   //存放前面点赞高的电影的id，

            for (int i = 0; i < front; i++) {   //循环front次，取出前面最多的几个电影
                InfoMovieVo infoMovieVo = sortedInfoMovieVos.get(i);
                Long mid = infoMovieVo.getMid();
                frontMovieIds.add(mid);
            }
            return frontMovieIds;
        }else {   //远程调用异常，
            return null;
        }
    }

    //返回一个想看最多的电影id集合
    private List<Long> selectFrontKeenIds(List<Long> movieIds,Long front){
        //远程调用，查询出这些电影对应的信息，   用于对比电影的点赞多少，
        R movieInfoR = moviesFeignService.listByMovieIds(movieIds);
        if (movieInfoR.getCode()==0){
            List<InfoMovieVo> infoMovieVos = movieInfoR.getData("data", new TypeReference<List<InfoMovieVo>>() {
            });

            //按照infoMovieVos中的点赞多少排序，
            List<InfoMovieVo> sortedInfoMovieVos = sortByKeen(infoMovieVos);

            List<Long> frontMovieIds=new ArrayList<>();   //存放前面点赞高的电影的id，

            for (int i = 0; i < front; i++) {   //循环front次，取出前面最多的几个电影
                InfoMovieVo infoMovieVo = sortedInfoMovieVos.get(i);
                Long mid = infoMovieVo.getMid();
                frontMovieIds.add(mid);
            }
            return frontMovieIds;
        }else {   //远程调用异常，
            return null;
        }
    }

    //将一个集合按照点赞数排序，
    private List<InfoMovieVo> sortByThumb(List<InfoMovieVo> infoMovieVos){
        List<InfoMovieVo> sortedInfoMovieVos = infoMovieVos.stream().sorted((infoMovie1, infoMovie2) -> {
            int l = (int) (infoMovie1.getThumbUp() - infoMovie2.getThumbUp());
            return l;
        }).collect(Collectors.toList());
        return sortedInfoMovieVos;
    }

    //将一个集合按照看过次数排序
    private List<InfoMovieVo> sortByWatched(List<InfoMovieVo> infoMovieVos){
        List<InfoMovieVo> sortedInfoMovieVos = infoMovieVos.stream().sorted((infoMovie1, infoMovie2) -> {
            int l = (int) (infoMovie1.getWatched() - infoMovie2.getWatched());
            return l;
        }).collect(Collectors.toList());
        return sortedInfoMovieVos;
    }


    //将一个集合按照想看次数排序
    private List<InfoMovieVo> sortByKeen(List<InfoMovieVo> infoMovieVos){
        List<InfoMovieVo> sortedInfoMovieVos = infoMovieVos.stream().sorted((infoMovie1, infoMovie2) -> {
            int l = (int) (infoMovie1.getWatched() - infoMovie2.getWatched());
            return l;
        }).collect(Collectors.toList());
        return sortedInfoMovieVos;
    }

}