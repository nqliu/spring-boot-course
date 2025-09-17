package top.nql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 关键：扫描路径必须是 "top.yxq.mapper"（Mapper接口所在包），不能只写 "top.yxq"
@MapperScan("top.nql.mapper")
@SpringBootApplication
public class MpApplication {
    public static void main(String[] args) {
        SpringApplication.run(MpApplication.class, args);
    }
}