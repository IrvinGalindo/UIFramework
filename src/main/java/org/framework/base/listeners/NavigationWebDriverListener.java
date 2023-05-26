package org.framework.base.listeners;

import com.aventstack.extentreports.Status;
import org.framework.base.CustomExtentReport;
import org.framework.base.DriverHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.Method;

public class NavigationWebDriverListener implements WebDriverListener {


    @Override
    public void afterGet(WebDriver driver, String url) {
        CustomExtentReport.getExtentTest().log(Status.PASS, "Navigate to " + url);

    }

    @Override
    public void afterTo(WebDriver.Navigation navigation, String url) {
        CustomExtentReport.getExtentTest().log(Status.PASS, "Navigate to " + url);
    }

    @Override
    public void afterBack(WebDriver.Navigation navigation) {
        WebDriver driver = DriverHandler.getDriver();
        CustomExtentReport.getExtentTest().log(Status.PASS, "Go back to " + driver.getCurrentUrl());

    }

    @Override
    public void afterForward(WebDriver.Navigation navigation) {
        WebDriver driver = DriverHandler.getDriver();
        CustomExtentReport.getExtentTest().log(Status.PASS, "Go forward to " + driver.getCurrentUrl());
    }

    @Override
    public void afterRefresh(WebDriver.Navigation navigation) {
        CustomExtentReport.getExtentTest().log(Status.PASS, "Refresh page");
    }

    @Override
    public void afterAnyCall(Object target, Method method, Object[] args, Object result) {
        String methodName = method.getName().toLowerCase();
        switch (methodName) {
            case "newwindow":
                CustomExtentReport.getExtentTest().log(Status.PASS, "switch to a new tab");
                break;
            case "frame":
                CustomExtentReport.getExtentTest().log(Status.PASS, "switch to frame");
                break;
            case "window":
                CustomExtentReport.getExtentTest().log(Status.PASS, "switch to window");
                break;
        }
    }
}
