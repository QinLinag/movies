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

import com.cqupt.movies.member.entity.CollectMovieEntity;
import com.cqupt.movies.member.service.CollectMovieService;
import com.cqupt.common.utils.PageUtils;
import com.cqupt.common.utils.R;



/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-10-26 08:29:08
 */
@RestController
@RequestMapping("member/collectmovie")
public class CollectMovieController {
    @Autowired
    private CollectMovieService collectMovieService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:collectmovie:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = collectMovieService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:collectmovie:info")
    public R info(@PathVariable("id") Long id){
		CollectMovieEntity collectMovie = collectMovieService.getById(id);

        return R.ok().put("collectMovie", collectMovie);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:collectmovie:save")
    public R save(@RequestBody CollectMovieEntity collectMovie){
		collectMovieService.save(collectMovie);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:collectmovie:update")
    public R update(@RequestBody CollectMovieEntity collectMovie){
		collectMovieService.updateById(collectMovie);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:collectmovie:delete")
    public R delete(@RequestBody Long[] ids){
		collectMovieService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
