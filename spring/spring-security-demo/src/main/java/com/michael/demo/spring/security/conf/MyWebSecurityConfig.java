package com.michael.demo.spring.security.conf;

import com.michael.demo.spring.security.security.SecurityProperties;
import com.michael.demo.spring.security.security.handler.MyAuthenticationException;
import com.michael.demo.spring.security.security.jwt.JwtAuthenticationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 类功能描述:
 * <pre>
 *   Security 配置
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/22 00:29
 */
@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(SecurityProperties.class)
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private JwtAuthenticationConfig jwtAuthenticationConfig;

    @Autowired
    private MyAuthenticationException myAuthenticationException;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            // 配置的忽略拦截
            .antMatchers(securityProperties.getIgnoreUrls()).permitAll()
            // 其它的请求需要认证
            .anyRequest().authenticated()
        ;
        // 普通登录配置
        http.formLogin()
            .loginPage(securityProperties.getLoginPage()).permitAll()
            // loginProcessingUrl用于指定前后端分离的时候调用后台登录接口的名称
            .loginProcessingUrl(securityProperties.getLogin()).permitAll()
            // 认证成功处理器
            .successHandler(successHandler)
            // 认证失败处理器
            .failureHandler(failureHandler)
        ;
        // 登出配置
        http.logout()
            .logoutUrl(securityProperties.getLogout()).permitAll()
            .logoutSuccessHandler(logoutSuccessHandler)
            // 用户注销后清除认证
            .clearAuthentication(true)
        ;
        // JWT 配置
        http.apply(jwtAuthenticationConfig)
        ;
        //异常处理配置
        http.exceptionHandling()
            // 未授权处理类
            .accessDeniedHandler(accessDeniedHandler)
            // 其它权限异常处理
            .authenticationEntryPoint(myAuthenticationException)
        ;
        // 其它配置
        http.cors().and().csrf().disable()
        ;
    }

    /**
     * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
     *
     * @return 认证管理对象
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
