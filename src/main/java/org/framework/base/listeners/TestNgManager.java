package org.framework.base.listeners;

import com.aventstack.extentreports.Status;
import org.framework.base.CustomExtentReport;
import org.framework.base.DriverHandler;
import org.framework.utils.Screen;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNgManager extends DriverHandler implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        CustomExtentReport.setExtentTest(CustomExtentReport.getExtentReport()
                .createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        CustomExtentReport.getExtentTest().log(Status.PASS, "Test case: " + result.getMethod().getMethodName() + " Passed");
        CustomExtentReport.removeExtentTest();

    }

    @Override
    public void onTestFailure(ITestResult result) {
        CustomExtentReport.getExtentTest().fail(result.getThrowable());
        CustomExtentReport.getExtentTest().log(Status.FAIL, "Test case: " + result.getMethod().getMethodName() + " Failed");
        String screenShotPath = Screen.takeScreenShot(getDriver(), CustomExtentReport.getSuiteName(), result.getMethod().getMethodName(), String.valueOf(result.getEndMillis()));
        CustomExtentReport.getExtentTest().addScreenCaptureFromPath(screenShotPath);
        CustomExtentReport.removeExtentTest();

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        CustomExtentReport.getExtentTest().log(Status.SKIP, "Test case: " + result.getMethod().getMethodName() + " is skipped");
        CustomExtentReport.removeExtentTest();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
        CustomExtentReport.setSuiteName(context.getName());
        CustomExtentReport.generateExtentReport();
    }

    @Override
    public void onFinish(ITestContext context) {
        CustomExtentReport.getExtentReport().flush();
        CustomExtentReport.removeExtentReport();
    }
}
