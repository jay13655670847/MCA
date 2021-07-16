package com.jay.cn.security.config.handler;

import com.jay.cn.security.model.bean.SysUser;
import com.jay.cn.security.model.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.context.SpringContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

/**
 * 登录成功处理逻辑
 */
@Component
public class MyAuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    //@Autowired
    SysUserServiceImpl sysUserService = new SysUserServiceImpl();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("********** SuccessHandler start ****************" );
        String username = authentication.getPrincipal().toString();

        String password = authentication.getDetails().toString();

        System.out.println("username:"+username);
        System.out.println("password:"+password);

//        SysUser user =sysUserService.selectByName(username);
//
//        user.setLastLoginTime(new Date());
//        user.setUpdateTime(new Date());
//        user.setId(user.getId());
//
//        sysUserService.update(user);

        //跳转到登录之前的请求
//        RequestCache cache =new HttpSessionRequestCache();
//        SavedRequest request = cache.getRequest(httpServletRequest, httpServletResponse);
//
//        if(request !=null){
//            String url = request.getRedirectUrl();
//            httpServletResponse.sendRedirect(url);
//        }else{
//            httpServletResponse.sendRedirect("/ok.html");
//        }



        System.out.println("********** SuccessHandler end ****************" );


    }
}
