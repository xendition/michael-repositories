package com.michael.demo.spring.security.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类功能描述:
 * <pre>
 *   SpringSecurity 配置文件
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/15 19:31
 */
@ConfigurationProperties(prefix = SecurityProperties.PREFIX)
public class SecurityProperties {

    public static final String PREFIX = "scales.security";

    /**
     * 监控中心和swagger需要访问的url
     */
    private static final String[] ENDPOINTS = {
            "/favicon.ico",
            "/oauth/**",
            "/actuator/**",
            "/v2/api-docs",
            "/swagger/api-docs",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**",
            "/druid/**"
    };

    /**
     * 设置不用认证的url
     */
    private String[] ignoreUrls = {};

    private String loginPage = "/sys/sys/noLogin";
    private String login = "/sys/sys/login";
    private String logout = "/sys/sys/logout";

    public void setIgnoreUrls(String[] ignoreUrls) {
        this.ignoreUrls = ignoreUrls;
    }

    public String[] getIgnoreUrls() {
        if (ignoreUrls == null || ignoreUrls.length == 0) {
            return ENDPOINTS;
        }
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(ENDPOINTS));
        list.addAll(Arrays.asList(ignoreUrls));
        return list.toArray(new String[0]);
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }
}
