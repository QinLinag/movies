package com.cqupt.movies.common.to;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CreateMemberInfoTo implements Serializable {

    private Long id;

    private Long statusEntityId;

    private Long memberId;

    private Long movieId;

    private BigDecimal grade;

}
