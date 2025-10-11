package top.nql.boot.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.nql.boot.common.Result;
import org.springframework.validation.BindException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError error = e.getBindingResult().getFieldError();
        assert error != null;
        return Result.error(error.getDefaultMessage());
    }

    @ExceptionHandler(BindException.class)
    public Result<?> handleBindException(BindException e) {
        FieldError error = e.getBindingResult().getFieldError();
        assert error != null;
        return Result.error(error.getDefaultMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return Result.error("请求参数格式错误");
    }

    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        e.printStackTrace();
        return Result.error("系统异常，请稍后再试");
    }
}