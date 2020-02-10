package org.sjsi.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

public class MailProperties {
    public static Properties PROPERTIES = new Properties();

    static {
        InputStream input = null;
        try {
            final String filePath = "mail.properties";
            URL res = MailProperties.class.getClassLoader().getResource(filePath);
            File file = null;
            if (res != null) {
                file = Paths.get(res.toURI()).toFile();
            }
            if (file == null || !file.exists()) {
                file = new File(filePath);
            }
            if (file.exists()) {
                input = new FileInputStream(file);
                PROPERTIES.load(input);
            }
        } catch (Throwable ex) {
            throw new RuntimeException("Blad przy wczytywaniu ustawien skrzynki email.", ex);
        } finally {
            if(input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    throw new RuntimeException("Blad przy wczytywaniu ustawien skrzynki email.", e);
                }
            }
        }
    }
}
