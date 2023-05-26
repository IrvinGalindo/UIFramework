package org.automation.google.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchResult {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "a#logo")
    public WebElement googleLogoLnk;

    @FindBy(css = "div[class^='g Ww4FFb']:nth-child(1) h3")
    public WebElement firstSearchLnk;

    public SearchResult(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void checkFirstSearchResultContainsExpectedValueToSearch(String expectedValueToSearch) {
        wait.until(ExpectedConditions.visibilityOf(googleLogoLnk));
        assertTrue(googleLogoLnk.isDisplayed());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstSearchLnk);
        wait.until(ExpectedConditions.visibilityOf(firstSearchLnk));
        assertEquals(firstSearchLnk.getText(), expectedValueToSearch);
    }

}
