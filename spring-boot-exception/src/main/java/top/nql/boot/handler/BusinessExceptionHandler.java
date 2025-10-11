package top.nql.boot.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.nql.boot.common.Result;
import top.nql.boot.exception.BusinessException;

/**
 * @author nqliu
 * @Description: 业务异常处理
 */
@Slf4j
@RestControllerAdvice
public class BusinessExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public Result<String> handleBusinessException(BusinessException e) {
        return Result.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    @Order(Ordered.LOWEST_PRECEDENCE) // 关键：标记为最低优先级
    public Result<String> handleOtherException(Exception e) {
        return Result.error(e.getMessage());
    }
}

