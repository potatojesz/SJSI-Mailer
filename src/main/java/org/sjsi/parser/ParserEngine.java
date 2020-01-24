package org.sjsi.parser;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface ParserEngine {
    List<Map<String, Object>> parse(File xls);
}
