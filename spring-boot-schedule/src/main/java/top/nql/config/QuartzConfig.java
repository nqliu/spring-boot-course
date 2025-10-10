package top.nql.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.nql.job.ExportUserAccount;

/**
 * @author alani
 */
@Configuration
public class QuartzConfig {

    /**
     * 定时任务详情
     */
    @Bean
    public JobDetail exportTaskDetail() {
        return JobBuilder.newJob(ExportUserAccount.class)
                .withIdentity("ExportJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger exportTaskTrigger() {
        // 每周日 18:30:00
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 30 18 ? * SUN");
        // 返回任务触发器
        return TriggerBuilder.newTrigger()
                .forJob(exportTaskDetail())
                .withIdentity("ExportTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
