package top.nql.boot.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.nql.boot.enums.ErrorCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ServerException extends RuntimeException{
  private int code;
  private String msg;
  public ServerException(String msg) {
    this.code = ErrorCode.PHONE_ERROR.getCode();
    this.msg = msg;
  }

  public ServerException(ErrorCode errorCode) {
    this.code = errorCode.getCode();
    this.msg = errorCode.getMsg();
  }

  public ServerException(String msg, Throwable e) {
    this.code = ErrorCode.PHONE_ERROR.getCode();
    this.msg = msg;
  }

}
