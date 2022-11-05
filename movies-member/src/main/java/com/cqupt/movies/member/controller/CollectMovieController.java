package com.cqupt.movies.member.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.alibaba.fastjson.TypeReference;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.member.feign.MovieSFeignService;
import com.cqupt.movies.member.vo.MovieVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cqupt.movies.member.entity.CollectMovieEntity;
import com.cqupt.movies.member.service.CollectMovieService;


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

    @Autowired
    private MovieSFeignService movieSFeignService;


    /**
     * 查询用户的收藏的所有电影，
     */
    @RequestMapping("/movies/collect/{memberId}")
    private R listCollectMoviesByMemberId(@PathVariable("memberId") Long memberId) {

        //查询出用户id和收藏电影的实例
        List<CollectMovieEntity> entities = collectMovieService.getCollectMovieEntityByMemberId(memberId);
        if (entities != null && entities.size() > 0) {
            //通过电影的id远程调用movies服务查询电影
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
                    return R.error(1, "远程调用movies服务失败");
                }
            } catch (Exception e) {
                return R.error(1, "查询电影时出现异常了");
            }

        } else {
            return R.ok().put("data", null);
        }

    }






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


}
