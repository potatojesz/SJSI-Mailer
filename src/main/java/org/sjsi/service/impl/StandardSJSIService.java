package org.sjsi.service.impl;

import org.apache.commons.lang3.tuple.Pair;
import org.sjsi.model.Constants;
import org.sjsi.model.exam.ExamOutcome;
import org.sjsi.parser.ParserEngine;
import org.sjsi.parser.ParserService;
import org.sjsi.parser.impl.StandardParserService;
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

    @Override
    public void process(String filePath) throws URISyntaxException {
        List<Pair<String, String>> messages = getMessages(filePath);
        for(Pair<String, String> pair : messages) {
            final String email = pair.getKey();
            final String message = pair.getValue();

            System.out.println("Wyślę email do " + email + " o tresci" + message);
        }
    }

    private List<Pair<String, String>> getMessages(String filePath) throws URISyntaxException {
        List<Pair<String, String>> messages = new ArrayList<>();
        URL res = getClass().getClassLoader().getResource("test_file.xls");
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
                messages.add(Pair.of(String.valueOf(map.get("email")), templateEngine.getContent(template, map)));
            }
        } else {
            throw new RuntimeException("Brak pliku " + filePath);
        }

        return messages;
    }
}
