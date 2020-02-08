package org.sjsi.parser.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.sjsi.model.exam.ExamOutcome;
import org.sjsi.parser.ParserEngine;
import org.tinylog.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class XlsParserEngine implements ParserEngine {
    @Override
    public List<Map<String, Object>> parse(File xls) {
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(xls));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;

            int rows; // No of rows
            rows = sheet.getPhysicalNumberOfRows();

            int cols = 0; // No of columns
            int tmp = 0;

            // This trick ensures that we get the data properly even if it doesn't start from first few rows
            for (int i = 0; i < 10 || i < rows; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if (tmp > cols) cols = tmp;
                }
            }
            String examDate = sheet.getRow(0).getCell(1).getStringCellValue();

            for (int r = 8; r < rows; r++) {
                row = sheet.getRow(r);
                if (row != null) {
                    Map<String, Object> parsed = parseRow(row, r);
                    if(parsed != null) {
                        parsed.put("date", examDate);
                        result.add(parsed);
                    }
                }
            }
        } catch (Exception e) {
            Logger.error(e, "Problem z załadowaniem pliku XLS!");
        }

        return result;
    }

    private Map<String, Object> parseRow(HSSFRow row, int line) {
        try {
           return fillProperties(row);
        } catch (Throwable e) {
            Logger.error(e, "Błąd w przetwarzaniu lini " + (line + 1));
            return null;
        }
    }

    private Map<String, Object> fillProperties(HSSFRow row) {
        Map<String, Object> result = new HashMap<>();
        if(StringUtils.isBlank(row.getCell(1).getStringCellValue()))  {
            //BLANK DATA
            return null;
        }
        result.put("name", row.getCell(1).getStringCellValue());
        result.put("lastname", row.getCell(2).getStringCellValue());
        result.put("exam_type", row.getCell(3).getStringCellValue());
        result.put("points", row.getCell(5).getStringCellValue());
        result.put("percent", row.getCell(6).getNumericCellValue() * 100);
        result.put("outcome",  row.getCell(7).getStringCellValue().toLowerCase().equals("zdał") ? ExamOutcome.SUCCESS : ExamOutcome.FAILED);
        result.put("email", row.getCell(8).getStringCellValue());
        result.put("address_name", row.getCell(10).getStringCellValue());
        result.put("address", row.getCell(13).getStringCellValue());
        result.put("post_code", row.getCell(15).getStringCellValue());
        result.put("city", row.getCell(14).getStringCellValue());
        result.put("phone", row.getCell(12).getNumericCellValue());
        return result;
    }
}
