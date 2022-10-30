package com.cqupt.movies.celebrities.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MoviesVo {
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
