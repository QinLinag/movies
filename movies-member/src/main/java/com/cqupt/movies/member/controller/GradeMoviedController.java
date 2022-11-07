package com.cqupt.movies.member.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.member.controller.to.CreateMemberInfoTo;
import com.cqupt.movies.member.vo.InfoMovieVo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cqupt.movies.member.entity.GradeMoviedEntity;
import com.cqupt.movies.member.service.GradeMoviedService;



/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-11-06 14:49:44
 */
@RestController
@RequestMapping("member/grademovie")
public class GradeMoviedController {
    @Autowired
    private GradeMoviedService gradeMoviedService;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 查询用户对某电影的评分情况
     * */
    @PostMapping("/memberidandmovieid")
    public R selectGradeByMemberIdAndMovieId(@RequestBody InfoMovieVo infoMovieVo){
        GradeMoviedEntity gradeMoviedEntity=gradeMoviedService.getByMemberIdAndMovieId(infoMovieVo);

        if (gradeMoviedEntity!=null) {
            return R.ok().setData(gradeMoviedEntity);
        }else {
            return R.ok().setData(null);
        }
    }





    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:grademovied:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = gradeMoviedService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:grademovied:info")
    public R info(@PathVariable("id") Long id){
		GradeMoviedEntity gradeMovied = gradeMoviedService.getById(id);

        return R.ok().put("gradeMovied", gradeMovied);
    }

    /**
     * 保存用户对某一电影的评分情况
     *
     * 分布式事务，  用rabbimq实现，
     * */
    @RequestMapping("/save")
    //@RequiresPermissions("member:grademovied:save")
    public R save(@RequestBody InfoMovieVo infoMovieVo){

        GradeMoviedEntity gradeMoviedEntity = new GradeMoviedEntity();
        BeanUtils.copyProperties(infoMovieVo,gradeMoviedEntity);
        gradeMoviedService.saveGradeMovie(gradeMoviedEntity);

        CreateMemberInfoTo createMemberInfoTo = new CreateMemberInfoTo();   //用于rabbitmq传输对象，
        GradeMoviedEntity byMemberIdAndMovieId = gradeMoviedService.getByMemberIdAndMovieId(infoMovieVo);//为什么要再查一次，是因为主键id是数据库自动生成，我们不知道
        createMemberInfoTo.setMemberId(byMemberIdAndMovieId.getMemberId());
        createMemberInfoTo.setGrade(byMemberIdAndMovieId.getGrade());
        createMemberInfoTo.setMovieId(byMemberIdAndMovieId.getMovieId());

        createMemberInfoTo.setId(byMemberIdAndMovieId.getId());
        createMemberInfoTo.setStatusEntityId(infoMovieVo.getStatusEntityId());
        //发消息，  评分添加成功
        rabbitTemplate.convertAndSend("memberinfo-event-exchange","memberinfo.delay.queue",createMemberInfoTo);

        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:grademovied:delete")
    public R delete(@RequestBody Long[] ids){
		gradeMoviedService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
