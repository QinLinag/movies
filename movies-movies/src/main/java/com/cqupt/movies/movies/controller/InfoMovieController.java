package com.cqupt.movies.movies.controller;

import java.util.Arrays;
import java.util.Map;

import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cqupt.movies.movies.entity.InfoMovieEntity;
import com.cqupt.movies.movies.service.InfoMovieService;



/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-10-26 00:43:48
 */
@RestController
@RequestMapping("movies/infomovie")
public class InfoMovieController {
    @Autowired
    private InfoMovieService infoMovieService;


    /**
     * 给电影点赞  点击已看   点击想看
     * */
    @RequestMapping("/thumbup")
    public R thumbUp(@RequestParam("mid") Long mid){
        InfoMovieEntity entity=infoMovieService.getByMid(mid);
        if (entity!=null){
            entity.setThumbUp(entity.getThumbUp()+1);
            infoMovieService.updateById(entity);
            return R.ok();
        }else{
            return R.error(2,"电影不存在");
        }
    }

    @RequestMapping("/watched")
    public R watched(@RequestParam("mid") Long mid){
        InfoMovieEntity entity=infoMovieService.getByMid(mid);
        if (entity!=null){
            entity.setWatched(entity.getWatched()+1);
            infoMovieService.updateById(entity);
            return R.ok();
        }else{
            return R.error(2,"电影不存在");
        }
    }

    @RequestMapping("/keen")
    public R keen(@RequestParam("mid") Long mid){
        InfoMovieEntity entity=infoMovieService.getByMid(mid);
        if (entity!=null){
            entity.setKeen(entity.getKeen()+1);
            infoMovieService.updateById(entity);
            return R.ok();
        }else{
            return R.error(2,"电影不存在");
        }
    }



    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("movies:infomovie:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = infoMovieService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("movies:infomovie:info")
    public R info(@PathVariable("id") Long id){
		InfoMovieEntity infoMovie = infoMovieService.getById(id);

        return R.ok().put("infoMovie", infoMovie);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("movies:infomovie:save")
    public R save(@RequestBody InfoMovieEntity infoMovie){
		infoMovieService.save(infoMovie);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("movies:infomovie:update")
    public R update(@RequestBody InfoMovieEntity infoMovie){
		infoMovieService.updateById(infoMovie);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("movies:infomovie:delete")
    public R delete(@RequestBody Long[] ids){
		infoMovieService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
