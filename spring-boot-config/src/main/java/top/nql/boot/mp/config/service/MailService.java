package top.nql.boot.mp.config.service;


import top.nql.boot.mp.config.enums.ResultStatus;
import top.nql.boot.mp.config.model.Mail;

public interface MailService {

    ResultStatus sendSimpleMail(Mail mail);
    ResultStatus sendHtmlMail(Mail mail);
}
