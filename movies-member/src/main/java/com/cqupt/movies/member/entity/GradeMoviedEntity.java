package com.cqupt.movies.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-11-06 14:49:44
 */
@Data
@TableName("grade_movied")
public class GradeMoviedEntity implements Serializable {
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
	/**
	 * 评分
	 */
	private BigDecimal grade;

}
