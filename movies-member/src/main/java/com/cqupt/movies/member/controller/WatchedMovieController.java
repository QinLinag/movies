package com.cqupt.movies.member.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cqupt.movies.member.entity.WatchedMovieEntity;
import com.cqupt.movies.member.service.WatchedMovieService;
import com.cqupt.common.utils.PageUtils;
import com.cqupt.common.utils.R;



/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-11-03 10:03:42
 */
@RestController
@RequestMapping("member/watchedmovie")
public class WatchedMovieController {
    @Autowired
    private WatchedMovieService watchedMovieService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:watchedmovie:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = watchedMovieService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:watchedmovie:info")
    public R info(@PathVariable("id") Long id){
		WatchedMovieEntity watchedMovie = watchedMovieService.getById(id);

        return R.ok().put("watchedMovie", watchedMovie);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:watchedmovie:save")
    public R save(@RequestBody WatchedMovieEntity watchedMovie){
		watchedMovieService.save(watchedMovie);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:watchedmovie:update")
    public R update(@RequestBody WatchedMovieEntity watchedMovie){
		watchedMovieService.updateById(watchedMovie);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:watchedmovie:delete")
    public R delete(@RequestBody Long[] ids){
		watchedMovieService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
