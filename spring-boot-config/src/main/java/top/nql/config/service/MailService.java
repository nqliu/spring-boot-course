package top.nql.config.service;


import top.nql.config.enums.ResultStatus;
import top.nql.config.model.Mail;

public interface MailService {

    ResultStatus sendSimpleMail(Mail mail);
    ResultStatus sendHtmlMail(Mail mail);
}
