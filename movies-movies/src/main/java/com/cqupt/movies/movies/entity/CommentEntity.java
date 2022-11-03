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
@TableName("comment")
public class CommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 评论的主键
	 */
	@TableId
	private Long id;
	/**
	 * 电影的id
	 */
	private Long mid;
	/**
	 * 评论者id
	 */
	private Long memberId;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 点赞数，评论
	 */
	private Integer praseCount;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
