package org.automation.google;

import org.automation.google.pages.GoogleSearch;
import org.automation.google.pages.SearchResult;
import org.framework.base.TestBase;
import org.framework.utils.Wait;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class GoogleSearchTests extends TestBase {

    @Test
    public void basicGoogleSearch() {
        final String expectedValueToSearch = "Hello World";
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://www.google.com");

        GoogleSearch googleSearchGoogle = new GoogleSearch(driver);
        webDriverWait.until(ExpectedConditions.visibilityOf(googleSearchGoogle.logoImg));
        assertTrue(googleSearchGoogle.logoImg.isDisplayed());
        googleSearchGoogle.searchInp.sendKeys(expectedValueToSearch);

        Actions action = new Actions(driver);
        action.moveToElement(googleSearchGoogle.searchBtn).click().perform();


       /* SearchResult searchResult = new SearchResult(driver);
        webDriverWait.until(ExpectedConditions.visibilityOf(searchResult.googleLogoLnk));
        assertTrue(searchResult.googleLogoLnk.isDisplayed());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchResult.firstSearchLnk);
        assertTrue(searchResult.firstSearchLnk.getText().contains(expectedValueToSearch));*/

    }

    @Test(enabled = false)
    public void invalidExpectedResultGoogleSearch() {
        final String expectedValueToSearch = "Hello World";
        driver.get("https://www.google.com");

        GoogleSearch googleSearchGoogle = new GoogleSearch(driver);
        googleSearchGoogle.searchInGoogle("Hello World");

        SearchResult searchResult = new SearchResult(driver);
        searchResult.checkFirstSearchResultContainsExpectedValueToSearch(expectedValueToSearch);

    }

    @Test()
    public void multipleOpenedTabs() {
        driver.get("https://www.google.com");
        Wait.thread(2);

     /*   driver.get("https://www.amazon.com");
        Wait.thread(2);
        driver.navigate().back();
        Wait.thread(2);
        driver.navigate().forward();
        Wait.thread(2);
        driver.navigate().refresh();*/

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.google.com");
        //driver.switchTo().newWindow(WindowType.TAB);
        driver.close();
        Wait.thread(2);

    }

    @Test(enabled = false)
    public void multipleOpenedWindows() {
        driver.get("https://www.google.com");

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.switchTo().newWindow(WindowType.WINDOW);
        Wait.thread(2);
        driver.close();
        Wait.thread(2);

    }

    @Test(enabled = false)
    public void selectTest() {
        driver.get("https://demoqa.com/select-menu");
        WebElement element = driver.findElement(By.id("oldSelectMenu"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Select select = new Select(element);
        select.selectByIndex(3);
        System.out.println("test");
    }

    @Test
    public void alertTest() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://demoqa.com/alerts");
        WebElement element = driver.findElement(By.id("promtButton"));
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Hello");
        Wait.thread(10);
       // alert.accept();

        System.out.println("test");
    }

}
