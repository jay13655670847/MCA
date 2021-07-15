package com.jay.cn.security.config.service;

import com.jay.cn.security.model.bean.SysUser;
import com.jay.cn.security.model.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * 校验密码
 */
public class MyAuthProvider implements AuthenticationProvider {


    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String password = authentication.getCredentials().toString();
        String username = authentication.getPrincipal().toString();
        System.out.println("********* username:"+username);
        System.out.println("********* password:"+password);

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
        System.out.println("********* user:"+userDetails.toString());

        if(userDetails!=null){
            if(new BCryptPasswordEncoder().matches(password,userDetails.getPassword())){
                System.out.println("返回authentication~~~~~~");

                Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
                return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
            }else{
                System.out.println("用户名或密码不正确");
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
