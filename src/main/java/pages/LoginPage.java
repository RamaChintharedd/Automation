package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page object for the login page. Encapsulates locators and interactions.
 */
public class LoginPage extends BasePage {
    private final By emailInput = By.id("Email");
    private final By passwordInput = By.id("Password");
    private final By loginButton = By.cssSelector("input.button-1.login-button, button[type='submit']");
    // DemoWebShop displays errors inside a div with class 'validation-summary-errors' or inline with field.
    private final By validationSummary = By.cssSelector("div.validation-summary-errors");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public boolean isOnLoginPage() {
        // Checks URL contains /login and email field visible
        return getCurrentUrl().contains("/login") && isElementVisible(emailInput);
    }

    public void enterEmail(String email) {
        type(emailInput, email == null ? "" : email);
    }

    public void enterPassword(String password) {
        type(passwordInput, password == null ? "" : password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public boolean isEmailVisibleAndInteractable() {
        return isElementVisible(emailInput);
    }

    public void focusEmail() {
        focusElement(emailInput);
    }

    public String getValidationSummaryText() {
        try {
            WebElement el = waitUtils.waitForVisibility(validationSummary);
            return el.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getEmailAriaLiveOrDescribedBy() {
        // Try aria-live on a nearby live region or aria-describedby on the email field
        try {
            String describedBy = getElementAttribute(emailInput, "aria-describedby");
            if (describedBy != null && !describedBy.isEmpty()) {
                By describedByLocator = By.id(describedBy);
                return getElementAttribute(describedByLocator, "innerText");
            }
        } catch (Exception ignored) { }
        try {
            String ariaLive = getElementAttribute(By.cssSelector("[aria-live]"), "aria-live");
            return ariaLive == null ? "" : ariaLive;
        } catch (Exception ignored) { }
        return "";
    }
}
