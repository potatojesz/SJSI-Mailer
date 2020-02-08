package org.sjsi.parser.impl;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.sjsi.parser.ParserEngine;
import org.tinylog.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XlsxParserEngine implements ParserEngine {
    @Override
    public List<Map<String, Object>> parse(File xls) {
        throw new RuntimeException("Obsługa plików XLSX nie jest zaimplementowana, proszę skonwertować plik do formatu XLS", new NotImplementedException());
    }
}
