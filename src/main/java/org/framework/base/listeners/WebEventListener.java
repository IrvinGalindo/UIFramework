package org.framework.base.listeners;

import com.aventstack.extentreports.Status;
import org.apache.commons.lang3.StringUtils;
import org.framework.base.CustomExtentReport;
import org.framework.utils.Highlighter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.events.WebDriverListener;
import java.util.Arrays;
import java.util.Collection;

public class WebEventListener implements WebDriverListener {
    
    @Override
    public void beforePerform(WebDriver driver, Collection<Sequence> actions) {
        String actionId = ((Sequence) actions.toArray()[0]).toJson().get("id").toString();
        CustomExtentReport.getExtentTest().log(Status.PASS, "performed action: " + actionId);

    }

    @Override
    public void beforeClick(WebElement element) {
        //tagName = element.getTagName();
    }

    @Override
    public void afterClick(WebElement element) {
        String selectorWithValue = getElementLocator(element);
        CustomExtentReport.getExtentTest().log(Status.PASS, "Click on [" + selectorWithValue + "]");

    }

    @Override
    public void afterSubmit(WebElement element) {
        System.out.println("afterSubmit");
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        element.clear();
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        String selectorWithValue = getElementLocator(element);
        CustomExtentReport.getExtentTest().log(Status.PASS, "Enter " + Arrays.toString(keysToSend) + " on [" + selectorWithValue + "]");

    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        new Highlighter(driver).highlight(result);
    }

    private String getElementLocator(WebElement element) {
        return element.toString().split("->")[1].replace("]}}", "");

    }
    private String getElementIdentifier(WebElement element) {
        String identifier = "";
        try {
            identifier = element.getAttribute("name");
            if (StringUtils.isBlank(identifier)) {
                identifier = element.getAttribute("id");
            }
            if (StringUtils.isBlank(identifier) && element.getTagName().equalsIgnoreCase("img")) {
                identifier = element.getAttribute("alt");
            }
            if (StringUtils.isBlank(identifier)) {
                identifier = element.getAccessibleName();
            }
        } catch (Exception exception) {
            //log.warn("Element identify could be extracted from element: " + getElementLocator(element));
        }

        return identifier;
    }
}
