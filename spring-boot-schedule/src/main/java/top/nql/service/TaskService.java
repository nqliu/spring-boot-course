package top.nql.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Slf4j
@Service
public class TaskService {

//    fixedDelay 表示固定延迟执行。即上一次任务执行完毕后，延迟指定的毫秒数，再执行下一次任务。
    @Scheduled(fixedDelay = 5000) // 延迟5秒后开始执行
    public void fixedDelayTask() {
        log.info("【固定延迟任务】执行时间：{}，执行线程：{}", LocalDateTime.now(), Thread.currentThread().getName());
        // 模拟任务执行耗时（1秒）
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

//    fixedRate 表示固定频率执行。即按照指定的频率（毫秒数）来执行任务，不管上一次任务是否执行完毕，到了指定时间就会尝试执行下一次任务。
    @Scheduled(fixedRate = 5000) // 频率为每5秒执行一次
    public void fixedRateTask() {
        log.info("【固定频率任务】执行时间：{}，执行线程：{}", LocalDateTime.now(), Thread.currentThread().getName());
    }

//    initialDelay 用于指定初始延迟时间，结合 fixedRate 或 fixedDelay 使用。表示应用启动后，延迟指定的毫秒数，才开始第一次执行任务，之后按照 fixedRate 或 fixedDelay 的规则执行。
    @Scheduled(initialDelay = 10000, fixedRate = 5000) // 启动后10秒开始，每5秒执行
    public void initialDelayTask() {
        log.info("【初始延迟任务】执行时间：{},执行线程：{}", LocalDateTime.now(), Thread.currentThread().getName());
    }

//    cron 表达式是一种更灵活、强大的定时规则定义方式，通过特定的字符串格式，可以精确指定任务在什么时间执行，比如每年、每月、每周、每天的某个时刻等
//Cron 表达式是定时任务的 “时间规则语言”，SpringBoot 支持 6 位或 7 位格式（秒 分 时 日 月 周 [年]），年为可选参数。
    @Scheduled(cron = "0 * * * * ?") // 每分钟执行一次
    public void cronTask() {
        log.info("【Cron任务】执行时间：{},执行线程：{}", LocalDateTime.now(), Thread.currentThread().getName());
    }
}