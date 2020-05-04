package com.example.myemailapp;

import android.widget.EditText;
import com.sun.mail.imap.IMAPFolder;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static org.jsoup.Jsoup.parse;

public class Receiver {

    public static List<Inbox> inboxList = new ArrayList<>();
    private String username, password;

    Receiver(AccountInfo accountInfo) {
        username = accountInfo.getUsername();
        password = accountInfo.getPassword();
    }

    public void receive() throws MessagingException, IOException {

        Properties props = System.getProperties();
        props.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.pop3.socketFactory.fallback", "false");
        props.setProperty("mail.pop3.port", "995");
        props.setProperty("mail.pop3.socketFactory.port", "995");

        Session session = Session.getInstance(props,null);

        URLName urln = new URLName("pop3","pop.gmail.com",995,null, username, password);

        Store store = session.getStore(urln);
        store.connect();

        Folder inbox = store.getFolder("Inbox");
        inbox.open(Folder.READ_ONLY);

        Message[] message = inbox.getMessages();
        System.out.println(message.length);
        for(int i = 1; i < message.length; i++) {
            // System.out.println(message[message.length-i].getSubject());
            Message msg = message[i];

            String from = MimeUtility.decodeText(msg.getFrom()[0].toString());
            String subject = msg.getSubject();
            String date = msg.getSentDate().toString();

            String content = "";
            MimeMultipart mimeMultipart;

            if(msg.isMimeType("multipart/*")) {
                mimeMultipart = (MimeMultipart) msg.getContent();
                int count = mimeMultipart.getCount();
                for(int j = 0; j < count; j++) {
                    BodyPart bodyPart = mimeMultipart.getBodyPart(j);
                    if(bodyPart.isMimeType("text/*")) {
                        String html = (String) bodyPart.getContent();
                        content += "\n" + parse(html).text();
                    } else {
                        content += "\n";
                    }
                }
            }
            else if(msg.isMimeType("text/html")) {
                String html = (String) msg.getContent();
                content = parse(html).text();
            }else if(msg.isMimeType("text/plain")) {
                content = msg.getContent().toString();
            }

            inboxList.add(new Inbox(from, subject, date, content));
        }

        inbox.close(false);
        store.close();
    }

}
