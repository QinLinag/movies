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
@TableName("celebrities")
public class Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 英文名字
	 */
	private String englishName;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 星座
	 */
	private String sign;
	/**
	 * 出生日期
	 */
	private String birth;
	/**
	 * 家乡
	 */
	private String hometown;
	/**
	 * 工作
	 */
	private String job;
	/**
	 * 
	 */
	private String imdb;
	/**
	 * 个人经历
	 */
	private String brief;
	/**
	 * 
	 */
	private String avatar;

}
