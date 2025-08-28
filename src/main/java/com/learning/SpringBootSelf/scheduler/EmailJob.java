package com.learning.SpringBootSelf.scheduler;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class EmailJob extends QuartzJobBean {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap= context.getMergedJobDataMap();

        String email= jobDataMap.getString("email");
        String subject= jobDataMap.getString("subject");
        String body= jobDataMap.getString("body");

        sendMail(mailProperties.getUsername(), email, subject, body);

    }
    private void sendMail(String from, String email, String subject, String body){

        try{
            MimeMessage message= javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper= new MimeMessageHelper(message, StandardCharsets.UTF_8.toString());
            messageHelper.setSubject(subject);
            messageHelper.setText(body, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(email);

            javaMailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
