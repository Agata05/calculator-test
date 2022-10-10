package test.pageobjects;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static java.time.Duration.ofSeconds;

@Slf4j
public final class CalculatorPage extends BasePage {

    public CalculatorPage(WebDriver driver) {
        super(driver);
    }

    static final String BASE_URL = "http://web2.0calc.com/";

    @FindBy(id = "BtnCos")
    private WebElement cosinusButton;

    @FindBy(id = "BtnDiv")
    private WebElement divideButton;

    @FindBy(xpath = "//div[@class='modal-content']//button[@value= 'deny']")
    private WebElement doNotConsentButton;

    @FindBy(id = "BtnMult")
    private WebElement multipleButton;

    @FindBy(id = "BtnParanL")
    private WebElement parenthesisLeftButton;

    @FindBy(id = "BtnParanR")
    private WebElement parenthesisRightButton;

    @FindBy(id = "BtnPi")
    private WebElement piButton;

    @FindBy(id = "BtnPlus")
    private WebElement plusButton;

    @FindBy(id = "trigorad")
    private WebElement radianRadioButton;

    @FindBy(id = "BtnSqrt")
    private WebElement sqrtButton;

    @FindBy(id = "BtnCalc")
    private WebElement equalsButton;

    @FindBy(xpath = "//div[@class='inputs']//input[@id='input']")
    private WebElement displayInput;

    @FindAll({@FindBy(xpath = "//ul/li/p[@class='l']")})
    private List<WebElement> historyList;


    public void openCalculatorPage() {
        log.info("Open Calculator page.");
        driver.get(BASE_URL);
        closeConsentWindow();
    }

    public CalculatorPage clickCosinus() {
        log.info("Cosinus operation.");
        cosinusButton.click();
        return this;
    }

    public CalculatorPage clickDivide() {
        log.info("Divide operation.");
        divideButton.click();
        return this;
    }

    public CalculatorPage clickMultiply() {
        log.info("Click multiply button.");
        multipleButton.click();
        return this;
    }

    public CalculatorPage clickParenthesisLeft() {
        log.info("Click left parenthesis button.");
        parenthesisLeftButton.click();
        return this;
    }

    public CalculatorPage clickParenthesisRight() {
        log.info("Click right parenthesis button.");
        parenthesisRightButton.click();
        return this;
    }

    public CalculatorPage clickPi() {
        log.info("Click π button.");
        piButton.click();
        return this;
    }

    public CalculatorPage clickPlus() {
        log.info("Click plus button.");
        plusButton.click();
        return this;
    }

    public CalculatorPage clickRadianOption() {
        log.info("Click radian radio button.");
        radianRadioButton.click();
        return this;
    }

    public CalculatorPage clickSquareRoot() {
        log.info("Click square root button.");
        sqrtButton.click();
        return this;
    }

    public CalculatorPage clickEqualsAndWaitForResult() {
        log.info("Click equals button and wait for result.");
        String textBeforeClick = getDisplayText();
        equalsButton.click();
        waitForTextNotEquals(displayInput, textBeforeClick, ofSeconds(5));
        return this;
    }

    public void clearDisplayText() {
        log.info("Clear text from display.");
        displayInput.clear();
    }

    public void closeContentWindowWithJS() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementsByClassName('fc-button fc-cta-consent fc-primary-button')[0].click()");
    }

    public void closeConsentWindow() {
        doNotConsentButton.click();
    }

    public CalculatorPage enterNumberWithButtons(String number) {
        log.info("Click number: " + number);
        for (char ch : number.toCharArray()) {
            By digitButtonBy = By.id(String.format("Btn%s", ch));
            WebElement digitButton = driver.findElement(digitButtonBy);
            digitButton.click();
        }
        return this;
    }

    public CalculatorPage expandHistoryDropDown() {
        log.info("Expand history dropdown.");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementsByClassName('btn dropdown-toggle pull-right')[0].click()");
        return this;
    }

    public String getDisplayText() {
        log.info("Get text from display.");
        return displayInput.getAttribute("value");
    }

    public List<String> getOperationResultsHistory() {
        log.info("Get results history of executed operation.");

        List<String> operation = new ArrayList<>();
        for (WebElement e : historyList) {
            String text = e.getText();
            operation.add(text);
        }
        return operation;
    }

}
