package com.cqupt.movies.auth_server.vo;

import lombok.Data;

@Data
public class SmsVo {

    private String phoneNum;
    private String code;
}
