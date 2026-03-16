package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page object representing the Demo Web Shop homepage.
 */
public class HomePage extends BasePage {
    private final By loginLink = By.linkText("Log in");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goToUrl(String url) {
        driver.get(url);
    }

    public void clickLogin() {
        WebElement el = wait.waitForClickable(loginLink);
        el.click();
    }

    public boolean isLoginLinkPresent() {
        try {
            return !driver.findElements(loginLink).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
