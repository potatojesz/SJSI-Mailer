package org.sjsi.template.impl;

import com.google.common.collect.ImmutableMap;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sjsi.Constants;
import org.sjsi.template.TemplateEngine;

import java.util.Map;

public class StandardTemplateEngineTest {
    private static TemplateEngine engine;

    @BeforeClass
    public static void beforeClass() {
        engine = new StandardTemplateEngine();
    }

    @Test
    public void getContentTest() {
        String contentPass = engine.getContent(Constants.PASS_TEMPLATE, preparePassModel());
        Assert.assertTrue(contentPass.contains("Szanowny/a Panie/Pani"));

        contentPass = engine.getContent(Constants.PASS_TEMPLATE, prepareNotFullPassModel());
        Assert.assertNull(contentPass);

        contentPass = engine.getContent(Constants.FAIL_TEMPLATE, preparePassModel());
        Assert.assertTrue(contentPass.contains("Z przykrością informujemy, że nie"));
    }

    private Map<Object, Object> preparePassModel() {
        return ImmutableMap.builder()
                .put("exam_type", "ISQTB Poziom Podstawowy")
                .put("date", "02-08-2020")
                .put("points", 27)
                .put("percent", 80.5)
                .put("name", "Tomasz".toUpperCase())
                .put("lastname", "Klimczak".toUpperCase())
                .put("address_name", "Tomasz Klimczak")
                .put("address", "Kowalska 22/2")
                .put("post_code", "45-316")
                .put("city", "Andrychów")
                .put("phone", "792125009")
                .build();
    }

    private Map<Object, Object> prepareNotFullPassModel() {
        return ImmutableMap.builder()
                .put("sex", 0)
                .put("exam_type", "ISQTB Poziom Podstawowy")
                .put("points", 27)
                .put("name", "Tomasz".toUpperCase())
                .put("lastname", "Klimczak".toUpperCase())
                .put("address_name", "Tomasz Klimczak")
                .put("address", "Kowalska 22/2")
                .put("city", "Andrychów")
                .put("phone", "792125009")
                .build();
    }
}
