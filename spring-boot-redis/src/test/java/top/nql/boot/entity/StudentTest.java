package top.nql.boot.entity;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class StudentTest {
    @Resource
    private RedisTemplate<String,Student> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testStudent(){
        Address address = Address.builder().city("南京市").province("江苏省").build();
        Student student = Student.builder().id("13").name("liuqn").address(address).build();
        redisTemplate.opsForValue().set("student",student,10, TimeUnit.SECONDS);
    }

    @Test
    void test(){
        stringRedisTemplate.opsForValue().set("key","123",20, TimeUnit.SECONDS);
    }

}
