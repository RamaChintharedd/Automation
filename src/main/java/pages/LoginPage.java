package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

import java.time.Duration;

/**
 * Page Object for the login page of Demo Web Shop.
 * Exposes actions and queries needed by tests.
 */
public class LoginPage {
    private final WebDriver driver;
    private final WaitUtils wait;

    // Common locators - designed to be resilient to small DOM changes
    private final By emailInput = By.id("Email");
    private final By passwordInput = By.id("Password");
    private final By loginButton = By.cssSelector("input[type='submit'][value='Log in'], button[type='submit']");
    private final By pageHeader = By.cssSelector("div.page-title h1, .page-title");
    private final By validationSummary = By.cssSelector(".validation-summary-errors, .field-validation-error, span.field-validation-error");
    private final By authenticationError = By.cssSelector(".message-error, .validation-summary-errors");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, Duration.ofSeconds(10));
    }

    public boolean isDisplayed() {
        try {
            WebElement header = wait.waitForElementVisible(pageHeader);
            String text = header.getText().toLowerCase();
            return text.contains("login") || text.contains("sign in");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmailInputPresent() {
        try {
            wait.waitForElementVisible(emailInput);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void focusEmail() {
        WebElement e = wait.waitForElementVisible(emailInput);
        e.click();
    }

    public void typeEmail(String email) {
        WebElement e = wait.waitForElementVisible(emailInput);
        e.clear();
        e.sendKeys(email);
    }

    public void typePassword(String password) {
        WebElement p = wait.waitForElementVisible(passwordInput);
        p.clear();
        p.sendKeys(password);
    }

    public void clickLogin() {
        WebElement b = wait.waitForElementClickable(loginButton);
        b.click();
    }

    public String getValidationMessage() {
        try {
            WebElement v = wait.waitForElementVisible(validationSummary);
            return v.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getAuthenticationError() {
        try {
            WebElement v = wait.waitForElementVisible(authenticationError);
            return v.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void submitWithEnter() {
        WebElement p = wait.waitForElementVisible(passwordInput);
        p.sendKeys(Keys.ENTER);
    }
}
