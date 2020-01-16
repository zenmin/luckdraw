package com.zm.cj.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Describle This Class Is 全局接口返回类
 * @Author ZengMin
 * @Date 2019/1/3 19:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ResponseEntity {

    private int code;

    private String msg;

    private Object data;

    public static ResponseEntity success() {
        return new ResponseEntity(100, "success", null);
    }

    public static ResponseEntity success(Object data) {
        return new ResponseEntity(100, "success", data);
    }

    public static ResponseEntity error(int code, String msg) {
        return new ResponseEntity(code, msg, null);
    }

    public static ResponseEntity error() {
        return new ResponseEntity(999, "失败", null);
    }

    public static ResponseEntity error(String msg) {
        return new ResponseEntity(999, msg, null);
    }


}
