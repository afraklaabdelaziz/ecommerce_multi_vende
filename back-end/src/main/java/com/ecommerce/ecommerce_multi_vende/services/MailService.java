package com.ecommerce.ecommerce_multi_vende.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private  JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public  void sendEmail(String toEmail,String body,String subject){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setText(body);
        message.setTo(toEmail);
        message.setFrom("afraklaabdelaziz@gmail.com");
        javaMailSender.send(message);
        System.out.println("success");
    }
}
