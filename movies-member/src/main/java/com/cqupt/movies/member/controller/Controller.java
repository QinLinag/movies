package com.cqupt.movies.member.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.cqupt.movies.common.exception.BizCodeEnum;
import com.cqupt.movies.common.utils.PageUtils;
import com.cqupt.movies.common.utils.R;
import com.cqupt.movies.member.exception.PhoneExistException;
import com.cqupt.movies.member.exception.UsernameExistException;
import com.cqupt.movies.member.vo.MemberLoginVo;
import com.cqupt.movies.member.vo.MemberRegistVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cqupt.movies.member.entity.Entity;
import com.cqupt.movies.member.service.MemberService;


/**
 * 
 *
 * @author qinliang
 * @email 2874974475@qq.com
 * @date 2022-10-26 08:29:08
 */
@RestController
@RequestMapping("/member")
public class Controller {
    @Autowired
    private MemberService MemberService;






    /**
     * 注册接口，由movies-auth-server服务调用
     *
     * */
    @PostMapping("/regist")
    public R regist(@RequestBody MemberRegistVo vo){  //@RequestBody，因为远程调用这个接口传过来的是json字符串，这个注解的意思是将json转为被注释的对象
        try{
            MemberService.regist(vo);
        }catch (UsernameExistException e){
            return R.error(BizCodeEnum.USER_EXIST_EXCEPTION.getCode(), BizCodeEnum.USER_EXIST_EXCEPTION.getMsg());
        }catch (PhoneExistException e){
            return R.error(BizCodeEnum.PHONE_EXIST_EXCEPTION.getCode(), BizCodeEnum.PHONE_EXIST_EXCEPTION.getMsg());
        }
        return R.ok();
    }


    /**
     * 登入接口，由movies-auth-server服务调用
     *
     * */

    @PostMapping("/login")
    public R login(@RequestBody MemberLoginVo vo){
       Entity entity= MemberService.login(vo);
       if(null!=entity){
           return R.ok().setData(entity);   //将登入的用户信息返回给前端封装起来，
       }else {
           return R.error(BizCodeEnum.LOGINACCT_PASSWORD_INVALID_EXCEPTION.getCode(), BizCodeEnum.LOGINACCT_PASSWORD_INVALID_EXCEPTION.getMsg());
       }
    }
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = MemberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        Entity byId = MemberService.getById(id);

        return R.ok().put("", byId);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody Entity entity){
		MemberService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody Entity entity){
		MemberService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		MemberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
