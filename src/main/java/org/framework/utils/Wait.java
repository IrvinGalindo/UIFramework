package org.framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait {
    private static final int SECONDS = 10;

    public static <T> void until(WebDriver driver, ExpectedCondition<T> expectedCondition, int seconds) {
        // CustomExtentReport.getExtentTest().log(Status.INFO, "Waiting until: " + expectedCondition);
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        webDriverWait.until(expectedCondition);
    }

    public static <T> void until(WebDriver driver, ExpectedCondition<T> expectedCondition) {
        until(driver, expectedCondition, SECONDS);
    }

    public static void thread(long seconds) {
        try {
            //CustomExtentReport.getExtentTest().log(Status.INFO, "Waiting " + seconds + "seconds");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
