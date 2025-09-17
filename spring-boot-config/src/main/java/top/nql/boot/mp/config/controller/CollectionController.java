package top.nql.boot.mp.config.controller;

import cn.hutool.core.collection.CollUtil;
import org.springframework.web.bind.annotation.*;
import top.nql.boot.mp.config.model.Result;

import java.util.List;
import java.util.stream.Collectors;
//集合处理接口
//用 CollUtil 实现集合判空、合并、排序。
@RestController
@RequestMapping("/collection")
public class CollectionController {

    @GetMapping("/merge")
    public Result<List<String>> mergeLists(@RequestBody List<List<String>> listOfLists) {
        if (CollUtil.isEmpty(listOfLists)) {
            return Result.fail(400, "集合列表不能为空");
        }
        // 使用Stream API合并多个集合
        List<String> merged = listOfLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return Result.success(merged);
    }

    @PostMapping("/sort")
    public Result<List<String>> sortList(@RequestBody List<String> list) {
        if (CollUtil.isEmpty(list)) {
            return Result.fail(400, "集合不能为空");
        }
        // 集合排序 - 修复方案2：使用Stream API
        List<String> sorted = list.stream()
                .sorted()
                .collect(Collectors.toList());
        return Result.success(sorted);
    }
}
