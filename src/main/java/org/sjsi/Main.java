package org.sjsi;

import org.sjsi.service.SJSIService;
import org.sjsi.service.impl.StandardSJSIService;

import java.net.URISyntaxException;

public class Main {
    private static SJSIService service = new StandardSJSIService();
    public static void main(String[] args) throws URISyntaxException {
        final String filePath = args[0];
        if (args.length > 1) {
            service.verify(filePath);
        } else {
            service.process(filePath);
        }
    }
}
