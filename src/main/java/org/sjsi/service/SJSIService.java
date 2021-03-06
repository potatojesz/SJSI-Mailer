package org.sjsi.service;

import java.net.URISyntaxException;

public interface SJSIService {
    void process(String filePath) throws URISyntaxException;

    void verify(String filePath) throws URISyntaxException;
}
