package com.michael.demo.spring.security.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 类功能描述:
 * <pre>
 *   自定义登出成功处理器
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/15 18:05
 */
@Slf4j
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Authentication authentication
    ) throws IOException, ServletException {

        log.info("登出成功");
    }
}
