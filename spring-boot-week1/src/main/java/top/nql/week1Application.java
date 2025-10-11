package top.nql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// 手动指定扫描范围：包含 CloopenConfig 所在的包
@ComponentScan(basePackages = {
        "top.nql.week1",          // 启动类所在包（默认扫描）
        "top.nql.week1.config"    // CloopenConfig 所在包（确保包含）
})
public class week1Application {
    public static void main(String[] args) {
        SpringApplication.run(week1Application.class, args);
    }
}