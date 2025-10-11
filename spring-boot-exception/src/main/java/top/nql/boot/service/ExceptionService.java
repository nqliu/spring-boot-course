package top.nql.boot.service;

import org.springframework.stereotype.Service;
import top.nql.boot.exception.BusinessException;
@Service
public class ExceptionService {
/**
 * 模拟未登录异常
 */
    public static void unAuthorError(){
        throw new BusinessException("权限不足");

    }
    /**
     * 模拟系统异常
     */
    public static void systemError(){
        throw new BusinessException("系统异常");

    }
}
