package org.framework.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Highlighter {
    private final JavascriptExecutor javascriptExecutor;


    public Highlighter(WebDriver driver) {
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    public void highlight(WebElement element) {
        // javascriptExecutor.executeScript("highlight(arguments[0])", element);
        try {
            javascriptExecutor.executeScript("arguments[0].style.outline='2px solid red';", element);
            Thread.sleep(500);
            javascriptExecutor.executeScript("arguments[0].style.outline='0px';", element);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
