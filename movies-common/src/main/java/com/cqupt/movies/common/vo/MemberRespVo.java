package com.cqupt.movies.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MemberRespVo {
    private Long id;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别
     */
    private String gender;
    /**
     * 出生日期
     */
    private Date date;

    //手机号
    private String phone;


}
