package top.nql.config.controller;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.nql.config.common.ApiResponse;
import top.nql.config.enums.ResultStatus;
import top.nql.config.model.Mail;
import top.nql.config.service.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Resource
    private MailService mailService;
    @PostMapping("/simple")
    public ResponseEntity<ApiResponse<ResultStatus>> sendSimpleMail(@Valid @RequestBody Mail mail) {
        ResultStatus rs=mailService.sendSimpleMail(mail);
        if (rs==ResultStatus.SUCCESS){
            return ResponseEntity.ok(ApiResponse.success("发送成功",rs));
        }
        return ResponseEntity.badRequest().body(ApiResponse.error("发送失败"));
    }
    @PostMapping("/html")
    public ResponseEntity<ApiResponse<ResultStatus>> sendHtmlMail(@Valid @RequestBody Mail mail) {
        ResultStatus rs=mailService.sendHtmlMail(mail);
        return rs==ResultStatus.SUCCESS?ResponseEntity.ok(ApiResponse.success("发送成功",rs)):ResponseEntity.badRequest().body(ApiResponse.error("发送失败"));
    }

}
