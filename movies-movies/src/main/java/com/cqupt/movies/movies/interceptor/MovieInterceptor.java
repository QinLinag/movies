package com.cqupt.movies.movies.interceptor;

import com.cqupt.movies.common.constant.AuthServerConstant;
import com.cqupt.movies.common.vo.MemberRespVo;
import com.cqupt.movies.movies.constant.MovieConstant;
import com.cqupt.movies.movies.to.UserInfoTo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;


@Component
public class MovieInterceptor implements HandlerInterceptor {

    //将用户的信息保存在threadlocal中，
    public static ThreadLocal<UserInfoTo> threadLocal=new ThreadLocal<>();

    //业务执行前  如果用户登入了，就获得用户信息，如果就设置一个临时用户信息返回，
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        UserInfoTo userInfoTo = new UserInfoTo();

        HttpSession session = request.getSession();
        MemberRespVo member = (MemberRespVo)session.getAttribute(AuthServerConstant.LOGIN_USER);    //用户信息保存在session中，
        if (member!=null){
            //用户登录
            userInfoTo.setUserId(member.getId());
            Cookie[] cookies = request.getCookies();
            if (cookies!=null&&cookies.length>0){
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();   //取出userkey，
                    if (name.equals(MovieConstant.TEMP_USER_NAME)){   //看看是不是临时用户，
                        userInfoTo.setUserKey(cookie.getValue());
                        userInfoTo.setTempUser(true);
                        return true;
                    }
                }
            }
        }


        if(member==null) {
            Cookie[] cookies = request.getCookies();
            if (cookies!=null&&cookies.length>0) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(MovieConstant.TEMP_USER_NAME)) {
                        System.out.println("getValue" + cookie.getValue());
                        userInfoTo.setUserKey(cookie.getValue());
                    }
                }
            }
        }

        //如果没有userkey，就分配一个，
        if(StringUtils.isEmpty(userInfoTo.getUserKey())){   //临时用户是否已经有了userKey,
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
            userInfoTo.setUserKey(uuid);
            Cookie cookie = new Cookie(MovieConstant.TEMP_USER_NAME, userInfoTo.getUserKey());
            cookie.setMaxAge(MovieConstant.TEMP_USER_COOKIE_TIMEOUT);
            cookie.setPath("/");
            response.addCookie(cookie);
        }


        //拦截器放行前，
        threadLocal.set(userInfoTo);
            return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
}
