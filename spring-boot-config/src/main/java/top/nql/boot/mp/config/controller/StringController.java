package top.nql.boot.mp.config.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.*;
import top.nql.boot.mp.config.model.Result;

import java.util.List;
//字符串处理接口
//用 StrUtil 实现非空判断、拼接、分割。
@RestController
@RequestMapping("/api/string")
public class StringController {

    @PostMapping("/concat")
    public Result<String> concatStrings(@RequestBody List<String> strList) {
        if (CollUtil.isEmpty(strList)) {
            return Result.fail(400, "字符串列表不能为空");
        }
        // 拼接字符串，用逗号分隔
        String result = StrUtil.join(",", strList);
        return Result.success(result);
    }

    @GetMapping("/isBlank")
    public Result<Boolean> checkBlank(@RequestParam String str) {
        // 判断字符串是否为空（空、null、空白符都算）
        boolean isBlank = StrUtil.isBlank(str);
        return Result.success(isBlank);
    }
}