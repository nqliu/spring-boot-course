package top.nql.boot.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.nql.boot.Exception.ServerException;
import top.nql.boot.cache.RedisCache;
import top.nql.boot.cache.RedisKeys;
import top.nql.boot.entity.LoginRequest;
import top.nql.boot.entity.LoginResponse;
import top.nql.boot.enums.ErrorCode;
import top.nql.boot.service.LoginService;
import top.nql.boot.utils.CommonUtils;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {
      private final RedisCache redisCache;
      @Override
      public LoginResponse login(LoginRequest loginRequest){
          String phone =loginRequest.getPhone();
          String inputCode =loginRequest.getCode();
        //1.校验手机号格式
          if (!CommonUtils.checkPhone(phone)) {
              throw new ServerException(ErrorCode.PHONE_ERROR);
          }
          //2.校验验证码是否为空
        if (inputCode == null || inputCode.trim().isEmpty()){
            throw new ServerException("验证码不能为空");
        }
        String redisKey= RedisKeys.getSmsKey(phone);
        String redisCode = redisCache.get(redisKey).toString();
        if(redisCode==null){
            throw new ServerException("验证码已过期或不存在");

        }
        if(!inputCode.equals(redisCode)){
            throw new ServerException("验证码错误");
        }
        redisCache.delete(redisKey);
        String token=generateToken(phone);
        log.info("用户{}登陆成功",phone);
        return new LoginResponse(token,phone);
        }
        private String generateToken(String phone){
            return UUID.randomUUID().toString().replace("-","")+phone.hashCode();
        }
      }