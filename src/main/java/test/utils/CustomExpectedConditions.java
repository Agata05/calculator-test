package test.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CustomExpectedConditions {

    public static ExpectedCondition<Boolean> textNotEquals(final WebElement element, final String text) {
        return new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                try {
                    return !element.getText().equals(text);
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            public String toString() {
                return String.format("text of the element %s to be equal: '%s'", element, text);
            }
        };
    }
}
