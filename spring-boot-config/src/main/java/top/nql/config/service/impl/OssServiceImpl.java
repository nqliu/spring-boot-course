package top.config.config.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.config.config.config.OssConfig;
import top.config.config.service.OssService;

import java.io.InputStream;
import java.util.UUID;

@Service
@Slf4j
public  class OssServiceImpl implements OssService {
    @Resource
    private OssConfig ossConfig;
    @Override
    public String upload(MultipartFile file) {
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            // 1.jpg,获取后缀名
            assert originalFilename != null;
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = UUID.randomUUID()+suffix;
            log.info("新文件名：{}",newFileName);
            String endpoint = ossConfig.getEndpoint();
            String bucket = ossConfig.getBucket();
            String accessKey = ossConfig.getAccessKey();
            String secretKey = ossConfig.getSecretKey();
            String dir = ossConfig.getDir();
            OSS ossClient = new OSSClientBuilder().build(endpoint,accessKey,secretKey);
            String uploadPath = dir + newFileName;
            InputStream inputStream;
            try {
                inputStream = file.getInputStream();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            ossClient.putObject(bucket,uploadPath,inputStream);
            ossClient.shutdown();
            return "https://"+ bucket +"."+endpoint+ "/" + uploadPath;
        }
        return "上传失败";
    }
}