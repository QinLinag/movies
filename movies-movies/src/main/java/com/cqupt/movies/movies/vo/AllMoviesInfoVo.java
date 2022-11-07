package com.cqupt.movies.movies.vo;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;



//封装一个返回给前端页面的数据，
@Data
public class AllMoviesInfoVo {

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


    /**
     * 评分
     * */
    private BigDecimal grade;





}
