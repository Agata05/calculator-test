package test.testclasses;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.pageobjects.CalculatorPage;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class CalculatorTest extends BaseTest {

    CalculatorPage calculatorPage;

    @BeforeClass
    public void setUp() {
        calculatorPage = new CalculatorPage(getDriver());
        calculatorPage.openCalculatorPage();
    }

    @AfterMethod
    public void cleanAfterTest() {
        calculatorPage.clearDisplayText();
    }

    @Test(priority = 1, groups = "operations")
    public void checkResultOfBasicOperations() {

        var expectedResult = "34990";
        var actualResult = calculatorPage
                .enterNumberWithButtons("35")
                .clickMultiply()
                .enterNumberWithButtons("999")
                .clickPlus()
                .clickParenthesisLeft()
                .enterNumberWithButtons("100")
                .clickDivide()
                .enterNumberWithButtons("4")
                .clickParenthesisRight()
                .clickEqualsAndWaitForResult()
                .getDisplayText();

        assertThat(actualResult).as("Expected basic operation result: ").isEqualTo(expectedResult);
    }

    @Test(priority = 2, groups = "operations")
    public void checkResultOfCosinusOperation() {

        var expectedResult = "-1";
        var actualResult = calculatorPage
                .clickRadianOption()
                .clickCosinus()
                .clickPi()
                .clickParenthesisRight()
                .clickEqualsAndWaitForResult()
                .getDisplayText();

        assertThat(actualResult).as("Expected cosinus operation result: ").isEqualTo(expectedResult);
    }

    @Test(priority = 3, groups = "operations")
    public void checkResultOfSqrtOperation() {

        var expectedResult = "9";
        var actualResult = calculatorPage
                .clickSquareRoot()
                .enterNumberWithButtons("81")
                .clickParenthesisRight()
                .clickEqualsAndWaitForResult()
                .getDisplayText();

        assertThat(actualResult).as("Expected sqrt operation result: ").isEqualTo(expectedResult);
    }

    @Test(dependsOnGroups = "operations")
    public void checkHistory() {

        String[] expectedResult = {"sqrt(81)", "cos(pi)", "35*999+(100/4)"};
        var actualResult = calculatorPage
                .expandHistoryDropDown()
                .getOperationResultsHistory();

        assertThat(actualResult).containsExactly(expectedResult);
    }
}
