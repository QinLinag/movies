package com.cqupt.movies.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.member.entity.ThumbMovieEntity;
import com.cqupt.movies.member.vo.InfoMovieVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cqupt.movies.member.entity.WatchedMovieEntity;
import com.cqupt.movies.member.service.WatchedMovieService;



/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-11-03 10:03:42
 */
@RestController
@RequestMapping("member/watchedmovie")
public class WatchedMovieController {
    @Autowired
    private WatchedMovieService watchedMovieService;


    /**
     * 通过用户id，和电影id查询用户点赞该电影的信息
     * */
    @GetMapping("/watched/memberidandmovieid")
    public R selectWatchedByMemberIdAndMovieId(@RequestParam("infoMovieVo") InfoMovieVo infoMovieVo){
        WatchedMovieEntity watchMovieEntity=watchedMovieService.getByMemberIdAndMovieId(infoMovieVo);
        return R.ok().setData(watchMovieEntity);
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:watchedmovie:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = watchedMovieService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:watchedmovie:info")
    public R info(@PathVariable("id") Long id){
		WatchedMovieEntity watchedMovie = watchedMovieService.getById(id);

        return R.ok().put("watchedMovie", watchedMovie);
    }

    /**
     * 通过用户id和电影id保存一条用户点赞信息，
     */
    @RequestMapping("/save")
    public R save(@RequestParam("infoMovieVo") InfoMovieVo infoMovieVo){
        WatchedMovieEntity watchedMovieEntity = new WatchedMovieEntity();
        watchedMovieEntity.setMovieId(infoMovieVo.getMovieId());
        watchedMovieEntity.setMemberId(infoMovieVo.getMemberId());

        watchedMovieService.save(watchedMovieEntity);

        return R.ok();
    }


    /**
     * 删除   用户id电影id
     */
    @RequestMapping("/delete")
    public R delete(@RequestParam("infoMovieVo") InfoMovieVo infoMovieVo){
        R r=watchedMovieService.deleteByMemberIdAndMovieId(infoMovieVo);
        if (r.getCode()==0) {
            return R.ok();
        }else {
            return R.error(1,"删除失败");
        }
    }


}
