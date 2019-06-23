package com.michael.demo.spring.security.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 类功能描述:
 * <pre>
 *   自定义登录成功处理器
 *   登录成功，需要返回客户端一个token?
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/15 18:00
 */
@Slf4j
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse httpServletResponse,
            Authentication authentication
    ) throws IOException, ServletException {
        redirectStrategy.sendRedirect(request, httpServletResponse, "/sys/login/index");
        log.info("登录成功！" + this.getClass().toString());
    }
}
