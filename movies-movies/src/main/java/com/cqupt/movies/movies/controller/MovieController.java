package com.cqupt.movies.movies.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cqupt.movies.movies.entity.MovieEntity;
import com.cqupt.movies.movies.service.MovieService;
import com.cqupt.common.utils.PageUtils;
import com.cqupt.common.utils.R;



/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-10-26 00:43:48
 */
@RestController
@RequestMapping("movies/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("movies:movie:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = movieService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{mid}")
    //@RequiresPermissions("movies:movie:info")
    public R info(@PathVariable("mid") Long mid){
		MovieEntity movie = movieService.getById(mid);

        return R.ok().put("movie", movie);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("movies:movie:save")
    public R save(@RequestBody MovieEntity movie){
		movieService.save(movie);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("movies:movie:update")
    public R update(@RequestBody MovieEntity movie){
		movieService.updateById(movie);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("movies:movie:delete")
    public R delete(@RequestBody Long[] mids){
		movieService.removeByIds(Arrays.asList(mids));

        return R.ok();
    }

}
