package com.cqupt.movies.member.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cqupt.movies.member.entity.ThumbMovieEntity;
import com.cqupt.movies.member.service.ThumbMovieService;



/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-10-26 08:29:08
 */
@RestController
@RequestMapping("member/thumbmovie")
public class ThumbMovieController {
    @Autowired
    private ThumbMovieService thumbMovieService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:thumbmovie:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thumbMovieService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:thumbmovie:info")
    public R info(@PathVariable("id") Long id){
		ThumbMovieEntity thumbMovie = thumbMovieService.getById(id);

        return R.ok().put("thumbMovie", thumbMovie);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:thumbmovie:save")
    public R save(@RequestBody ThumbMovieEntity thumbMovie){
		thumbMovieService.save(thumbMovie);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:thumbmovie:update")
    public R update(@RequestBody ThumbMovieEntity thumbMovie){
		thumbMovieService.updateById(thumbMovie);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:thumbmovie:delete")
    public R delete(@RequestBody Long[] ids){
		thumbMovieService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
