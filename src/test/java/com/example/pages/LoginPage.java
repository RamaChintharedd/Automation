package com.example.pages;

import com.example.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Page Object representing the login page and its interactions.
public class LoginPage extends BasePage {

    private final By emailInput = By.id("Email");
    private final By passwordInput = By.id("Password");
    private final By loginButton = By.xpath("//input[@value='Log in']");
    private final By validationSummary = By.cssSelector(".validation-summary-errors");
    private final By emailFieldError = By.id("Email-error");
    private final By passwordFieldError = By.id("Password-error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEmailDisplayed() {
        return WaitUtils.waitForElementVisible(driver, emailInput).isDisplayed();
    }

    public boolean isPasswordDisplayed() {
        return WaitUtils.waitForElementVisible(driver, passwordInput).isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        return WaitUtils.waitForElementVisible(driver, loginButton).isDisplayed();
    }

    public void enterEmail(String email) {
        WebElement el = WaitUtils.waitForElementVisible(driver, emailInput);
        el.clear();
        el.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement el = WaitUtils.waitForElementVisible(driver, passwordInput);
        el.clear();
        el.sendKeys(password);
    }

    public void submitLogin() {
        WebElement btn = WaitUtils.waitForElementVisible(driver, loginButton);
        btn.click();
    }

    // Convenience: enter credentials and submit
    public AccountPage loginAs(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        submitLogin();
        return new AccountPage(driver);
    }

    public String getValidationSummaryText() {
        try {
            return WaitUtils.waitForElementVisible(driver, validationSummary).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getEmailFieldErrorText() {
        try {
            return WaitUtils.waitForElementVisible(driver, emailFieldError).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getPasswordFieldErrorText() {
        try {
            return WaitUtils.waitForElementVisible(driver, passwordFieldError).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
