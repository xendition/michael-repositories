package com.michael.demo.spring.security.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.michael.demo.spring.security.model.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 类功能描述:
 * <pre>
 *   自定义登录失败处理器
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/15 18:01
 */
@Slf4j
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AuthenticationException e
    ) throws IOException, ServletException {
        // 也可以重新跳转到其它页面

        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(mapper.writeValueAsString(JsonResult.failed("登录失败")));
    }
}
