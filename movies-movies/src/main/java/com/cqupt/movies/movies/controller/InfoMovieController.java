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

import com.cqupt.movies.movies.entity.InfoMovieEntity;
import com.cqupt.movies.movies.service.InfoMovieService;
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
@RequestMapping("movies/infomovie")
public class InfoMovieController {
    @Autowired
    private InfoMovieService infoMovieService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("movies:infomovie:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = infoMovieService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("movies:infomovie:info")
    public R info(@PathVariable("id") Long id){
		InfoMovieEntity infoMovie = infoMovieService.getById(id);

        return R.ok().put("infoMovie", infoMovie);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("movies:infomovie:save")
    public R save(@RequestBody InfoMovieEntity infoMovie){
		infoMovieService.save(infoMovie);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("movies:infomovie:update")
    public R update(@RequestBody InfoMovieEntity infoMovie){
		infoMovieService.updateById(infoMovie);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("movies:infomovie:delete")
    public R delete(@RequestBody Long[] ids){
		infoMovieService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
