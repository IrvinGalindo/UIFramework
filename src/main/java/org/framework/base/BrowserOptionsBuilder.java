package org.framework.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

public class BrowserOptionsBuilder {

    private static JSONObject parseJSON(String resourceJsonName) throws Exception {
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(new FileReader(resourceJsonName));
    }

    private static JSONObject getCapabilities(String jsonLocation, String browserName) throws Exception {
        JSONObject browserOptionsObject = parseJSON(jsonLocation);
        return (JSONObject) browserOptionsObject.get(browserName.toLowerCase());
    }

    private static Map<String, ?> convertOptionsToList(String resourceJsonName, String browserName) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonLocation = BrowserOptionsBuilder.class.getClassLoader().getResource("Browser/Capabilities/" + resourceJsonName).getPath();
        return objectMapper.readValue(getCapabilities(jsonLocation, browserName).toString(), Map.class);
    }

    public static MutableCapabilities getBrowserOptions(String resourceJsonName, String browserName) throws Exception {
        Map<String, Object> optionsList = (Map<String, Object>) convertOptionsToList(resourceJsonName, browserName);
        ArrayList<String> options = (ArrayList<String>) optionsList.get("options");

        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(options);
                setCapabilities((Map<String, Object>) optionsList.get("capabilities"), chromeOptions);
                return chromeOptions;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments(options);
                setCapabilities((Map<String, Object>) optionsList.get("capabilities"), firefoxOptions);
                return firefoxOptions;
            case "safari":
                SafariOptions safariOptions = new SafariOptions();
                setCapabilities((Map<String, Object>) optionsList.get("capabilities"), safariOptions);
                return safariOptions;
        }

        return new MutableCapabilities();
    }


    private static void setCapabilities(Map<String, Object> capabilities, MutableCapabilities browserOptions) {
        capabilities.forEach(browserOptions::setCapability);
    }
}
