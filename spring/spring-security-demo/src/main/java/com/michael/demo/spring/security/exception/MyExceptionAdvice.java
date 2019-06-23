package com.michael.demo.spring.security.exception;

import com.michael.demo.spring.security.model.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;

/**
 * 类功能描述:
 * <pre>
 *   异常处理
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/23 01:02
 */
@Slf4j
@ControllerAdvice
public class MyExceptionAdvice {

    /**
     * IllegalArgumentException异常处理返回json
     * 返回状态码:400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public JsonResult badRequestException(IllegalArgumentException e) {
        return defHandler("参数解析失败", e);
    }

    /**
     * 返回状态码:404
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoHandlerFoundException.class})
    public JsonResult handleNoHandlerFoundException(NoHandlerFoundException e) {
        return defHandler("不存在的响应", e);
    }

    /**
     * 返回状态码:405
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public JsonResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return defHandler("不支持当前请求方法", e);
    }

    /**
     * 返回状态码:415
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public JsonResult handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return defHandler("不支持当前媒体类型", e);
    }

    /**
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SQLException.class})
    public JsonResult handleSQLException(SQLException e) {
        return defHandler("服务运行异常", e);
    }

    /**
     * AccessDeniedException 未授权访问异常处理
     * 返回状态码:403
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthenticationException.class)
    public JsonResult handlePermissionDenyException(AuthenticationException e) {
        return defHandler("需要授权", e, false);
    }

    /**
     * BusinessException 业务异常处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    public JsonResult handleException(BusinessException e) {
        return defHandler("业务异常", e);
    }

    /**
     * 所有异常统一处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public JsonResult handleException(Exception e) {
        return defHandler("未知异常", e);
    }

    public JsonResult defHandler(String msg, Exception e) {
        return defHandler(msg, e, true);
    }

    public JsonResult defHandler(String msg, Exception e, boolean printLog) {
        if (printLog) {
            log.error(msg, e);
        }
        return JsonResult.failed(msg);
    }
}
