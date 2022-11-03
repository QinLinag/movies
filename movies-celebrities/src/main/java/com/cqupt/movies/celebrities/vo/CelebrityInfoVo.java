package com.cqupt.movies.celebrities.vo;

import lombok.Data;

@Data
public class CelebrityInfoVo {

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


    //明星一共出演了几部电影
    private Long moviesCount;

}
