package com.clever.manager;

import com.clever.Constant;
import com.clever.bean.system.EmailSubject;
import com.clever.util.DesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 邮件发送主体工厂
 *
 * @Author xixi
 * @Date 2023-12-26 14:50
 **/
public class JavaMailSenderFactory {

    private final static Logger log = LoggerFactory.getLogger(JavaMailSenderFactory.class);

    private static final Map<String, JavaMailSender> MAIL_SENDER_MAP = new HashMap<>(4);

    /**
     * 获取发送类
     *
     * @param subject 主体
     * @return 发送实体
     */
    public static synchronized JavaMailSender buildSender(EmailSubject subject) {
        if (MAIL_SENDER_MAP.containsKey(subject.getId())) {
            return MAIL_SENDER_MAP.get(subject.getId());
        } else {
            return buildMailSender(subject);
        }
    }

    /**
     * 当邮件主体更新时删除发送主体
     *
     * @param subjectId 主体id
     */
    public static void updateMailSender(String subjectId) {
        MAIL_SENDER_MAP.remove(subjectId);
    }

    /**
     * 不存在则构建
     *
     * @param subject 主体
     * @return 构建后的
     */
    private static JavaMailSender buildMailSender(EmailSubject subject) {
        log.info("buildMailSender-begin, subject={}", subject.getUsername());
        Properties properties = new Properties();
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.ssl.trust", subject.getHost());
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.starttls.enable", "false");
        properties.setProperty("mail.smtp.starttls.required", "false");
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setHost(subject.getHost());
        javaMailSenderImpl.setPort(subject.getPort());
        javaMailSenderImpl.setPassword(DesUtil.safeDecrypt(subject.getPassword(), Constant.KEY));
        javaMailSenderImpl.setUsername(subject.getUsername());
        javaMailSenderImpl.setProtocol(subject.getDriver());
        javaMailSenderImpl.setDefaultEncoding(StandardCharsets.UTF_8.name());
        javaMailSenderImpl.setJavaMailProperties(properties);
        MAIL_SENDER_MAP.put(subject.getId(), javaMailSenderImpl);
        log.info("buildMailSender-end, subject={}", subject.getUsername());
        return javaMailSenderImpl;
    }
}
