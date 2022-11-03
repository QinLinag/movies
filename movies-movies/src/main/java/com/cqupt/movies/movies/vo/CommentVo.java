package com.cqupt.movies.movies.vo;


import lombok.Data;

@Data
public class CommentVo {

    private Long id;

    private Long mid;

    private Long memberId;

    private String content;


}
