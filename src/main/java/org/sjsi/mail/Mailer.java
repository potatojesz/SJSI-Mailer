package org.sjsi.mail;

import org.sjsi.mail.model.Mail;

import java.util.Collection;

public interface Mailer {
    void send(Mail mail);
    void send(Collection<Mail> mail);
}
