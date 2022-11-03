package com.cqupt.movies.member.entity;

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
 * @date 2022-11-03 10:03:41
 */
@Data
@TableName("keen_movie")
public class KeenMovieEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 用户id
	 */
	private Long memberId;
	/**
	 * 电影id
	 */
	private Long movieId;

}
