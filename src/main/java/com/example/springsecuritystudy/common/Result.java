package com.example.springsecuritystudy.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    // 状态码
    private Integer code;
    // 是否成功
    private Boolean success;
    // 返回数据
    private T data;

    public static <T> Result<T> success() {
        return new Result<>(200, true, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200, true, data);
    }

    public static <T> Result<T> failure(int code) {
        return new Result<>(code, false, null);
    }

    public static <T> Result<T> failure(int code, T data) {
        return new Result<>(code, false, data);
    }



}
