package top.config.config.service;


import top.config.config.enums.ResultStatus;
import top.config.config.model.Mail;

public interface MailService {

    ResultStatus sendSimpleMail(Mail mail);
    ResultStatus sendHtmlMail(Mail mail);
}
