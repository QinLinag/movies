package com.cqupt.movies.common.exception;

public enum BizCodeEnum {

    UNKNOW_EXCEPTION(10000,"system exception"),
    VALID_EXCEPTION(10001,"unvalid date exception"),
    SMS_CODE_EXCEPTION(10002,"验证码发送频率太高，稍后重试"),
    USER_EXIST_EXCEPTION(15001,"用户存在"),
    PHONE_EXIST_EXCEPTION(15002,"手机号存在"),
    LOGINACCT_PASSWORD_INVALID_EXCEPTION(15003,"账号密码错误");

    private int code;
    private String msg;

    BizCodeEnum(int code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
