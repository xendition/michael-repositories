package com.michael.demo.spring.security.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.michael.demo.spring.security.model.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 类功能描述:
 * <pre>
 *   自定义没有权限的处理器
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/15 18:07
 */
@Slf4j
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void handle(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AccessDeniedException e
    ) throws IOException, ServletException {

        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        httpServletResponse.setContentType("application/json;charset=utf-8");
        // httpServletResponse.getWriter().write("需要授权");
        httpServletResponse.getWriter().write(mapper.writeValueAsString(JsonResult.failed("需要授权")));
    }
}
