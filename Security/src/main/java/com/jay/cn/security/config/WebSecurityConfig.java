package com.jay.cn.security.config;


import com.jay.cn.security.config.handler.MyAuthenticationFailureHandler;
import com.jay.cn.security.config.handler.MyAuthenticationSuccessHandler;
import com.jay.cn.security.config.service.MyAuthProvider;
import com.jay.cn.security.config.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.csrf().and().cors().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                //.loginProcessingUrl("/login").loginPage("/login.do")
                .permitAll()
                .successHandler(new MyAuthenticationSuccessHandler())
                .failureHandler(new MyAuthenticationFailureHandler())

        ;

    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.userDetailsService(userDetailsService());

        auth.authenticationProvider(new MyAuthProvider());
    }


}
