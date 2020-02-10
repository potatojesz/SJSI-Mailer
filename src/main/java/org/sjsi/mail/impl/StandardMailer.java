package org.sjsi.mail.impl;

import org.sjsi.mail.MailProperties;
import org.sjsi.mail.Mailer;
import org.sjsi.mail.model.Mail;
import org.tinylog.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Collection;

public class StandardMailer implements Mailer {
    final String username;
    final String password;
    final String sender;
    final String topic;

    public StandardMailer() {
        sender = (String) MailProperties.PROPERTIES.get("sender");
        username = (String) MailProperties.PROPERTIES.get("username");
        password = (String) MailProperties.PROPERTIES.get("password");
        topic = (String) MailProperties.PROPERTIES.get("topic");
    }

    @Override
    public void send(Collection<Mail> mails) {
        Session session = Session.getInstance(MailProperties.PROPERTIES,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        for(Mail mail : mails) {
            send(mail, session);
        }
    }

    @Override
    public void send(Mail mail) {
        Session session = Session.getInstance(MailProperties.PROPERTIES,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        send(mail, session);
    }

    private void send(Mail mail, Session session) {
        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(sender));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mail.getAddress()));

            // Set Subject: header field
            message.setSubject(topic);

            // Now set the actual message
            message.setText(mail.getContent());

            // Send message
            Transport.send(message);

            Logger.info("Wiadomość poprawnie wysłana do " + mail.getAddress());

        } catch (MessagingException e) {
            throw new RuntimeException("Blad podczas wyslania emaila do " + mail.getAddress(), e);
        }
    }
}
