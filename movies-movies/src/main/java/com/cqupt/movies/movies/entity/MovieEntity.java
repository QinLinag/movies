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
 * @date 2022-10-26 00:43:48
 */
@Data
@TableName("movie")
public class MovieEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long mid;
	/**
	 * 类型
	 */
	private String tags;
	/**
	 * 发布日期
	 */
	private Date date;
	/**
	 * 出演明星
	 */
	private String stars;
	/**
	 * 细节信息
	 */
	private String detail;
	/**
	 * 电影名
	 */
	private String name;
	/**
	 * 
	 */
	private String score;
	/**
	 * 情节
	 */
	private String plot;
	/**
	 * 
	 */
	private String avatar;
	/**
	 * 出演明星
	 */
	private String celebrities;

}
