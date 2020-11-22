package com.demo.util;

import com.demo.constant.MailConstant;
import com.demo.tool.ThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * <h1>邮件工具类</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Component
public class MailUtils {

    /**
     * 发送邮件实例
     */
    private static JavaMailSender javaMailSender;

    /**
     * JavaMailSender自动注入可能会报错，请不要删除@Autowired
     */
    @Autowired
    private MailUtils(JavaMailSender javaMailSender) {
        MailUtils.javaMailSender = javaMailSender;
    }

    /**
     * 发送普通邮件
     *
     * @param to      发送到
     * @param subject 主题
     * @param text    内容
     */
    public static void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(MailConstant.USERNAME);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        // 使用线程池（邮件发送速度比较慢）
        ThreadPool.execute(() -> javaMailSender.send(message));
    }

    /**
     * 发送HTML邮件
     *
     * @param to      发送到
     * @param subject 主题
     * @param text    内容
     */
    public static void sendMailHtml(String to, String subject, String text) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        try {
            messageHelper.setFrom(MailConstant.USERNAME);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用线程池（邮件发送速度比较慢）
        ThreadPool.execute(() -> javaMailSender.send(messageHelper.getMimeMessage()));
    }
}
