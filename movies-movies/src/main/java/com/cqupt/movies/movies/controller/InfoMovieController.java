package com.cqupt.movies.movies.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.TypeReference;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.movies.entity.InfoMovieStatusEntity;
import com.cqupt.movies.movies.feign.MemberFeignService;
import com.cqupt.movies.movies.interceptor.MovieInterceptor;
import com.cqupt.movies.movies.service.InfoMovieStatusService;
import com.cqupt.movies.movies.to.UserInfoTo;
import com.cqupt.movies.movies.vo.GradeMovieInfoVo;
import com.cqupt.movies.movies.vo.InfoMovieVo;
import com.cqupt.movies.movies.vo.MovieInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private MemberFeignService memberFeignService;

    @Autowired
    private InfoMovieStatusService infoMovieStatusService;




    /**
     * 评分， 只有用户登入了才能评分，   如果用户早已评过，那么就无法再次评分  1-10分，
     * */
    @Transactional
    @PostMapping("/grade")
    public R grade(@RequestBody InfoMovieVo infoMovieVo){
        //判断用户是否登入
        UserInfoTo userInfoTo = MovieInterceptor.threadLocal.get();
        //if (userInfoTo.getUserId()!=null&&userInfoTo.getUserId()==infoMovieVo.getMemberId()){   //该用户登入，
            //远程调用member服务，查询用户是否已经评分过了，
        System.out.println(infoMovieVo.getMemberId()+"  "+ infoMovieVo.getMovieId());
            R r = memberFeignService.selectGradeByMemberIdAndMovieId(infoMovieVo);
            if (r.getData("data",new TypeReference<GradeMovieInfoVo>(){})==null){   //未评过分

                InfoMovieStatusEntity infoMovieStatusEntity = new InfoMovieStatusEntity();
                infoMovieStatusEntity.setMovieId(infoMovieVo.getMovieId());
                infoMovieStatusEntity.setMemberId(infoMovieVo.getMemberId());
                infoMovieStatusEntity.setStatus(0L);    //未完成，
                infoMovieStatusService.save(infoMovieStatusEntity);  //保存到数据库，，自动生成id主键

                InfoMovieStatusEntity infoMovieStatusEntity1=infoMovieStatusService.getByMemberIdAndMovieId(infoMovieVo);
                infoMovieVo.setStatusEntityId(infoMovieStatusEntity1.getId());

                memberFeignService.saveGrade(infoMovieVo);
                //改变电影的评分，
                infoMovieService.updateInfoMovie(infoMovieVo);
                infoMovieStatusEntity1.setStatus(1L);   //当电影的评分修改成功后，就将状态设置为已完成
                infoMovieStatusService.updateById(infoMovieStatusEntity1);

                return R.ok();
            }else {   //已经评过分，无法在评分
                return R.error(2,"该用户已经评过分");
            }
        //}else {  //该用户未登入
          //  return R.error(1,"用户登入信息错误");
        //}
    }



    /**
     * 电影点赞  点击已看   点击想看    给差评
     *但是如果用户已经点赞了，再次点赞那么就是取消电赞，，已看、想看、差评也是一样，
     * 所以每次我们先查一下这个表，判断用户是否已经点赞完成，
     *
     * 点赞这些，我们也设定只有登入了才能够进行
     * */
    @Transactional
    @PostMapping("/thumbup")
    public R thumbUp(@RequestBody InfoMovieVo infoMovieVo){
        UserInfoTo userInfoTo = MovieInterceptor.threadLocal.get();
        if (userInfoTo.getUserId()!=null&&userInfoTo.getUserId()==infoMovieVo.getMemberId()) {
            //先查看该用户是否已经点赞该电影，如果已经点赞就取消电赞，
            //远程调用Member服务，查询
            R r = memberFeignService.selectThumbByMemberIdAndMovieId(infoMovieVo);
            if (r.getData("data",new TypeReference<MovieInfo>(){})==null){   //这里说明用户没有给该电影点过赞
                InfoMovieEntity entity = infoMovieService.getByMid(infoMovieVo.getMovieId());
                if (entity != null) {
                    entity.setThumbUp(entity.getThumbUp() + 1);
                    infoMovieService.updateById(entity);
                    //同时还要将点赞信息添加到用户信息中，
                    memberFeignService.saveThumbInfo(infoMovieVo);
                    return R.ok();
                } else {
                    return R.error(2, "电影不存在");
                }
            }else {   //已经给该电影点过赞，现在应该是取消电赞，
                //删除用户信息保存的点赞该电影信息，   并且将该电影点赞次数减一
                R delete = memberFeignService.deleteThumbInfo(infoMovieVo);
                if (delete.getCode()==0){
                    InfoMovieEntity entity = infoMovieService.getByMid(infoMovieVo.getMovieId());
                    entity.setThumbUp(entity.getThumbUp()-1);
                    infoMovieService.updateById(entity);
                    return R.ok();
                }else {
                    return R.error(1,"取消点赞失败");
                }
            }

        }else {
            return R.error(1,"用户未登入或者登入错误");
        }
    }

    @Transactional
    @RequestMapping("/watched")
    public R watched(@RequestBody InfoMovieVo infoMovieVo){
        UserInfoTo userInfoTo = MovieInterceptor.threadLocal.get();
        if (userInfoTo.getUserId()!=null&&userInfoTo.getUserId()==infoMovieVo.getMemberId()) {
            //查询用户已看该电影信息
            R r = memberFeignService.selectWatchedByMemberIdAndMovieId(infoMovieVo);
            if (r.getData("data",new TypeReference<MovieInfo>(){})==null) {     //还未点击已看
                InfoMovieEntity entity = infoMovieService.getByMid(infoMovieVo.getMovieId());
                if (entity != null) {
                    entity.setWatched(entity.getWatched() + 1);
                    infoMovieService.updateById(entity);
                    //保存到用户的电影已看信息中，
                    memberFeignService.saveWatchedInfo(infoMovieVo);
                    return R.ok();
                } else {
                    return R.error(2, "电影不存在");
                }
            }else {    //已经点击过已看，应该取消
                memberFeignService.deleteWatchedInfo(infoMovieVo);
                //已看电影数减一
                InfoMovieEntity entity = infoMovieService.getByMid(infoMovieVo.getMovieId());
                entity.setWatched(entity.getWatched());
                infoMovieService.updateById(entity);
                return R.ok();
            }
        }else {
            return R.error(1,"未登录或登录错误");
        }
    }

    @Transactional
    @PostMapping("/keen")
    public R keen(@RequestBody InfoMovieVo infoMovieVo){
        UserInfoTo userInfoTo = MovieInterceptor.threadLocal.get();
        if (userInfoTo.getUserId()!=null&&userInfoTo.getUserId()==infoMovieVo.getMemberId()) {
            //查询用户想看该电影信息
            R r = memberFeignService.selectKeenByMemberIdAndMovieId(infoMovieVo);
            if (r.getData("data",new TypeReference<MovieInfo>(){})==null) {     //还未点击已看
                InfoMovieEntity entity = infoMovieService.getByMid(infoMovieVo.getMovieId());
                if (entity != null) {
                    entity.setKeen(entity.getKeen() + 1);
                    infoMovieService.updateById(entity);
                    //保存到用户的电影已看信息中，
                    memberFeignService.saveKeenInfo(infoMovieVo);
                    return R.ok();
                } else {
                    return R.error(2, "电影不存在");
                }
            }else {    //已经点击过已看，应该取消
                memberFeignService.deleteKeenInfo(infoMovieVo);
                //已看电影数减一
                InfoMovieEntity entity = infoMovieService.getByMid(infoMovieVo.getMovieId());
                entity.setKeen(entity.getKeen());
                infoMovieService.updateById(entity);
                return R.ok();
            }
        }else {
            return R.error(1,"未登录或登录错误");
        }
    }

    @Transactional
    @RequestMapping("/bad")
    public R bad(@RequestBody InfoMovieVo infoMovieVo){
        UserInfoTo userInfoTo = MovieInterceptor.threadLocal.get();
        if (userInfoTo.getUserId()!=null&&userInfoTo.getUserId()==infoMovieVo.getMemberId()) {
            //查询用户想看该电影信息
            R r = memberFeignService.selectBadByMemberIdAndMovieId(infoMovieVo);
            if (r.getData("data",new TypeReference<MovieInfo>(){})==null) {     //还未点击已看
                InfoMovieEntity entity = infoMovieService.getByMid(infoMovieVo.getMovieId());
                if (entity != null) {
                    entity.setBad(entity.getBad() + 1);
                    infoMovieService.updateById(entity);
                    //保存到用户的电影已看信息中，
                    memberFeignService.saveBadInfo(infoMovieVo);
                    return R.ok();
                } else {
                    return R.error(2, "电影不存在");
                }
            }else {    //已经点击过已看，应该取消
                memberFeignService.deleteBadInfo(infoMovieVo);
                //已看电影数减一
                InfoMovieEntity entity = infoMovieService.getByMid(infoMovieVo.getMovieId());
                entity.setBad(entity.getBad());
                infoMovieService.updateById(entity);
                return R.ok();
            }
        }else {
            return R.error(1,"未登录或登录错误");
        }
    }


    /**
     * 通过电影id集合，查询出这些电影对应的信息
     * */
    @GetMapping("/list/infomovies/bymovieids")
    public R listByMovieIds(@RequestParam("movieIds") List<Long> movieIds){
        List<InfoMovieEntity> infoMovieEntities=infoMovieService.listByMovieIds(movieIds);
        return R.ok().setData(infoMovieEntities);
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
