package org.framework.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.nio.file.Paths;

public class CustomExtentReport {
    private static final InheritableThreadLocal<ExtentReports> extentReports = new InheritableThreadLocal<>();
    private static final InheritableThreadLocal<ExtentSparkReporter> reporter = new InheritableThreadLocal<>();
    private static final InheritableThreadLocal<ExtentTest> extentTest = new InheritableThreadLocal<>();
    private static final InheritableThreadLocal<String> suiteName = new InheritableThreadLocal<>();


    public static ExtentReports generateExtentReport() {
        try {
            ExtentReports extentReport = new ExtentReports();


            String sourcePath = Paths.get(System.getProperty("user.dir"), "Reports", suiteName.get(), "index.html").toString();
            reporter.set(new ExtentSparkReporter(sourcePath));
            getReporter().config().setReportName("Web Automation Report");
            getReporter().config().setDocumentTitle("Test Results");
            getReporter().config().setTheme(Theme.DARK);

            extentReport.attachReporter(getReporter());
            extentReport.setSystemInfo("Executed by: ", System.getProperty("user.name"));
            extentReport.setSystemInfo("Executed on: ", System.getProperty("os.name"));
            setExtentReport(extentReport);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return getExtentReport();
    }

    public static ExtentReports getExtentReport() {
        return extentReports.get();
    }

    private static void setExtentReport(ExtentReports extentReport) {
        extentReports.set(extentReport);
    }

    private static ExtentSparkReporter getReporter() {
        return reporter.get();
    }

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    public static void setExtentTest(ExtentTest extentTest) {
        CustomExtentReport.extentTest.set(extentTest);
    }

    public static void removeExtentTest() {
        extentTest.remove();
    }

    public static void removeExtentReport() {
        extentReports.remove();
    }

    public static void setSuiteName(String suiteName) {
        CustomExtentReport.suiteName.set(suiteName);
    }

    public static String getSuiteName() {
        return suiteName.get();
    }

}
