package org.sjsi;

import org.sjsi.service.SJSIService;
import org.sjsi.service.impl.StandardSJSIService;

import java.net.URISyntaxException;

public class Main {
    private static SJSIService service = new StandardSJSIService();
    public static void main(String[] args) throws URISyntaxException {
	    service.process("W:\\projects\\java\\SJSI-Mailer\\src\\test\\resources\\test_file.xls");
    }
}
