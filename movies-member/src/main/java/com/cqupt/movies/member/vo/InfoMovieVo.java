package com.cqupt.movies.member.vo;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class InfoMovieVo {

    private Long statusEntityId;

    private Long memberId;

    private Long movieId;

    private BigDecimal grade;


}
