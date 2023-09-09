package com.mycompany;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Environment {

    public static final String BASE_URL = "https://www.saucedemo.com";
    public static final String USER_NAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";
    public static final int EXPLICIT_WAIT = 10;

    private static AppiumDriverLocalService service;

    private static HashMap<String,String> sessionMap = new HashMap<>();

    public static HashMap<String,String> getSessionMap(){
        return sessionMap;
    }

    public static void addSession(String key,String value){
        sessionMap.put(key,value);
    }

    public static void clear(){
        sessionMap.clear();
    }
    public static IOSDriver initializeIOSDriver() {
        try {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            URL jsonResource = Environment.class.getClassLoader().getResource("capabilities.json");
            String content = new String(Files.readAllBytes(Paths.get(jsonResource.toURI())));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(content);
            JsonNode iosAppConfig = rootNode.get("iOS");
            iosAppConfig.fieldNames().forEachRemaining(fieldName -> {
                capabilities.setCapability(fieldName, iosAppConfig.get(fieldName).asText());
            });
            IOSDriver driver = new IOSDriver(new URL("http://hub-cloud.browserstack.com/wd/hub"), capabilities);
            return driver;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static AndroidDriver initializeAndroidDriver() {
        try {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            URL jsonResource = Environment.class.getClassLoader().getResource("capabilities.json");
            String content = new String(Files.readAllBytes(Paths.get(jsonResource.toURI())));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(content);
            JsonNode androidAppConfig = rootNode.get("Android");
            androidAppConfig.fieldNames().forEachRemaining(fieldName -> {
                capabilities.setCapability(fieldName, androidAppConfig.get(fieldName).asText());
            });
            AndroidDriver driver = new AndroidDriver(new URL("http://hub-cloud.browserstack.com/wd/hub"), capabilities);
            return driver;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static IOSDriver insitalizeSafariWebDriver() {
        IOSDriver driver = null;
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            URL jsonResource = Environment.class.getClassLoader().getResource("capabilities.json");
            String content = new String(Files.readAllBytes(Paths.get(jsonResource.toURI())));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(content);
            JsonNode safariAppConfig = rootNode.get("Safari");
            safariAppConfig.fieldNames().forEachRemaining(fieldName -> {
                capabilities.setCapability(fieldName, safariAppConfig.get(fieldName).asText());
            });

            driver = new IOSDriver(new URL("http://hub-cloud.browserstack.com/wd/hub"), capabilities);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver;


    }

    public static AndroidDriver insitalizeChromeWebDriver(){
        AndroidDriver driver = null;
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            URL jsonResource = Environment.class.getClassLoader().getResource("capabilities.json");
            String content = new String(Files.readAllBytes(Paths.get(jsonResource.toURI())));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(content);
            JsonNode chromeWebconfig = rootNode.get("ChromeWeb");
            chromeWebconfig.fieldNames().forEachRemaining(fieldName -> {
                capabilities.setCapability(fieldName, chromeWebconfig.get(fieldName).asText());
            });

            driver = new AndroidDriver(new URL("http://hub-cloud.browserstack.com/wd/hub"), capabilities);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver;


    }


}

