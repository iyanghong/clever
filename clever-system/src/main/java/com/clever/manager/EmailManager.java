package com.clever.manager;

import com.clever.bean.system.EmailSubject;
import com.clever.bean.system.EmailTemplate;
import com.clever.service.EmailSubjectService;
import com.clever.service.RedisService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Author xixi
 * @Date 2023-12-26 14:49
 **/
@Component
public class EmailManager {

    private final EmailSubjectService emailSubjectService;

    public EmailManager(EmailSubjectService emailSubjectService) {
        this.emailSubjectService = emailSubjectService;
    }

    /**
     * 发送验证码邮件
     *
     * @param receiveEmail 接收邮箱
     */
    public void sendEmail(String receiveEmail, EmailTemplate emailTemplate) throws MessagingException {
        EmailSubject emailSubject = emailSubjectService.selectById(emailTemplate.getSubjectId());
        JavaMailSender emailSender = JavaMailSenderFactory.buildSender(emailSubject);
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        //设置发件邮箱
        mimeMessage.setFrom(emailSubject.getUsername());
        //接收邮箱
        mimeMessageHelper.setTo(receiveEmail);
        mimeMessageHelper.setSubject(emailSubject.getSubjectName());
        mimeMessageHelper.setText(emailTemplate.getContent(), true);
        //发送
        emailSender.send(mimeMessage);
    }
}
