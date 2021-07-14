package com.jay.cn.cros1.config;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.
                //哪些地址需要登录
                        authorizeRequests()
                //所有
                .anyRequest().authenticated()
                .and()
                //自定义登录页面
                .formLogin()
                //.loginPage("/login")
                .loginProcessingUrl("/login.do")
                .permitAll()
                //登录失败页面
                .failureUrl("/login.html?error")
                //登录成功页面
                .successForwardUrl("/success.do").permitAll()
                .passwordParameter("username")
                .usernameParameter("password")
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        System.out.println("************ error");
                        e.printStackTrace();
                    }
                })
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        System.out.println("************ success");
                        String username = httpServletRequest.getParameter("username");
                        String password = httpServletRequest.getParameter("password");
                        System.out.println("username:" + username + "---password:" + password);
                        httpServletResponse.sendRedirect("/success.do");
                    }
                })
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        System.out.println("************ logout");
                        httpServletResponse.sendRedirect("/login");
                    }
                })
                .and().csrf().csrfTokenRepository(new HttpSessionCsrfTokenRepository())
        ;

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);

        auth.inMemoryAuthentication()
                .withUser("root").roles("admin").password("$2a$10$voLWTUCULs8CyIwGS8qe6e2j9OYrDf605OVvNCHz0XKihok3Caqwa")
                .and()
                .withUser("zhangsan").roles("user").password("$2a$10$CIQlMvH79sOKH3q0xPEOj.sIOZRADuG5yL9QdbL53piv2OtzayXuK");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Test
    public void test() {
        String encode = this.passwordEncoder().encode("123456");
        String encode2 = this.passwordEncoder().encode("456");
        System.out.println(encode);
        System.out.println(encode2);
    }
}
