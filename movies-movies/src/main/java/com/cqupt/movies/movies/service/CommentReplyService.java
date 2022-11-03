package com.cqupt.movies.movies.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.movies.entity.CommentReplyEntity;
import com.cqupt.movies.movies.vo.CommentReplyVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-11-01 20:01:07
 */
public interface CommentReplyService extends IService<CommentReplyEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 按照评论的id查出所有关于该评论的回复，
     * */
    List<CommentReplyEntity> listByCommentId(Long id);


    /**
     * 保存一条回复，
     * */
    R saveCommentReply(CommentReplyVo commentReplyVo);

    /**
     * 删除一条评论
     * */
    R removeCommentReply(CommentReplyVo commentReplyVo);
}

