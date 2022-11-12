package com.cqupt.movies.member.controller;

import java.util.Map;

import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.member.vo.InfoMovieVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cqupt.movies.member.entity.BadMovieEntity;
import com.cqupt.movies.member.service.BadMovieService;




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
     * 通过用户id，和电影id查询用户差评该电影的信息
     * */
    @PostMapping("/bad/memberidandmovieid")
    public R selectBadByMemberIdAndMovieId(@RequestBody InfoMovieVo infoMovieVo){
        BadMovieEntity badMovieEntity=badMovieService.getByMemberIdAndMovieId(infoMovieVo);
        return R.ok().setData(badMovieEntity);
    }




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
     * 通过用户id和电影id保存一条用户差评信息，
     */
    @RequestMapping("/save")
    public R save(@RequestBody InfoMovieVo infoMovieVo){
        BadMovieEntity badMovieEntity = new BadMovieEntity();
        badMovieEntity.setMovieId(infoMovieVo.getMovieId());
        badMovieEntity.setMemberId(infoMovieVo.getMemberId());

        badMovieService.save(badMovieEntity);

        return R.ok();
    }



    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody InfoMovieVo infoMovieVo){
        R r=badMovieService.deleteByMemberIdAndMovieId(infoMovieVo);
        if (r.getCode()==0) {
            return R.ok();
        }else {
            return R.error(1,"删除失败");
        }
    }

}
