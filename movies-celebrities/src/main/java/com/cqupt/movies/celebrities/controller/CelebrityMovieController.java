package com.cqupt.movies.celebrities.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cqupt.movies.celebrities.entity.CelebrityMovieEntity;
import com.cqupt.movies.celebrities.service.CelebrityMovieService;
import com.cqupt.common.utils.PageUtils;
import com.cqupt.common.utils.R;



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
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("celebrities:celebritymovie:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = celebrityMovieService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("celebrities:celebritymovie:info")
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
