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

import com.cqupt.movies.member.entity.KeenMovieEntity;
import com.cqupt.movies.member.service.KeenMovieService;
import com.cqupt.common.utils.PageUtils;
import com.cqupt.common.utils.R;



/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-11-03 10:03:41
 */
@RestController
@RequestMapping("member/keenmovie")
public class KeenMovieController {
    @Autowired
    private KeenMovieService keenMovieService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:keenmovie:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = keenMovieService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:keenmovie:info")
    public R info(@PathVariable("id") Long id){
		KeenMovieEntity keenMovie = keenMovieService.getById(id);

        return R.ok().put("keenMovie", keenMovie);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:keenmovie:save")
    public R save(@RequestBody KeenMovieEntity keenMovie){
		keenMovieService.save(keenMovie);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:keenmovie:update")
    public R update(@RequestBody KeenMovieEntity keenMovie){
		keenMovieService.updateById(keenMovie);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:keenmovie:delete")
    public R delete(@RequestBody Long[] ids){
		keenMovieService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
