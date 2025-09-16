package top.nql.config.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    /**
     * 文件上传
     */
    String upload(MultipartFile file);

}
