package top.nql.boot.mp.config.controller;


import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.nql.boot.mp.config.service.OssService;

@RestController
@RequestMapping("/oss")
public class OssController {
    @Resource
    private OssService ossService;
    @RequestMapping("upload")
    public String upload(MultipartFile file)
    {
        return ossService.upload(file);
    }
}
