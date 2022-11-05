package com.cqupt.movies.celebrities.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.cqupt.movies.celebrities.exception.CelebrityMovieException;
import com.cqupt.movies.celebrities.vo.MoviesVo;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cqupt.movies.celebrities.entity.CelebrityMovieEntity;
import com.cqupt.movies.celebrities.service.CelebrityMovieService;



/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-10-26 08:28:21
 */
@RestController
@RequestMapping("celebrities/celebritymovie")
public class CelebrityMovieController {
    @Autowired
    private CelebrityMovieService celebrityMovieService;


    /**
     * 点击某个明星参演电影,查询出这些电影，
     * 页面请求过来一个明星id，
     * */

    @RequestMapping("/movies")
    public R listMoviesByCelebId(@RequestParam("celebId") Long celebId){

        try {
                                                                //listMoviesByCelebId,这个函数中有远程调用movies这个微服务
            List<MoviesVo> moviesEntities=celebrityMovieService.listMoviesByCelebId(celebId);
            return R.ok().setData(moviesEntities);
        }catch (CelebrityMovieException e){
            return R.error(1,"远程调用出错了");
        }

    }


    /**
     *
     * 查询出明星参演过的电影，按照点赞次数选择出front个点赞最多的几个电影，           后来想了一下，不如给指定的明星参演的电影排序，按照点赞次数，哎~
     * */
    @GetMapping("/movies/thumb")
    public R listMostThumbMovieByCelebId(@RequestParam("celebId") Long celebId,@RequestParam("front") Long front){
        List<MoviesVo> mostThumbMovies=celebrityMovieService.listMostThumbMovieByCelebId(celebId,front);
        if (mostThumbMovies==null){  //远程调用失败
            return R.error(1,"远程调用失败");
        }
        return R.ok().setData(mostThumbMovies);
    }

    /**
     * 选出明星产演过，看过次数最多的电影
     * */
    @GetMapping("/movies/watched")
    public R listMostWatchedMovieByCelebId(@RequestParam("celebId") Long celebId,@RequestParam("front") Long front){
        List<MoviesVo> mostThumbMovies=celebrityMovieService.listMostWatchedMovieByCelebId(celebId,front);
        if (mostThumbMovies==null){  //远程调用失败
            return R.error(1,"远程调用失败");
        }
        return R.ok().setData(mostThumbMovies);
    }

    /**
     * 选出明星产演过，想看次数最多的电影
     * */
    @GetMapping("/movies/keen")
    public R listMostKeenMovieByCelebId(@RequestParam("celebId") Long celebId,@RequestParam("front") Long front){
        List<MoviesVo> mostThumbMovies=celebrityMovieService.listMostKeenMovieByCelebId(celebId,front);
        if (mostThumbMovies==null){  //远程调用失败
            return R.error(1,"远程调用失败");
        }
        return R.ok().setData(mostThumbMovies);
    }











    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = celebrityMovieService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		CelebrityMovieEntity celebrityMovie = celebrityMovieService.getById(id);

        return R.ok().put("celebrityMovie", celebrityMovie);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("celebrities:celebritymovie:save")
    public R save(@RequestBody CelebrityMovieEntity celebrityMovie){
		celebrityMovieService.save(celebrityMovie);

        return R.ok();
    }


}
