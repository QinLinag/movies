package com.cqupt.movies.movies.vo;


import lombok.Data;

@Data
public class CommentUpdateVo {

    private Long id;

    private Long mid;

    private Long memberId;

    private String content;

    private Integer praseCount;

}
