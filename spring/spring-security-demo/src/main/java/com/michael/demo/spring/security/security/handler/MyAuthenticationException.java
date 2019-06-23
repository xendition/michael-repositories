package com.michael.demo.spring.security.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.michael.demo.spring.security.model.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 类功能描述:
 * <pre>
 *   security 非权限不足异常的其它异常捕捉
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/23 21:15
 */
@Slf4j
@Component("myAuthenticationException")
public class MyAuthenticationException implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void commence(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException authException
    ) throws IOException, ServletException {

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json;charset=utf-8");
        // httpServletResponse.getWriter().write("需要授权");
        response.getWriter().write(mapper.writeValueAsString(JsonResult.failed(authException.getMessage())));
    }
}
