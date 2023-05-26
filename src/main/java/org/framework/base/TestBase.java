package org.framework.base;

import org.framework.base.listeners.TestNgManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.testng.AssertJUnit.fail;

@Listeners(TestNgManager.class)
public class TestBase {
    public WebDriver driver;
    private final DriverHandler driverHandler = new DriverHandler();

    @BeforeMethod
    @Parameters({"browserName", "capabilities"})
    public void setUp(@Optional("Chrome") String browserName, @Optional("defaultCaps.json") String capabilities) {
        try {
            driver = driverHandler.initialization(browserName, capabilities);
        } catch (Exception e) {
            // log.error("driver can not be initialized: here is what happened " + e.getLocalizedMessage());
            fail(e.getLocalizedMessage());
        }

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
