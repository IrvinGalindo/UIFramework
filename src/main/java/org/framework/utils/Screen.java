package org.framework.utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Screen {
    protected static Logger log = LogManager.getLogger(Screen.class);

    public static String takeScreenShot(WebDriver driver, String suiteName, String testName, String executionTime) {
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            String filePath = Paths.get("img", testName, driver.getTitle() + " page - " + executionTime + ".png").toString();
            String sourcePath = Paths.get("Reports", suiteName, filePath).toString();
            FileUtils.copyFile(screenshotFile, new File(sourcePath));
            log.info("Create screenshot" + testName +" on " + sourcePath);
            return filePath;
        } catch (IOException e) {
            log.info("Screenshot can not be created. TestName: " + testName);
            throw new RuntimeException(e);
        }
    }
}
