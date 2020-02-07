package org.sjsi.parser.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sjsi.parser.ParserEngine;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class StandardParserEngineTest {
    private static ParserEngine engine;

    @BeforeClass
    public static void beforeClass() {
        engine = new StandardParserEngine();
    }

    @Test
    public void testFile() throws URISyntaxException {
        URL res = getClass().getClassLoader().getResource("test_file.xls");
        File file = Paths.get(res.toURI()).toFile();
        Assert.assertNotNull(file);
        List<Map<String, Object>> test = engine.parse(file);
        Assert.assertNotNull(test);
    }
}
