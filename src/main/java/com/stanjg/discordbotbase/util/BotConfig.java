package com.stanjg.discordbotbase.util;

import java.io.*;
import java.util.Properties;

public class BotConfig {

    private static Properties properties;

    public BotConfig() {

        loadProperties();

    }

    private void loadProperties() {

        properties = new Properties();

        File file = new File("config.properties");

        if (!file.exists())
            createFile();

        try (FileInputStream stream = new FileInputStream(file)) {

            properties.load(stream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createFile() {

        try (OutputStream out = new FileOutputStream("config.properties")) {

            Properties props = new Properties();

            // Define default properties
            props.setProperty("bot-token", "");
            props.setProperty("embed-color", "FFFFFF");
            props.setProperty("footer-text", "");

            props.store(out, null);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getProperty(Options option) {
        return properties.getProperty(option.getKey());
    }

    public enum Options {

        BOT_TOKEN("bot-token"), COLOR("embed-color"), FOOTER_TEXT("footer-text");

        String key;

        Options(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

}
