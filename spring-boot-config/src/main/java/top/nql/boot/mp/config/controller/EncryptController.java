package top.nql.boot.mp.config.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.nql.boot.mp.config.model.Result;
//加密处理接口
//用 SecureUtil 实现 MD5、AES 加密。
@RestController
@RequestMapping("/api/encrypt")
public class EncryptController {

    private static final String AES_KEY = "1234567890123456"; // AES 密钥（16 位）

    @GetMapping("/md5")
    public Result<String> md5Encrypt(@RequestParam String content) {
        if (StrUtil.isBlank(content)) {
            return Result.fail(400, "加密内容不能为空");
        }
        // MD5 加密
        String md5 = SecureUtil.md5(content);
        return Result.success(md5);
    }

    @GetMapping("/aes")
    public Result<String> aesEncrypt(@RequestParam String content) {
        if (StrUtil.isBlank(content)) {
            return Result.fail(400, "加密内容不能为空");
        }
        // AES 加密（先加密为字节，再转 Base64 方便传输）
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, AES_KEY.getBytes());
        String encrypted = aes.encryptBase64(content);
        return Result.success(encrypted);
    }
}
