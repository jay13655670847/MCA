package com.jay.cn.security.config.service;

import com.jay.cn.security.model.bean.SysPermission;
import com.jay.cn.security.model.bean.SysUser;
import com.jay.cn.security.model.service.impl.SysPermissionServiceImpl;
import com.jay.cn.security.model.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 登录校验方法
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    SysUserServiceImpl sysUserService;

    @Autowired
    SysPermissionServiceImpl sysPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("************* loadUserByUsername start*****************");

        System.out.println("username:" + username);

        if (username == null || username == "") {
            throw new RuntimeException("用户不能为空");
        }

        SysUser user = sysUserService.selectByName(username);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        System.out.println("user:"+user.toString());

        List<SysPermission> sysPermissions =
                sysPermissionService.selectListByUser(user.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for(SysPermission sysPermission:sysPermissions){
            grantedAuthorities.add(new SimpleGrantedAuthority(sysPermission.getPermissionCode()));
        }


        System.out.println("************* loadUserByUsername end*****************");
        return new User(user.getUserName(), user.getPassword(), user.getEnabled(), user.getAccountNonExpired(), user.getCredentialsNonExpired(), user.getAccountNonLocked(), grantedAuthorities);
    }
}
