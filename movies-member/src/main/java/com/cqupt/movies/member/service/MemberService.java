package com.cqupt.movies.member.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.member.entity.Entity;
import com.cqupt.movies.member.exception.PhoneExistException;
import com.cqupt.movies.member.exception.UsernameExistException;
import com.cqupt.movies.member.vo.MemberLoginVo;
import com.cqupt.movies.member.vo.MemberRegistVo;

import java.util.Map;

/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-10-26 08:29:08
 */
public interface MemberService extends IService<Entity> {

    PageUtils queryPage(Map<String, Object> params);

    void regist(MemberRegistVo vo);

    void checkUserNameUnique(String username) throws UsernameExistException;

    void checkPhoneUnique(String phone) throws PhoneExistException;


    Entity login(MemberLoginVo vo);
}

