package org.framework.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.base.listeners.AlertWebDriverListener;
import org.framework.base.listeners.NavigationWebDriverListener;
import org.framework.base.listeners.WebEventListener;
import org.framework.constants.BrowserTypes;
import org.framework.constants.DefaultWaits;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import java.time.Duration;
import java.util.ArrayList;

public class DriverHandler {
    protected Logger log = LogManager.getLogger(DriverHandler.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public WebDriver initialization(String browserName, String capabilitiesFileName) throws Exception {
        //"GoogleTestCaps.json"
        MutableCapabilities mutableCapabilities = capabilitiesFileName != null ? BrowserOptionsBuilder.getBrowserOptions(capabilitiesFileName, browserName)
                : new MutableCapabilities(); // pending to fix when no caps
        WebDriver driver;
        switch (browserName.toLowerCase()) {
            case BrowserTypes.CHROME:
            case BrowserTypes.GOOGLE_CHROME:
                driver = WebDriverManager.chromedriver().capabilities(mutableCapabilities).create();
                break;
            case BrowserTypes.IE:
                driver = WebDriverManager.iedriver().capabilities(mutableCapabilities).create();
                break;
            case BrowserTypes.FIREFOX:
                driver = WebDriverManager.firefoxdriver().capabilities(mutableCapabilities).create();
                break;
            case BrowserTypes.SAFARI:
                driver = WebDriverManager.safaridriver().capabilities(mutableCapabilities).create();
                break;
            default:
                throw new IllegalArgumentException("driver can not be initialized, the property called browser can not be found " + browserName);
        }

        ArrayList<WebDriverListener> listeners = getWebDriverListeners();
        for (WebDriverListener listener : listeners) {
            driver = new EventFiringDecorator(listener).decorate(driver);
        }


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DefaultWaits.IMPLICIT_WAIT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(DefaultWaits.PAGE_LOAD_TIMEOUT));
        setDriver(driver);

        return driver;

    }

    private ArrayList<WebDriverListener> getWebDriverListeners() {
        ArrayList<WebDriverListener> listeners = new ArrayList<>();
        listeners.add(new AlertWebDriverListener());
        listeners.add(new NavigationWebDriverListener());
        listeners.add(new WebEventListener());
        return listeners;
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public void setDriver(WebDriver driver) {
        DriverHandler.driverThreadLocal.set(driver);
    }

    public void closeDriver() {
        if (getDriver() != null) {
            getDriver().quit();
        }
        driverThreadLocal.remove();
    }


}
