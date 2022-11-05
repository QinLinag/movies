package com.cqupt.movies.movies.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cqupt.movies.common.constant.MovieConstant;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;

import com.cqupt.movies.movies.entity.InfoMovieEntity;
import com.cqupt.movies.movies.feign.CelebritiesFeignService;
import com.cqupt.movies.movies.interceptor.MovieInterceptor;
import com.cqupt.movies.movies.service.InfoMovieService;

import com.cqupt.movies.movies.vo.AllMoviesInfoVo;
import com.cqupt.movies.movies.vo.CelebrityVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import com.cqupt.movies.movies.entity.MovieEntity;
import com.cqupt.movies.movies.service.MovieService;



/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-10-26 00:43:48
 */
@RestController
@RequestMapping("movies/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private InfoMovieService infoMovieService;

    @Autowired
    private CelebritiesFeignService celebritiesFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 根据电影名字进行模糊查询，
     *
     * 在查询电影时，同时返回电影的其他信息
     * */
    @RequestMapping("/list/name")
    public R listByName(@RequestParam("name") String name) {
        List<MovieEntity> entities=movieService.listByName(name);

        if (entities!=null&&entities.size()>0){   //如果电影库中有这个电影的话
            List<AllMoviesInfoVo> allMoviesList = entities.stream().map(item -> {
                AllMoviesInfoVo allMoviesInfoVo = new AllMoviesInfoVo();
                BeanUtils.copyProperties(item, allMoviesInfoVo);  //复制信息，

                Long mid = item.getMid(); //电影id
                InfoMovieEntity infoMovieEntity = infoMovieService.getByMid(mid);  //查出来的每部电影的具体信息
                if (infoMovieEntity!=null) {  //如果有此电影的电影其他信息的话，
                    allMoviesInfoVo.setCollect(infoMovieEntity.getCollect());
                    allMoviesInfoVo.setKeen(infoMovieEntity.getKeen());
                    allMoviesInfoVo.setWatched(infoMovieEntity.getWatched());
                    return allMoviesInfoVo;
                }
                return allMoviesInfoVo;
            }).collect(Collectors.toList());
            return R.ok().put("data",allMoviesList);
        }
        return R.ok();
    }


    /**
     * 点击某一部电影时，查找出参演的演员明星，封装为一个list集合，
     *
     * 前端页面请求带来一个电影的id
     * */
    @RequestMapping("/celebrities/{mid}")
    public R listCelebrityByMovie(@PathVariable("mid") Long mid){
        MovieEntity byId = movieService.getById(mid);  //先找出电影
        if (byId!=null){
            String substring = byId.getCelebrities().substring(1, byId.getCelebrities().length() - 2);

            String[] celebIds = substring.split(", ");//具体每一个演员的id，  分割一下，
            List<String> strings = Arrays.stream(celebIds).collect(Collectors.toList());
            List<CelebrityVo> celebrityVos = strings.stream().map((s) -> {
                //远程调用
                R info = celebritiesFeignService.info(Long.valueOf(s));
                if (info != null) {
                    CelebrityVo celebrityEntity =info.getData("data",new TypeReference<CelebrityVo>(){});
                    return celebrityEntity;
                } else {
                    return null;
                }
            }).collect(Collectors.toList());

            return R.ok().put("data",celebrityVos);  //查找出所有参演明星返回
        }else {
            return R.error(1,"电影不存在");
        }
    }



    /**
     * 根据id批量查询电影
     *
     * 供其他服务远程调用使用，
     * */
    @RequestMapping("/list/byids")
    public R listByIds(@RequestParam("ids") List<Long> ids){
        List<MovieEntity> entities = movieService.listByIds(ids);
        return R.ok().setData(entities);
    }




    /**
     * 按照电影类型查找所有的电影， tag在数据库中是字符串，需要通过,分割所有的类型
     *
     * 我们将查找过的电影保存到redis中，然后，用于排序等操作，以防再重数据库中差查一次，
     * */
    @GetMapping("/list/tag")
    public R listByTags(@RequestParam List<Integer> tags){   //接收多个类型
       List<MovieEntity> entities=movieService.listByTags(tags);

       return R.ok().setData(entities);
    }

    /**
     * 最开始想前端返回tag字符串，但是都来想，这样不是很好，
     * 然后想前端传数字，然后我这里映射成string  所以就用/list/tag这个接口
     * */
    @GetMapping("/list/tagstring")
    public R listByTagsString(@RequestParam List<String > tags){   //接收多个类型
        List<MovieEntity> entities=movieService.listByTagsString(tags);

        return R.ok().setData(entities);
    }


    /**
     * 按照点赞数量升序排列查询    或者降序排序查询
     *
     * 排序的的前提是已经按照tags查找过电影，
     * */
    @GetMapping("/sort/thumbup/{flag}")   //标志位  0-dec   其他-inc
    public R sortByThumbUp(@PathVariable("flag") Integer flag){
        //已经查过的电影在redis中保存着的，
        String tagJsonMovies = redisTemplate.opsForValue().get(MovieInterceptor.threadLocal.get().getUserKey() + MovieConstant.TAG_NAME);
        System.out.println(tagJsonMovies);
        System.out.println(MovieInterceptor.threadLocal.get().getUserKey());
        List<MovieEntity> tagMoves = JSONObject.parseObject(tagJsonMovies, new TypeReference<List<MovieEntity>>() {
        });

        if (tagMoves!=null&&tagMoves.size()>0) {

            if (flag == 0) {  //降序
                List<MovieEntity> collect = tagMoves.stream().sorted((tagMovie1, tagMovie2) -> {
                    Long mid1 = tagMovie1.getMid();  //电影id
                    Long mid2 = tagMovie2.getMid();  //电影id
                    InfoMovieEntity byMid1 = infoMovieService.getByMid(mid1);
                    InfoMovieEntity byMid2 = infoMovieService.getByMid(mid2);
                    int sort =0;
                    if (byMid1!=null&&byMid2!=null) {
                        Long thumbUp1 = byMid1.getThumbUp();
                        Long thumbUp2 = byMid2.getThumbUp();
                        sort = (int) (thumbUp1 - thumbUp2);
                    }
                    return sort;
                }).collect(Collectors.toList());
                return R.ok().setData(collect);
            } else {   //升序
                List<MovieEntity> collect = tagMoves.stream().sorted((tagMovie1, tagMovie2) -> {
                    Long mid1 = tagMovie1.getMid();  //电影id
                    Long mid2 = tagMovie2.getMid();  //电影id
                    InfoMovieEntity byMid1 = infoMovieService.getByMid(mid1);
                    InfoMovieEntity byMid2 = infoMovieService.getByMid(mid1);
                    int sort =0;
                    if (byMid1!=null&&byMid2!=null) {
                        Long thumbUp1 = byMid1.getThumbUp();
                        Long thumbUp2 = byMid2.getThumbUp();
                        sort=(int) (thumbUp2 - thumbUp1);
                    }

                    return sort;
                }).collect(Collectors.toList());
                return R.ok().setData(collect);
            }
        }else {    //在redis中没有这个用户通过tag搜索过的电影
            return R.error(1,"没有电影");
        }
    }

    /**
     * 按照观看数量升序排列查询    或者降序排序查询
     *
     * 排序的的前提是已经按照tags查找过电影，
     * */

    @GetMapping("/sort/watched/{flag}")   //标志位  0-dec   其他-inc
    public R sortByWatched(@PathVariable("flag") Integer flag){
        //已经查过的电影在redis中保存着的，
        String tagJsonMovies = redisTemplate.opsForValue().get(MovieInterceptor.threadLocal.get().getUserKey() + MovieConstant.TAG_NAME);
        List<MovieEntity> tagMoves = JSONObject.parseObject(tagJsonMovies, new TypeReference<List<MovieEntity>>() {
        });

        if (tagMoves!=null&&tagMoves.size()>0) {

            if (flag == 0) {  //降序
                List<MovieEntity> collect = tagMoves.stream().sorted((tagMovie1, tagMovie2) -> {
                    Long mid1 = tagMovie1.getMid();  //电影id
                    Long mid2 = tagMovie2.getMid();  //电影id
                    InfoMovieEntity byMid1 = infoMovieService.getByMid(mid1);
                    InfoMovieEntity byMid2 = infoMovieService.getByMid(mid2);
                    int sort=0;
                    if (byMid1!=null&&byMid2!=null) {
                        Long watched1 = byMid1.getWatched();
                        Long watched2 = byMid2.getWatched();
                        sort = (int) (watched1 - watched2);
                    }

                    return sort;
                }).collect(Collectors.toList());
                return R.ok().setData(collect);
            } else {   //升序
                List<MovieEntity> collect = tagMoves.stream().sorted((tagMovie1, tagMovie2) -> {
                    Long mid1 = tagMovie1.getMid();  //电影id
                    Long mid2 = tagMovie2.getMid();  //电影id
                    InfoMovieEntity byMid1 = infoMovieService.getByMid(mid1);
                    InfoMovieEntity byMid2 = infoMovieService.getByMid(mid1);
                    int sort=0;
                    if (byMid1!=null&&byMid2!=null) {
                        Long watched1 = byMid1.getWatched();
                        Long watched2 = byMid2.getWatched();
                        sort = (int) (watched2 - watched1);
                    }
                    return sort;
                }).collect(Collectors.toList());
                return R.ok().setData(collect);
            }
        }else {    //在redis中没有这个用户通过tag搜索过的电影
            return R.error(1,"没有电影");
        }
    }



    /**
     * 推荐电影，没点一次就更新一次   存放在redis中，
     *
     * */





    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("movies:movie:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = movieService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{mid}")
    //@RequiresPermissions("movies:movie:info")
    public R info(@PathVariable("mid") Long mid){
		MovieEntity movie = movieService.getById(mid);

        return R.ok().put("movie", movie);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("movies:movie:save")
    public R save(@RequestBody MovieEntity movie){
		movieService.save(movie);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("movies:movie:update")
    public R update(@RequestBody MovieEntity movie){
		movieService.updateById(movie);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("movies:movie:delete")
    public R delete(@RequestBody Long[] mids){
		movieService.removeByIds(Arrays.asList(mids));

        return R.ok();
    }

}
