package com.cqupt.movies.movies.controller;

import java.util.Arrays;
import java.util.Map;

import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.movies.interceptor.MovieInterceptor;
import com.cqupt.movies.movies.to.UserInfoTo;
import com.cqupt.movies.movies.vo.CommentReplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cqupt.movies.movies.entity.CommentReplyEntity;
import com.cqupt.movies.movies.service.CommentReplyService;



/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-11-01 20:01:07
 */
@RestController
@RequestMapping("movies/commentreply")
public class CommentReplyController {
    @Autowired
    private CommentReplyService commentReplyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = commentReplyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		CommentReplyEntity commentReply = commentReplyService.getById(id);

        return R.ok().put("commentReply", commentReply);
    }

    /**
     * 在某条评论下面保存一条回复，  同样必须登入了，才可以回复
     */
    @RequestMapping("/save")
    public R save(@RequestBody CommentReplyVo commentReplyVo){
        UserInfoTo userInfoTo = MovieInterceptor.threadLocal.get();
        if (userInfoTo.getUserId()!=null&&userInfoTo.getUserId()==commentReplyVo.getMemberId()){
            R r=commentReplyService.saveCommentReply(commentReplyVo);
            return R.ok();
        }else{
            return R.error(1,"请未登入，或者用户错误");
        }
    }



    /**
     *    点赞数增加，
     */
    @RequestMapping("/update")
    public R update(@RequestBody CommentReplyEntity commentReply){
		commentReplyService.updateById(commentReply);

        return R.ok();
    }

    /**
     * 删除一条在评论下面的回复，    必须是本人登入了才能删除，
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody CommentReplyVo commentReplyVo){
        UserInfoTo userInfoTo = MovieInterceptor.threadLocal.get();
        if (userInfoTo.getUserId()!=null&&userInfoTo.getUserId()==commentReplyVo.getMemberId()){
            R r=commentReplyService.removeCommentReply(commentReplyVo);
        }else {
            return R.error(1,"未登入或用户错误");
        }
        return R.ok();
    }

}
