package com.cqupt.movies.member.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class InfoMoiveStatusVo {

    private Long id;

    private Long status;

    private Long memberId;

    private Long movieId;
}
