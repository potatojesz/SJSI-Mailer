package org.sjsi.template;

import java.util.Map;

public interface TemplateEngine {
    String getContent(String template, Map<Object, Object> model);
}
