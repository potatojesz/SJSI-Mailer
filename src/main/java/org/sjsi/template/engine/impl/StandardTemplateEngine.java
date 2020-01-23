package org.sjsi.template.engine.impl;

import freemarker.core.InvalidReferenceException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.sjsi.template.engine.TemplateEngine;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;

public class StandardTemplateEngine implements TemplateEngine {
    private Configuration cfg;

    public StandardTemplateEngine() {
        configure();
    }

    private void configure() {
        if(cfg == null) {
            cfg = new Configuration();
            cfg.setDefaultEncoding("UTF-8");
            try {
                URL res = getClass().getClassLoader().getResource("templates");
                File file = Paths.get(res.toURI()).toFile();
                cfg.setDirectoryForTemplateLoading(file);
                cfg.setDefaultEncoding("UTF-8");
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            } catch (IOException | URISyntaxException e) {
                Logger.error(e, "Problem z załadowaniem szablonów!");
            }
        }
    }

    public String getContent(String  template, Map<Object, Object> model) {
        try (StringWriter writer = new StringWriter()) {
            Template tpl = cfg.getTemplate(template);
            tpl.process(model, writer);

            return writer.toString();
        } catch (IOException e) {
            Logger.warn(e, "Nie mogę znaleźć szablonu o nazwie " + template);
        } catch (TemplateException e) {
            Logger.warn(e, "Wystąpił błąd w czasie procesowania szablonu " + template);
        }

        return null;
    }
}
