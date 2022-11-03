package com.cqupt.movies.movies.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-11-01 20:01:07
 */
@Data
@TableName("comment_reply")
public class CommentReplyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 回复评论的主键
	 */
	@TableId
	private Long id;
	/**
	 * 该评论id
	 * */
	private Long commentId;
	/**
	 * 回复评论的用户id
	 */
	private Long memberId;
	/**
	 * 被回复的用户id
	 */
	private Long replymemberId;
	/**
	 * 回复内容
	 */
	private String content;
	/**
	 * 点赞数
	 */
	private Integer praseCount;
	/**
	 * 回复时间
	 */
	private Date createTime;

}
