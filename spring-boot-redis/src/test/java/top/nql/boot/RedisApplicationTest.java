package top.nql.boot;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;
@SpringBootTest
class RedisApplicationTest {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void test(){
        stringRedisTemplate.opsForValue().set("key","123",20, TimeUnit.SECONDS);
    }

}