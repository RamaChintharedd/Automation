package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page object for the login page and related actions/queries.
 */
public class LoginPage extends BasePage {
    private final By emailInput = By.id("Email");
    private final By passwordInput = By.id("Password");
    private final By loginButton = By.xpath("//input[@value='Log in' or @type='submit']");
    // Validation summary / error message(s)
    private final By validationSummary = By.cssSelector(".message-error, .validation-summary-errors");
    private final By fieldValidationError = By.cssSelector("span.field-validation-error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnLoginPage() {
        // Basic check: URL contains '/login'
        return getCurrentUrl().contains("/login");
    }

    public void enterEmail(String email) {
        WebElement e = wait.waitForVisibility(emailInput);
        e.clear();
        e.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement p = wait.waitForVisibility(passwordInput);
        p.clear();
        p.sendKeys(password);
    }

    public void clickLogin() {
        WebElement btn = wait.waitForClickable(loginButton);
        btn.click();
    }

    public boolean isEmailFieldVisibleAndEnabled() {
        try {
            WebElement e = wait.waitForVisibility(emailInput);
            return e.isDisplayed() && e.isEnabled();
        } catch (Exception ex) {
            return false;
        }
    }

    public String getValidationErrorText() {
        try {
            if (!driver.findElements(validationSummary).isEmpty()) {
                return driver.findElement(validationSummary).getText().trim();
            }
            if (!driver.findElements(fieldValidationError).isEmpty()) {
                return driver.findElement(fieldValidationError).getText().trim();
            }
            return "";
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    public boolean hasValidationErrorContaining(String expected) {
        String txt = getValidationErrorText();
        return txt != null && txt.toLowerCase().contains(expected.toLowerCase());
    }
}
