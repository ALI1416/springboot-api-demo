package com.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class Mail {

    private static JavaMailSender javaMailSender;

    //JavaMailSender自动注入可能会报错，请不用管
    @Autowired
    private Mail(JavaMailSender javaMailSender) {
        Mail.javaMailSender = javaMailSender;
    }

    public static void sendMail1() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1416978277@qq.com");
        message.setTo("1416978277@qq.com");
        message.setSubject("这是标题");
        message.setText("这是内容");
        javaMailSender.send(message);
    }

    public static void sendMail2() {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        try {
            messageHelper.setSubject("标题");
            messageHelper.setFrom("1416978277@qq.com");
            messageHelper.setTo("1416978277@qq.com");
            messageHelper.setText("<h1>标题</h1><br/><p>这是内容</p>", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        javaMailSender.send(messageHelper.getMimeMessage());
    }
}
