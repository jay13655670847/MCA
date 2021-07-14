package com.jay.cn.security.config.handler;

import com.jay.cn.security.model.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

/**
 * 登录成功处理逻辑
 */
public class MyAuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    @Autowired
    SysUserServiceImpl sysUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        Object credentials = authentication.getCredentials();

        Object principal = authentication.getPrincipal();

        Object details = authentication.getDetails();

        System.out.println("credentials:"+credentials);
        System.out.println("principal:"+principal);
        System.out.println("details:"+details);

//        user.setLastLoginTime(new Date());
//        user.setUpdateTime(new Date());
//        user.setUpdateUser(user.getId());
//
//        sysUserService.update(user);
    }
}
