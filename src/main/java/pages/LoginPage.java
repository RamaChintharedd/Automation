package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object for the login page. Contains interactions for entering credentials,
 * submitting the form and retrieving validation/authentication error messages.
 */
public class LoginPage extends BasePage {

    private By emailInput = By.id("Email");
    private By passwordInput = By.id("Password");
    private By loginButton = By.cssSelector("input.button-1.login-button");
    private By authError = By.cssSelector("div.message-error.validation-summary-errors");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEmailFieldVisible() {
        return !driver.findElements(emailInput).isEmpty() && driver.findElement(emailInput).isDisplayed();
    }

    public boolean isPasswordFieldVisible() {
        return !driver.findElements(passwordInput).isEmpty() && driver.findElement(passwordInput).isDisplayed();
    }

    public void enterEmail(String email) {
        WebElement e = driver.findElement(emailInput);
        e.clear();
        e.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement e = driver.findElement(passwordInput);
        e.clear();
        e.sendKeys(password);
    }

    public void submitLogin() {
        driver.findElement(loginButton).click();
    }

    public String getAuthenticationErrorText() {
        if (!driver.findElements(authError).isEmpty()) {
            return driver.findElement(authError).getText().trim();
        }
        return "";
    }

    /**
     * Uses HTML5 field validation via JavaScript to return the browser validation message for a field.
     * If the page does not rely on HTML5 validation, the returned string may be empty.
     */
    public String getFieldValidationMessage(By fieldLocator) {
        WebElement field = driver.findElement(fieldLocator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object msg = js.executeScript("return arguments[0].validationMessage;", field);
        return msg == null ? "" : msg.toString();
    }

    public String getEmailFieldValidationMessage() {
        return getFieldValidationMessage(emailInput);
    }

    public String getPasswordFieldValidationMessage() {
        return getFieldValidationMessage(passwordInput);
    }
}
