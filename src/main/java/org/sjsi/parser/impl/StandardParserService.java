package org.sjsi.parser.impl;

import org.apache.commons.io.FilenameUtils;
import org.sjsi.model.parser.FileExtension;
import org.sjsi.parser.ParserEngine;
import org.sjsi.parser.ParserService;

import java.io.File;
import java.util.List;
import java.util.Map;

public class StandardParserService implements ParserService {
    @Override
    public List<Map<String, Object>> parse(File file) {
        return pickEngine(FileExtension.valueOf(FilenameUtils.getExtension(file.getName()).toUpperCase())).parse(file);
    }

    private ParserEngine pickEngine(FileExtension fileExtension) {
        switch(fileExtension) {
            case XLS:
                return new XlsParserEngine();
            case XLSX:
                return new XlsxParserEngine();
            default:
                throw new UnsupportedOperationException("Nie obsługiwany typ pliku.");
        }
    }
}
