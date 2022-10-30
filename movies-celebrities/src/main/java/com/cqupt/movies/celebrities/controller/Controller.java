package com.cqupt.movies.celebrities.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cqupt.movies.celebrities.entity.Entity;
import com.cqupt.movies.celebrities.service.CelebritiesService;




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
    private CelebritiesService CelebritiesService;





    /**
     * 根据名星姓名进行模糊查询，
     *
     * */
    @RequestMapping("/list/{name}")
    public R listByName(@PathVariable("name") String name){
        List<Entity> entity=CelebritiesService.listByName(name);
        return R.ok().put("data",entity);
    }



    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("celebrities::list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = CelebritiesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("celebrities::info")
    public R info(@PathVariable("id") Long id){
        Entity byId = CelebritiesService.getById(id);

        return R.ok().setData( byId);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("celebrities::save")
    public R save(@RequestBody Entity entity){
		CelebritiesService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("celebrities::update")
    public R update(@RequestBody Entity entity){
		CelebritiesService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("celebrities::delete")
    public R delete(@RequestBody Long[] ids){
		CelebritiesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
