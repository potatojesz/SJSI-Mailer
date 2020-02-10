package org.sjsi.service.impl;

import org.sjsi.Constants;
import org.sjsi.mail.Mailer;
import org.sjsi.mail.impl.StandardMailer;
import org.sjsi.mail.model.Mail;
import org.sjsi.parser.ParserService;
import org.sjsi.parser.impl.StandardParserService;
import org.sjsi.parser.model.ExamOutcome;
import org.sjsi.service.SJSIService;
import org.sjsi.template.TemplateEngine;
import org.sjsi.template.impl.StandardTemplateEngine;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StandardSJSIService implements SJSIService {
    private ParserService parser = new StandardParserService();
    private TemplateEngine templateEngine = new StandardTemplateEngine();
    private Mailer mailer = new StandardMailer();

    @Override
    public void process(String filePath) throws URISyntaxException {
        List<Mail> mails = getMails(filePath);
        for(Mail mail : mails) {
           mailer.send(mail);
        }
    }

    private List<Mail> getMails(String filePath) throws URISyntaxException {
        List<Mail> mails = new ArrayList<>();
        URL res = getClass().getClassLoader().getResource(filePath);
        File file = null;
        if (res != null) {
            file = Paths.get(res.toURI()).toFile();
        }
        if(file == null || !file.exists()) {
            file = new File(filePath);
        }
        if(file.exists()) {
            List<Map<String, Object>> paramsList = parser.parse(file);
            for(Map map : paramsList) {
                final String template;
                ExamOutcome outcome = (ExamOutcome) map.get("outcome");
                if(ExamOutcome.SUCCESS.equals(outcome)) {
                    template = Constants.PASS_TEMPLATE;
                } else if (ExamOutcome.FAILED.equals(outcome)) {
                    template = Constants.FAIL_TEMPLATE;
                } else {
                    throw new RuntimeException("Brak rezultatu dla egazminu");
                }
                mails.add(new Mail((String) map.get("email"), templateEngine.getContent(template, map)));
            }
        } else {
            throw new RuntimeException("Brak pliku " + filePath);
        }

        return mails;
    }
}
