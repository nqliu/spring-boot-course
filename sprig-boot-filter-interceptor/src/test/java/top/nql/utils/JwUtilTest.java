package top.nql.utils;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class JwUtilTest {
    @Resource
    private JwtUtil jwUtil;
    @Test
    void generateToken() {
        String token = jwUtil.generateToken(String.valueOf(1L),"nql");
       log.info("token:{}",token);
       boolean b=jwUtil.validateToken(token);
       log.info("token验证结果:{}",b);
       assertTrue(b);
    }

}