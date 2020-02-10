package org.sjsi.parser.impl;

import org.sjsi.parser.ParserEngine;

import java.io.File;
import java.util.List;
import java.util.Map;

public class XlsxParserEngine implements ParserEngine {
    @Override
    public List<Map<String, Object>> parse(File xls) {
        throw new RuntimeException("Obsługa plików XLSX nie jest zaimplementowana, proszę skonwertować plik do formatu XLS");
    }
}
