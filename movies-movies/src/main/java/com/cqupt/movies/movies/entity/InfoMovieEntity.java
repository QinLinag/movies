package com.cqupt.movies.movies.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * 
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-10-26 00:43:48
 */
@Data
@TableName("info_movie")
public class InfoMovieEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 电影id
	 */
	private Long mid;
	/**
	 * 点赞数
	 */
	private Long thumbUp;
	/**
	 * 收藏数
	 */
	private Long collect;
	/**
	 * 看过数
	 */
	private Long watched;
	/**
	 * 想看数量
	 */
	private Long keen;

}
