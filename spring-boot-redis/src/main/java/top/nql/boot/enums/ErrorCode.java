package top.nql.boot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNAUTHORIZED(401, "登陆失效，请重新登录"),INTERVAL_SERVER_ERROR(500, "服务器异常，请稍后再试"),
    CODE_SEND_FAIL(3001,"短信发送失败"),SMS_CODE_ERROR(3002,"短信验证码错误"),PHONE_ERROR(3003,"手机号错误");
    private final int code;
    private final String msg;


}
