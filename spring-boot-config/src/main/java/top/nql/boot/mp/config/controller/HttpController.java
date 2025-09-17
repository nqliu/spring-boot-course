package top.nql.boot.mp.config.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.*;
import top.nql.boot.mp.config.model.Result;

import java.util.Map;
//HTTP 请求接口
//用 HttpUtil 实现 GET、POST 请求。

@RestController
@RequestMapping("/api/http")
public class HttpController {

    @GetMapping("/get")
    public Result<String> httpGet(@RequestParam String url) {
        if (StrUtil.isBlank(url)) {
            return Result.fail(400, "请求 URL 不能为空");
        }
        // 发送 GET 请求
        String result = HttpUtil.get(url);
        return Result.success(result);
    }

    @PostMapping("/post")
    public Result<String> httpPost(@RequestParam String url, @RequestBody Map<String, Object> params) {
        if (StrUtil.isBlank(url)) {
            return Result.fail(400, "请求 URL 不能为空");
        }
        // 发送 POST 请求（参数为 JSON）
        String result = HttpUtil.post(url, JSONUtil.toJsonStr(params));
        return Result.success(result);
    }
}