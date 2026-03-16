package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

// Page Object representing the login page and actions on it
public class LoginPage extends BasePage {
    private final By emailInput = By.id("Email");
    private final By passwordInput = By.id("Password");
    private final By loginButton = By.cssSelector("input[value='Log in']");
    private final By loginHeader = By.cssSelector("div.page-title h1");
    private final By validationSummary = By.cssSelector("div.message-error");
    private final By logoutLink = By.linkText("Log out");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String getHeadingText() {
        try {
            WebElement h = wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader));
            return h.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public void enterEmail(String email) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        e.clear();
        e.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement p = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        p.clear();
        p.sendKeys(password);
    }

    public void clickLogin() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        btn.click();
    }

    public boolean isEmailVisibleAndEnabled() {
        try {
            WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
            return e.isEnabled();
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(validationSummary)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(validationSummary)).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isLogoutVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
