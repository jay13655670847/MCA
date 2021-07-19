package com.jay.cn.security.config;


import com.jay.cn.security.config.handler.MyAuthenticationFailureHandler;
import com.jay.cn.security.config.handler.MyAuthenticationSuccessHandler;
import com.jay.cn.security.config.service.MyAuthProvider;
import com.jay.cn.security.config.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(WebSecurity web) throws Exception {
        //super.configure(web);
        web.ignoring().antMatchers("/templates/**","/static/**","login.html","/**.css","/css/**","/img/**","/js/**","/vendor/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //此处defaultSuccessUrl不起作用，只需把successHandler前移
                .successHandler(new MyAuthenticationSuccessHandler())
                .loginProcessingUrl("/login") //当发现/login 时认为是登录，需 要执行 UserDetailsServiceImpl
                .successForwardUrl("/index") //登录成功后，跳转到指定请求（此处是 post 请求）
                .failureForwardUrl("/login")//登录失败
                .loginPage("/login.html")
                .defaultSuccessUrl("/index")//此处后面可以加个参数true，意思无论登陆之前是否有请求，都跳转到指定请求
                .permitAll()
                .successHandler(new MyAuthenticationSuccessHandler())
                .failureHandler(new MyAuthenticationFailureHandler());


        // url 拦截
        http.authorizeRequests()
                .antMatchers("/login.html").permitAll() //login.html 不需要被认证
                .antMatchers("/login.html").permitAll() //loginfail.html 不需要被认证
                .anyRequest().authenticated();//所有的请求都必须被认证。必须登录 后才能访问。

        http.rememberMe();

        // 关闭 csrf 防护
        http.csrf().disable();

    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new MyUserDetailsService();
    }

    @Bean
    public AuthenticationProvider myAuthProvider(){
        return new MyAuthProvider();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        System.out.println("--------------准备执行 userDetailsService");
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
        System.out.println("--------------准备执行 myAuthProvider");
        auth.authenticationProvider(myAuthProvider());
    }


}
