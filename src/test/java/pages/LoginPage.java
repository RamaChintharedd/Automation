package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object for the Demo Web Shop login-related pages and interactions.
 * Encapsulates locators and actions to keep step definitions readable and maintainable.
 */
public class LoginPage extends BasePage {
    // Locators
    private final By loginLinkOnHome = By.linkText("Log in");
    private final By emailInput = By.id("Email");
    private final By passwordInput = By.id("Password");
    private final By loginButton = By.cssSelector("input.login-button, input[type='submit'][value='Log in']");
    private final By myAccountLink = By.linkText("My account");
    private final By orderHistoryLink = By.linkText("Orders"); // may be present under account

    // Common validation locators (the demo site uses data-valmsg-for attributes)
    private final By emailValidation = By.cssSelector("span.field-validation-error[data-valmsg-for='Email'], span[data-valmsg-for='Email']");
    private final By passwordValidation = By.cssSelector("span.field-validation-error[data-valmsg-for='Password'], span[data-valmsg-for='Password']");
    private final By invalidCredentialsSummary = By.cssSelector("div.message-error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Navigation
    public void goToHomePage(String url) {
        navigateTo(url);
    }

    public void clickLoginLinkOnHome() {
        driver.findElement(loginLinkOnHome).click();
    }

    public void goToLoginPage(String url) {
        navigateTo(url);
    }

    // Interactions
    public void enterEmail(String email) {
        WebElement e = driver.findElement(emailInput);
        e.clear();
        e.sendKeys(email);
    }

    public void enterPassword(String pwd) {
        WebElement e = driver.findElement(passwordInput);
        e.clear();
        e.sendKeys(pwd);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Assertions / checks
    public boolean isOnLoginPage(String expectedUrl) {
        return getCurrentUrl().startsWith(expectedUrl);
    }

    public boolean hasEmailField() {
        return isElementPresent(emailInput);
    }

    public boolean hasPasswordField() {
        return isElementPresent(passwordInput);
    }

    public boolean hasLoginButton() {
        return isElementPresent(loginButton);
    }

    public boolean isAuthenticated() {
        // Basic check for account-specific link
        return isElementPresent(myAccountLink) || isElementPresent(orderHistoryLink);
    }

    public boolean isEmailValidationVisible() {
        return isElementPresent(emailValidation) && !driver.findElements(emailValidation).get(0).getText().trim().isEmpty();
    }

    public boolean isPasswordValidationVisible() {
        return isElementPresent(passwordValidation) && !driver.findElements(passwordValidation).get(0).getText().trim().isEmpty();
    }

    public boolean isInvalidCredentialsMessageVisible() {
        return isElementPresent(invalidCredentialsSummary) && !driver.findElements(invalidCredentialsSummary).get(0).getText().trim().isEmpty();
    }
}
