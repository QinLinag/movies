package com.cqupt.movies.movies.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cqupt.movies.movies.entity.InfoMovieStatusEntity;
import com.cqupt.movies.movies.service.InfoMovieStatusService;



/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-11-07 14:26:53
 */
@RestController
@RequestMapping("movies/infomoviestatus")
public class InfoMovieStatusController {
    @Autowired
    private InfoMovieStatusService infoMovieStatusService;

    @PostMapping("/list/statusid")
    public R getById(@RequestParam("statusid") Long statusid){
        InfoMovieStatusEntity byId = infoMovieStatusService.getById(statusid);
        System.out.println("++++++++++++++++++++++++++++");
        return R.ok().setData(byId);
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("movies:infomoviestatus:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = infoMovieStatusService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("movies:infomoviestatus:info")
    public R info(@PathVariable("id") Long id){
		InfoMovieStatusEntity infoMovieStatus = infoMovieStatusService.getById(id);

        return R.ok().put("infoMovieStatus", infoMovieStatus);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("movies:infomoviestatus:save")
    public R save(@RequestBody InfoMovieStatusEntity infoMovieStatus){
		infoMovieStatusService.save(infoMovieStatus);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("movies:infomoviestatus:update")
    public R update(@RequestBody InfoMovieStatusEntity infoMovieStatus){
		infoMovieStatusService.updateById(infoMovieStatus);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("movies:infomoviestatus:delete")
    public R delete(@RequestBody Long[] ids){
		infoMovieStatusService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
