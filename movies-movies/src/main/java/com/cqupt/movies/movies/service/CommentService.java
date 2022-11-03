package com.cqupt.movies.movies.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.movies.entity.CommentEntity;
import com.cqupt.movies.movies.vo.CommentUpdateVo;

import java.util.Map;

/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-11-01 20:01:07
 */
public interface CommentService extends IService<CommentEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //删除一条评论的方法，
    R deleteCommentByCommentId(Long id);


    //修改评论点赞数
    R updateCommentPraise(CommentUpdateVo commentUpdateVo);
}

