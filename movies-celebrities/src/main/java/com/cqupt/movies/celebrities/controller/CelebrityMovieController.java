package com.cqupt.movies.celebrities.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.TypeReference;
import com.cqupt.movies.celebrities.feign.MoviesFeignService;
import com.cqupt.movies.celebrities.vo.MoviesVo;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private MoviesFeignService moviesFeignService;


    /**
     * 点击某个明星参演电影,查询出这些电影，
     * 页面请求过来一个明星id，
     * */

    @RequestMapping("/movies")
    public R listMoviesByCelebId(@RequestParam("celebId") Long celebId){
        List<CelebrityMovieEntity> celebrityMovieEntities=celebrityMovieService.listByCelebId(celebId);

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
                    return R.ok().put("data", moviesEntities);
                } else {
                    return R.error(1, "远程调用时有异常");
                }
            }catch (Exception e){
                return R.error(1,"查询电影时有异常");
            }
        }else {
            return R.ok().put("data",null);
        }
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

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("celebrities:celebritymovie:update")
    public R update(@RequestBody CelebrityMovieEntity celebrityMovie){
		celebrityMovieService.updateById(celebrityMovie);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("celebrities:celebritymovie:delete")
    public R delete(@RequestBody Long[] ids){
		celebrityMovieService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
