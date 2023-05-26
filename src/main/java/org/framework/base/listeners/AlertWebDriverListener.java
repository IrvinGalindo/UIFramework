package org.framework.base.listeners;

import com.aventstack.extentreports.Status;
import org.framework.base.CustomExtentReport;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.events.WebDriverListener;

public class AlertWebDriverListener implements WebDriverListener {
    @Override
    public void afterAccept(Alert alert) {
        CustomExtentReport.getExtentTest().log(Status.PASS, "Accept alert");
    }

    @Override
    public void afterDismiss(Alert alert) {
        CustomExtentReport.getExtentTest().log(Status.PASS, "Dismiss alert");

    }

    @Override
    public void afterSendKeys(Alert alert, String text) {
        CustomExtentReport.getExtentTest().log(Status.PASS, "Send [ " + text + " ] to alert");
    }
}
