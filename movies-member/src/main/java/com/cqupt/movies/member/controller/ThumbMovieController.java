package com.cqupt.movies.member.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.alibaba.fastjson.TypeReference;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.member.entity.CollectMovieEntity;
import com.cqupt.movies.member.feign.MovieSFeignService;
import com.cqupt.movies.member.vo.InfoMovieVo;
import com.cqupt.movies.member.vo.MovieVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @Autowired
    private MovieSFeignService movieSFeignService;

    /**
     * 查询用户点赞的电影
     */
    @RequestMapping("/movies/thumb/{memberId}")
    private R listThumbMoviesByMemberId(@PathVariable("memberId") Long memberId){
        //查询出收藏电影的实例
        List<ThumbMovieEntity> entities = thumbMovieService.getThumbMovieEntityByMemberId(memberId);
        if (entities != null && entities.size() > 0) {
            List<Long> movieIds = entities.stream().map(entity -> {
                return entity.getMovieId();
            }).collect(Collectors.toList());
            try {
                R r = movieSFeignService.listByIds(movieIds);
                if (r.getCode() == 0) {
                    List<MovieVo> movieVos = r.getData("data", new TypeReference<List<MovieVo>>() {
                    });
                    return R.ok().put("data", movieVos);
                } else {
                    return R.error(1, "远程调用movies失败");
                }
            } catch (Exception e) {
                return R.error(1, "查询电影出现异常");
            }
        } else {
            return R.ok().put("data", null);
        }
    }


    /**
     * 通过用户id，和电影id查询用户点赞该电影的信息
     *
     * 给远程调用的movies服务使用，
     * */
    @GetMapping("/thumb/memberidandmovieid")
    public R selectThumbByMemberIdAndMovieId(@RequestParam("infoMovieVo")InfoMovieVo infoMovieVo){
        ThumbMovieEntity thumbMovieEntity=thumbMovieService.getByMemberIdAndMovieId(infoMovieVo);
        return R.ok().setData(thumbMovieEntity);
    }

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
     * 通过用户id和电影id保存一条用户点赞信息，
     *
     * 给movies服务远程调用使用
     */
    @RequestMapping("/save")
    public R save(@RequestParam("infoMovieVo") InfoMovieVo infoMovieVo){
        ThumbMovieEntity thumbMovieEntity = new ThumbMovieEntity();
        thumbMovieEntity.setMovieId(infoMovieVo.getMovieId());
        thumbMovieEntity.setMemberId(infoMovieVo.getMemberId());

        thumbMovieService.save(thumbMovieEntity);

        return R.ok();
    }



    /**
     * 删除
     * 给movies服务远程调用
     */
    @RequestMapping("/delete")
    public R delete(@RequestParam("infoMovieVo") InfoMovieVo infoMovieVo){
        R r=thumbMovieService.deleteByMemberIdAndMovieId(infoMovieVo);
        if (r.getCode()==0) {
            return R.ok();
        }else {
            return R.error(1,"删除失败");
        }
    }

}
