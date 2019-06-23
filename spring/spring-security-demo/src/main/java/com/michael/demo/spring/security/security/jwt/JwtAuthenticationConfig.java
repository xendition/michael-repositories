package com.michael.demo.spring.security.security.jwt;

import com.michael.demo.spring.security.security.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 类功能描述:
 * <pre>
 *   JWT 配置
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/22 22:10
 */
@Component("jwtAuthenticationConfig")
public class JwtAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private LoginUserService loginUserService;

    @Override
    public void configure(HttpSecurity http) {

        JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider(loginUserService);

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(
                http.getSharedObject(AuthenticationManager.class)
        );

        http.authenticationProvider(jwtAuthenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;
    }
}
