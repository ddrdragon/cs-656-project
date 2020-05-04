package com.example.myemailapp;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Sender {

    private String username, password;
    private String address, subject, message;

    Sender(AccountInfo accountInfo) {
        username = accountInfo.getUsername();
        password = accountInfo.getPassword();
        address = accountInfo.getAddress();
        subject = accountInfo.getSubject();
        message = accountInfo.getMessage();
    }

    public void sendMail() throws MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        Session session = Session.getInstance(properties, authenticator);

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(username));
        mimeMessage.addRecipients(MimeMessage.RecipientType.TO, address);
        mimeMessage.setSubject(subject);
        mimeMessage.setText(message);
        Transport.send(mimeMessage);
    }


}
