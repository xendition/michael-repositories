package com.michael.demo.spring.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 类功能描述:
 * <pre>
 *   API 返回结果集封装
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/05/27 22:22
 */
@Getter
@Setter
@AllArgsConstructor
public class JsonResult<T> implements Serializable {

    /**
     * 结果码
     */
    private Integer code;

    /**
     * 结果消息,一般为错误提示消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 成功 - 无响应数据,无消息
     */
    public static <T> JsonResult<T> succeed() {
        return succeedWith(null, JsonResultCodeEnum.SUCCESS.getCode(), null);
    }

    /**
     * 成功 - 无响应数据,有消息
     */
    public static <T> JsonResult<T> succeed(String msg) {
        return succeedWith(null, JsonResultCodeEnum.SUCCESS.getCode(), msg);
    }

    /**
     * 成功 - 有响应数据,无消息
     */
    public static <T> JsonResult<T> succeed(T data) {
        return succeedWith(data, JsonResultCodeEnum.SUCCESS.getCode(), null);
    }

    /**
     * 成功 - 有响应数据,有消息
     */
    public static <T> JsonResult<T> succeed(T data, String msg) {
        return succeedWith(data, JsonResultCodeEnum.SUCCESS.getCode(), msg);
    }

    /**
     * 失败 - 无响应数据,有消息
     */
    public static <T> JsonResult<T> failed(String msg) {
        return succeedWith(null, JsonResultCodeEnum.ERROR.getCode(), msg);
    }

    /**
     * 失败 - 有响应数据,有消息
     */
    public static <T> JsonResult<T> failed(T data, String msg) {
        return succeedWith(data, JsonResultCodeEnum.ERROR.getCode(), msg);
    }

    private static <T> JsonResult<T> succeedWith(T data, Integer code, String msg) {
        return new JsonResult<>(code, msg, data);
    }

    private static <T> JsonResult<T> failedWith(T data, Integer code, String msg) {
        return new JsonResult<>(code, msg, data);
    }
}
