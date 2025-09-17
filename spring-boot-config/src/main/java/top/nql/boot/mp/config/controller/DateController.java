package top.nql.boot.mp.config.controller;

import cn.hutool.core.date.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.nql.boot.mp.config.model.Result;

import java.util.Date;
// 日期时间处理接口
// 用 DateUtil 实现日期格式化、增减、解析。
@RestController
@RequestMapping("/date")
public class DateController {

    @GetMapping("/format")
    public Result<String> formatDate(@RequestParam String dateStr, @RequestParam String pattern) {
        try {
            // 解析字符串为日期
            Date date = DateUtil.parse(dateStr, pattern);
            // 格式化为 "yyyy-MM-dd HH:mm:ss"
            String formatted = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
            return Result.success(formatted);
        } catch (Exception e) {
            return Result.fail(500, "日期处理失败：" + e.getMessage());
        }
    }

    @GetMapping("/offset")
    public Result<Date> offsetDate(@RequestParam Date date, @RequestParam Integer days) {
        // 日期增减（days 为正加，为负减）
        Date offsetDate = DateUtil.offsetDay(date, days);
        return Result.success(offsetDate);
    }
}