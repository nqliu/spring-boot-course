<<<<<<<< HEAD:spring-boot-week1/src/main/java/top/nql/service/impl/SmsServiceImpl.java
package top.nql.service.impl;
========
package top.nql.boot.service.impl;
>>>>>>>> a43fc7db9e2411a2611127d4f9c60a7174b604bf:spring-boot-redis/src/main/java/top/nql/boot/service/impl/SmsServiceImpl.java

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
<<<<<<<< HEAD:spring-boot-week1/src/main/java/top/nql/service/impl/SmsServiceImpl.java
import top.nql.config.CloopenConfig;
import top.nql.service.SmsService;
========
import top.nql.boot.Exception.ServerException;
import top.nql.boot.cache.RedisCache;
import top.nql.boot.cache.RedisKeys;
import top.nql.boot.config.CloopenConfig;
import top.nql.boot.enums.ErrorCode;
import top.nql.boot.service.SmsService;
import top.nql.boot.utils.CommonUtils;
>>>>>>>> a43fc7db9e2411a2611127d4f9c60a7174b604bf:spring-boot-redis/src/main/java/top/nql/boot/service/impl/SmsServiceImpl.java

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final CloopenConfig cloopenConfig;
    private final RedisCache redisCache;

    @Override
    public boolean sendSms(String phone) {
        if (!CommonUtils.checkPhone(phone)) {
            throw new ServerException(ErrorCode.PHONE_ERROR);
        }

        int code = CommonUtils.generateCode();
        redisCache.set(RedisKeys.getSmsKey(phone), code, 60);
        log.info("发送短信验证码：{}", code);
        boolean flag = true;
        flag = send(phone,code);
        return flag;
    }


    private boolean send(String phone, int code) {
        String serverIp = cloopenConfig.getServerIp();
        String serverPort = cloopenConfig.getPort();
        String accountSid = cloopenConfig.getAccountSid();
        String accountToken = cloopenConfig.getAccountToken();
        String appId = cloopenConfig.getAppId();
        String templateId = cloopenConfig.getTemplateId();

        // 初始化SDK
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSid, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);

        String[] datas = {String.valueOf(code), "1"};
        HashMap<String, Object> result = sdk.sendTemplateSMS(
                phone,
                templateId,
                datas,
                "1234",
                UUID.randomUUID().toString()
        );

        if ("000000".equals(result.get("statusCode"))) {
            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for (String key : keySet) {
                Object object = data.get(key);
                log.info("{} = {}", key, object);
            }
        } else {
            log.error("错误码={}, 错误信息= {}",
                    result.get("statusCode"),
                    result.get("statusMsg"));
            return false;
        }
        return true;
    }
}

