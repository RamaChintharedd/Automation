package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object for the Login Page. Encapsulates element locators and interactions.
 */
public class LoginPage extends BasePage {

    @FindBy(id = "Email")
    private WebElement emailInput;

    @FindBy(id = "Password")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@value='Log in' or @type='submit']")
    private WebElement loginButton;

    @FindBy(css = ".message-error")
    private WebElement globalErrorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEmailDisplayed() {
        try {
            return emailInput.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordDisplayed() {
        try {
            return passwordInput.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginButtonDisplayed() {
        try {
            return loginButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    /**
     * Attempts to read HTML5 validation message from the input element.
     * If the browser enforces native validation, this will return that message.
     */
    public String getEmailValidationMessage() {
        try {
            return (String) ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", emailInput);
        } catch (Exception e) {
            return "";
        }
    }

    public String getPasswordValidationMessage() {
        try {
            return (String) ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", passwordInput);
        } catch (Exception e) {
            return "";
        }
    }

    public String getGlobalErrorText() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(globalErrorMessage)).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean hasFieldValidationMessages() {
        // Demo Web Shop may show inline validation spans with class field-validation-error
        try {
            return !driver.findElements(By.cssSelector(".field-validation-error")).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
