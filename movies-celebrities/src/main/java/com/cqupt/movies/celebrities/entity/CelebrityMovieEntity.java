package com.cqupt.movies.celebrities.entity;

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
 * @date 2022-10-26 08:28:21
 */
@Data
@TableName("celebrity_movie")
public class CelebrityMovieEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 明星id
	 */
	private Long celebrityId;
	/**
	 * 电影id
	 */
	private Long movieMid;

}
