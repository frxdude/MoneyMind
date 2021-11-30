package com.cs316.money_mind.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Helper
 *
 * @author Sainjargal Ishdorj
 **/
@Component
public class Helper {

    MessageSource messageSource;

    Environment NonEnv;

    static Environment env;

    public Helper() {
    }

    @Autowired
    public Helper(MessageSource messageSource, Environment nonEnv) {
        this.messageSource = messageSource;
        NonEnv = nonEnv;
    }

    public static void sendEmail(String body, String email, String subject) {
        final String username = env.getProperty("spring.mail.username");
        final String password = env.getProperty("spring.mail.password");

        Properties prop = new Properties();
        prop.put("mail.smtp.host", env.getProperty("spring.mail.host"));
        prop.put("mail.smtp.port", 587);
        prop.put("mail.smtp.ssl.trust", env.getProperty("spring.mail.host"));
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            assert username != null;
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject(subject);
            message.setContent(body, "text/html;charset=utf-8");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("Mail sent");
    }

    @PostConstruct
    private void init() {
        env = this.NonEnv;
    }
}