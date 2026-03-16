package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Page Object representing the Login page
public class LoginPage extends BasePage {
    private By emailInput = By.id("Email");
    private By passwordInput = By.id("Password");
    private By loginButton = By.cssSelector("input.button-1.login-button");
    // Generic field validation errors; more specific selectors can be added if needed
    private By fieldValidationErrors = By.cssSelector(".field-validation-error");
    private By summaryValidation = By.cssSelector(".validation-summary-errors");
    private By myAccountLink = By.linkText("My account");
    private By logoutLink = By.linkText("Log out");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        navigateTo(url);
    }

    public boolean isAt() {
        return getCurrentUrl().contains("/login");
    }

    public boolean isLoginFormVisible() {
        return isDisplayed(emailInput) && isDisplayed(passwordInput) && isDisplayed(loginButton);
    }

    public void enterEmail(String email) {
        type(emailInput, email);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public boolean emailFieldCanReceiveFocus() {
        try {
            waitForVisibility(emailInput).click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidationErrorPresentForEmail() {
        // Look through validation elements for clues about email
        String summary = getText(summaryValidation).toLowerCase();
        if (!summary.isEmpty() && summary.contains("email")) return true;
        String fieldErr = getText(fieldValidationErrors).toLowerCase();
        return fieldErr.contains("email") || fieldErr.contains("invalid");
    }

    public boolean isOnLoginPage() {
        return isAt();
    }

    public boolean isAuthenticated() {
        // Determine authentication by presence of account-specific UI (e.g., 'Log out')
        return isDisplayed(logoutLink) || isDisplayed(myAccountLink);
    }
}
