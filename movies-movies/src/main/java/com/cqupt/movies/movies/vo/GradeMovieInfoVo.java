package com.cqupt.movies.movies.vo;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GradeMovieInfoVo {

    private Long id;

    private Long memberId;

    private Long movieId;

    private BigDecimal grade;


}
