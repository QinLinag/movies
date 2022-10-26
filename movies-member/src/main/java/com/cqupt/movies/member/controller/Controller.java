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

import com.cqupt.movies.member.entity.Entity;
import com.cqupt.movies.member.service.Service;
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
@RequestMapping("member/")
public class Controller {
    @Autowired
    private Service Service;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member::list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = Service.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member::info")
    public R info(@PathVariable("id") Long id){
		Entity  = Service.getById(id);

        return R.ok().put("", );
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member::save")
    public R save(@RequestBody Entity ){
		Service.save();

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member::update")
    public R update(@RequestBody Entity ){
		Service.updateById();

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member::delete")
    public R delete(@RequestBody Long[] ids){
		Service.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
