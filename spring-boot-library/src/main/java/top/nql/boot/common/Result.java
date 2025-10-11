package top.nql.boot.common;

import lombok.Data;

@Data

public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    // 成功响应方法
    public static <T> Result<T> success(T data, String message) {
        Result<T> result = new Result<>();
        result.code = 200;
        result.message = message;
        result.data = data;
        return result;
    }

    // 失败响应方法
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.code = 500;
        result.message = message;
        result.data = null;
        return result;
    }
}


