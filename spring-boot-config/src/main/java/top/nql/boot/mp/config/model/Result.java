package top.nql.boot.mp.config.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    // 状态码：200 成功，非 200 失败
    private Integer code;
    // 提示信息
    private String message;
    // 接口返回的数据
    private T data;

    // 成功时的构造方法
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    // 失败时的构造方法
    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<>(code, message, null);
    }
}
