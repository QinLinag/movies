package com.cqupt.movies.celebrities.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import com.cqupt.movies.celebrities.feign.MoviesFeignService;
import com.cqupt.movies.celebrities.vo.CelebrityInfoVo;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import org.springframework.beans.BeanUtils;
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
    private CelebritiesService celebritiesService;




    /**
     * 根据名星姓名进行模糊查询，
     *
     * */
    @RequestMapping("/list/{name}")
    public R listByName(@PathVariable("name") String name){
        //模糊查询所有明星的基本信息
        List<Entity> entities=celebritiesService.listByName(name);

        //查询明星出演电影部数    其实明星参演电影多少部可以保存一个字段在明星表中，但是懒得改了，
        List<CelebrityInfoVo> allCelebrity = entities.stream().map(entity -> {
            CelebrityInfoVo celebrityInfoVo = new CelebrityInfoVo();
            BeanUtils.copyProperties(entity, celebrityInfoVo);  //拷贝数据到返回前端的对象里，
            Long id = entity.getId();
            Long count = celebritiesService.countMoviesByCelebId(id);
            celebrityInfoVo.setMoviesCount(count);
            return celebrityInfoVo;
        }).collect(Collectors.toList());

        return R.ok().put("data",allCelebrity);
    }






    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = celebritiesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("celebrities::info")
    public R info(@PathVariable("id") Long id){
        Entity byId = celebritiesService.getById(id);

        return R.ok().setData( byId);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("celebrities::save")
    public R save(@RequestBody Entity entity){
		celebritiesService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("celebrities::update")
    public R update(@RequestBody Entity entity){
		celebritiesService.updateById(entity);

        return R.ok();
    }

}
