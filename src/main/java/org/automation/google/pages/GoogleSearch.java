package org.automation.google.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class GoogleSearch {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//img[@alt='Google']")
    public WebElement logoImg;

    @FindBy(xpath = "//input[@name='q']")
    public WebElement searchInp;

    @FindBy(xpath = "(//input[@name='btnK'])[1]")
    public WebElement searchBtn;

    @FindBy(xpath = "//a[text()='Gmail']")
    public WebElement gmailLnk;


    public GoogleSearch(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void searchInGoogle(String sentenceToSearch) {
        wait.until(ExpectedConditions.visibilityOf(searchBtn));
        assertTrue(searchInp.isDisplayed());
        searchInp.sendKeys(sentenceToSearch);
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        searchBtn.click();
    }

}
