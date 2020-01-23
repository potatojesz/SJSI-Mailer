package org.sjsi.template.engine;

import java.util.Map;

public interface TemplateEngine {
    String getContent(String template, Map<Object, Object> model);
}
