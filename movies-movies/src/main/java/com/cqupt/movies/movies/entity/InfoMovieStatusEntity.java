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
 * @date 2022-11-07 14:26:53
 */
@Data
@TableName("info_movie_status")
public class InfoMovieStatusEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 状态
	 */
	private Long status;
	/**
	 * 用户id
	 */
	private Long memberId;
	/**
	 * 电影id
	 */
	private Long movieId;

}
