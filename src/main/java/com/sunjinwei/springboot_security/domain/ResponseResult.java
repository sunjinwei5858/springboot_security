package com.sunjinwei.springboot_security.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: com.sunjinwei.springboot_security.domain
 * @author: sun jinwei
 * @create: 2023-07-16 14:30
 * @description:
 **/
@Data
@NoArgsConstructor
public class ResponseResult<T> {

    private Integer code;

    private String msg;

    private T data;

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}