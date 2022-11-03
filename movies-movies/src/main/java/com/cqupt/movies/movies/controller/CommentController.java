package com.cqupt.movies.movies.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.movies.interceptor.MovieInterceptor;
import com.cqupt.movies.movies.to.UserInfoTo;
import com.cqupt.movies.movies.vo.CommentUpdateVo;
import com.cqupt.movies.movies.vo.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cqupt.movies.movies.entity.CommentEntity;
import com.cqupt.movies.movies.service.CommentService;



/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-11-01 20:01:07
 */
@RestController
@RequestMapping("movies/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;




    /**
     * 保存一条评论，  前端已经判断了用户是否登入了的，只有用户登录了才能评论，
     *
     * commentVo前端返回的数据
     */
    @RequestMapping("/save")
    public R save(@RequestBody CommentVo commentVo){
        //获得当前线程的用户
        UserInfoTo userInfoTo = MovieInterceptor.threadLocal.get();

        if (userInfoTo.getUserId()!=null) {
            CommentEntity commentEntity = new CommentEntity();
            BeanUtils.copyProperties(commentVo, commentEntity);  //拷贝
            commentEntity.setCreateTime(new Date());
            commentEntity.setUpdate(new Date());
            commentEntity.setMemberId(userInfoTo.getUserId());
            commentEntity.setPraseCount(0);
            commentService.save(commentEntity);
            return R.ok();
        }else {
            return R.error(1,"用户未登入，所以无法评论");
        }
    }


    /**
     * 修改评论内容，   必须是该评论的用户登入才行
     */
    @PostMapping("/update/content")
    public R updateContent(@RequestBody CommentUpdateVo commentUpdateVo){
        UserInfoTo userInfoTo = MovieInterceptor.threadLocal.get();
        if (userInfoTo.getUserId()!=null&&userInfoTo.getUserId()==commentUpdateVo.getMemberId()){  //只有登入了的用户才能,并且登入的用户必须是该评论的用户
            //还要区数据库查一遍是否有该评论
            CommentEntity commentDb = commentService.getById(commentUpdateVo.getId());
            if (commentDb!=null) { //数据库中存在
                CommentEntity commentEntity = new CommentEntity();
                BeanUtils.copyProperties(commentUpdateVo, commentEntity);
                commentEntity.setPraseCount(commentDb.getPraseCount());
                commentEntity.setUpdate(new Date());
                commentService.updateById(commentEntity);
                return R.ok();
            }else {
                //数据库中不存在，
                return R.error(1,"该评论不存在");
            }
        }else {
            return R.error(1,"用户未登入或不能修改他人评论");
        }
    }


    /**
     * 修改评论点赞数，   这个就没有限制了，不管用户是否登入都可以点赞，
     * */
    @PostMapping("/update/praise")
    public R updatePraise(@RequestBody CommentUpdateVo commentUpdateVo){
        R r=commentService.updateCommentPraise(commentUpdateVo);
        if (r.getCode()==1) {
            return R.ok();
        }else {
            return R.error(1,"点赞失败");
        }
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = commentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		CommentEntity comment = commentService.getById(id);

        return R.ok().put("comment", comment);
    }




    /**
     * 删除一条评论，   只有用户登入、并且是该评论用户才能删除
     *
     * 删除评论，并且把这个评论的所有回复也删除 （这里很重要，）
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody CommentVo commentVo){
        UserInfoTo userInfoTo = MovieInterceptor.threadLocal.get();
        if (userInfoTo.getUserId()!=null&&userInfoTo.getUserId()==commentVo.getMemberId()){
            //调用删除评论的方法
            R r=commentService.deleteCommentByCommentId(commentVo.getId());
            if (r.getCode()==0){
                return R.ok();
            }else {
                return R.error(1,"删除失败");
            }
        }else {
            return R.error(1,"删除失败");
        }
    }

}
