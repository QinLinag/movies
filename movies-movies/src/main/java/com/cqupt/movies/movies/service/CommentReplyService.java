package com.cqupt.movies.movies.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqupt.common.utils.PageUtils;
import com.cqupt.movies.movies.entity.CommentReplyEntity;

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
}

