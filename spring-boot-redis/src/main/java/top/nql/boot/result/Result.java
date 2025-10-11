package top.nql.boot.result;

import lombok.Data;
import top.nql.boot.enums.ErrorCode;

@Data
public class Result<T> {
    private int code = 0;
<<<<<<< HEAD
    private String msg = "success";
    private T data;
    public static <T> Result<T> ok() {
        return ok(null);
    }
=======

    private String msg = "success";

    private T data;

    public static <T> Result<T> ok() {
        return ok(null);
    }

>>>>>>> a43fc7db9e2411a2611127d4f9c60a7174b604bf
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        return result;
    }
<<<<<<< HEAD
    public static <T> Result<T> error() {
        return error(ErrorCode.PHONE_ERROR);
    }
    public static <T> Result<T> error(String msg) {
        return error(ErrorCode.PHONE_ERROR.getCode(), msg);
    }
    public static <T> Result<T> error(ErrorCode errorCode) {

        return error(errorCode.getCode(), errorCode.getMsg());
    }
=======

    public static <T> Result<T> error() {
        return error(ErrorCode.PHONE_ERROR);
    }

    public static <T> Result<T> error(String msg) {
        return error(ErrorCode.PHONE_ERROR.getCode(), msg);
    }

    public static <T> Result<T> error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

>>>>>>> a43fc7db9e2411a2611127d4f9c60a7174b604bf
    public static <T> Result<T> error(int code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
