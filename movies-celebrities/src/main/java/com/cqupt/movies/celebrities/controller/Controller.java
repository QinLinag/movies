package com.cqupt.movies.celebrities.controller;

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

import com.cqupt.movies.celebrities.entity.Entity;
import com.cqupt.movies.celebrities.service.Service;




/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-10-26 08:28:21
 */
@RestController
@RequestMapping("celebrities/")
public class Controller {
    @Autowired
    private Service Service;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("celebrities::list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = Service.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("celebrities::info")
    public R info(@PathVariable("id") Long id){
		Entity  = Service.getById(id);

        return R.ok().put("data", Entity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("celebrities::save")
    public R save(@RequestBody Entity ){
		Service.save();

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("celebrities::update")
    public R update(@RequestBody Entity ){
		Service.updateById();

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("celebrities::delete")
    public R delete(@RequestBody Long[] ids){
		Service.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
