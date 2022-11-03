package com.cqupt.movies.celebrities.vo;


import lombok.Data;

@Data
public class InfoMovieVo {

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
