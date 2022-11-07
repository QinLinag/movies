package com.cqupt.movies.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.member.entity.ThumbMovieEntity;
import com.cqupt.movies.member.vo.InfoMovieVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cqupt.movies.member.entity.KeenMovieEntity;
import com.cqupt.movies.member.service.KeenMovieService;



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
     * 通过用户id，和电影id查询用户点赞该电影的信息
     * */
    @PostMapping("/keen/memberidandmovieid")
    public R selectKeenByMemberIdAndMovieId(@RequestBody InfoMovieVo infoMovieVo){
        KeenMovieEntity keenMovieEntity=keenMovieService.getByMemberIdAndMovieId(infoMovieVo);
        return R.ok().setData(keenMovieEntity);
    }



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
     * 通过用户id和电影id保存一条用户点赞信息，
     */
    @RequestMapping("/save")
    public R save(@RequestBody InfoMovieVo infoMovieVo){
        KeenMovieEntity keenMovieEntity = new KeenMovieEntity();
        keenMovieEntity.setMovieId(infoMovieVo.getMovieId());
        keenMovieEntity.setMemberId(infoMovieVo.getMemberId());

        keenMovieService.save(keenMovieEntity);

        return R.ok();
    }



    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody InfoMovieVo infoMovieVo){
        R r=keenMovieService.deleteByMemberIdAndMovieId(infoMovieVo);
        if (r.getCode()==0) {
            return R.ok();
        }else {
            return R.error(1,"删除失败");
        }
    }

}
