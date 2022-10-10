package test.testclasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;

@Slf4j
public abstract class BaseTest {

    private WebDriver driver;

    @BeforeSuite
    public static void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
    }

    private WebDriver createWebDriver() {

        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        return webDriver;
    }

    protected WebDriver getDriver() {

        if (driver == null) {
            driver = createWebDriver();
        }
        return driver;
    }

    @AfterClass
    public void tearDown(ITestContext context) {

        log.info("Completing test suite: " + context.getCurrentXmlTest().getName());
        try {
            if (getDriver() != null) {
                log.info("Closing browser.");
                getDriver().quit();
            }
        } catch (Exception e) {
            log.error("Error while closing browser. " + e.getMessage());
        }
    }
}
