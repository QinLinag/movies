package com.cqupt.movies.movies.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class CommentVo {

    private Long id;

    private Long mid;

    @JsonProperty(value = "memberId")
    private Long memberId;

    private String content;


}
