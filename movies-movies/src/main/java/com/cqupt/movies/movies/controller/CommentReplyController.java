package com.cqupt.movies.movies.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cqupt.movies.movies.entity.CommentReplyEntity;
import com.cqupt.movies.movies.service.CommentReplyService;
import com.cqupt.common.utils.PageUtils;
import com.cqupt.common.utils.R;



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
    //@RequiresPermissions("movies:commentreply:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = commentReplyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("movies:commentreply:info")
    public R info(@PathVariable("id") Long id){
		CommentReplyEntity commentReply = commentReplyService.getById(id);

        return R.ok().put("commentReply", commentReply);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("movies:commentreply:save")
    public R save(@RequestBody CommentReplyEntity commentReply){
		commentReplyService.save(commentReply);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("movies:commentreply:update")
    public R update(@RequestBody CommentReplyEntity commentReply){
		commentReplyService.updateById(commentReply);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("movies:commentreply:delete")
    public R delete(@RequestBody Long[] ids){
		commentReplyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
