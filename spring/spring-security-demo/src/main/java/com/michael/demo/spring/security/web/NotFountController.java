package com.michael.demo.spring.security.web;

import com.michael.demo.spring.security.model.JsonResult;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类功能描述:
 * <pre>
 *   404 拦截处理
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/16 17:25
 */
// @ApiIgnore
@RestController
public class NotFountController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     * 但凡走到这里的，都是404
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping(ERROR_PATH)
    public JsonResult methodNotFount() {
        return JsonResult.failed("不存在的响应");
    }
}
