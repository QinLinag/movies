package com.cqupt.movies.member.exception;

public class UsernameExistException extends RuntimeException{


    public UsernameExistException() {
        super("用户名已经存在了");
    }
}
