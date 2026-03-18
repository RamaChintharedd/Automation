package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

import java.time.Duration;

/**
 * Page Object representing the Demo Web Shop homepage.
 * Encapsulates interaction details for navigation to the login page.
 */
public class HomePage {
    private final WebDriver driver;
    private final WaitUtils wait;

    private final By loginLink = By.cssSelector("a[href='/login'], a[href*='login']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, Duration.ofSeconds(10));
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public boolean isLoginLinkPresent() {
        try {
            wait.waitForElementVisible(loginLink);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLoginLink() {
        WebElement el = wait.waitForElementClickable(loginLink);
        el.click();
    }
}
