package com.cqupt.movies.movies.vo;


import lombok.Data;

@Data
public class CommentReplyVo {

    private Long id;

    private Long commentId;

    private Long memberId;

    private Long replymemberId;

    private String content;

}
