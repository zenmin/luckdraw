package com.zm.cj.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @Describle This Class Is 全局异常类
 * @Author ZengMin
 * @Date 2019/1/3 19:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommonException extends RuntimeException {

    public int code;

    public CommonException() {
    }
    public CommonException(int code) {
        this(code, null, null);
    }

    public CommonException(int code, String message) {
        this(code, message, null);
    }

    public CommonException(int code,
                           @NotNull Throwable cause) {
        this(code, null, cause);
        this.code = code;
    }

    public CommonException(int code, String message,
                           Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}
