package com.michael.demo.spring.security.model;

/**
 * JsonResult 响应封装结果码
 *
 * @author Michael
 */
public enum JsonResultCodeEnum {

    /**
     * 成功响应
     */
    SUCCESS(0),

    /**
     * 失败响应
     */
    ERROR(1);

    private Integer code;

    JsonResultCodeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
