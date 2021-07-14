package com.jay.cn.security.config.service;

import com.jay.cn.security.model.bean.SysUser;
import com.jay.cn.security.model.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 校验密码
 */
@Service
public class MyAuthProvider implements AuthenticationProvider {

    @Autowired
    SysUserServiceImpl sysUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String password = authentication.getCredentials().toString();
        String username = authentication.getPrincipal().toString();
        System.out.println("********* password:"+password);
        System.out.println("********* username:"+username);

        SysUser user = sysUserService.selectByName(username);

        if(user!=null){
            if(new BCryptPasswordEncoder().matches(password,user.getPassword())){
               return  authentication;
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
