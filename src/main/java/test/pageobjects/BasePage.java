package test.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static test.utils.CustomExpectedConditions.textNotEquals;

public abstract class BasePage {

    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void waitForTextNotEquals(final WebElement element, String text, Duration timeout) {
        final WebDriverWait wait = new WebDriverWait(this.driver, timeout.getSeconds());
        wait.until(textNotEquals(element, text));
    }
}
