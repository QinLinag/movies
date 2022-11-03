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

import com.cqupt.movies.member.entity.BadMovieEntity;
import com.cqupt.movies.member.service.BadMovieService;
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
@RequestMapping("member/badmovie")
public class BadMovieController {
    @Autowired
    private BadMovieService badMovieService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:badmovie:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = badMovieService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:badmovie:info")
    public R info(@PathVariable("id") Long id){
		BadMovieEntity badMovie = badMovieService.getById(id);

        return R.ok().put("badMovie", badMovie);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:badmovie:save")
    public R save(@RequestBody BadMovieEntity badMovie){
		badMovieService.save(badMovie);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:badmovie:update")
    public R update(@RequestBody BadMovieEntity badMovie){
		badMovieService.updateById(badMovie);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:badmovie:delete")
    public R delete(@RequestBody Long[] ids){
		badMovieService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
