package com.cs316.money_mind.helper;

import lombok.Getter;
import lombok.Setter;

/**
 * MailThread
 *
 * @author Sainjargal Ishdorj
 **/

@Getter
@Setter
public class MailThread extends Thread {

    private String subject;
    private String body;
    private String email;

    public MailThread(String body, String email, String subject){
        this.body = body;
        this.email = email;
        this.subject = subject;
    }

    @Override
    public void run() {
        System.out.println("Mail Thread started");
        Helper.sendEmail(body, email, subject);
    }
}