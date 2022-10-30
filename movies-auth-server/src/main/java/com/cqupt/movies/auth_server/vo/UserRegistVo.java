package com.cqupt.movies.auth_server.vo;

import lombok.Data;

@Data
public class UserRegistVo {

    private String password;

    private String username;

    private String phone;

    private String code;   //注册需要的验证码


}
