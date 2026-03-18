package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

import java.time.Duration;

/**
 * Page object for user account / dashboard page that appears after successful login.
 */
public class DashboardPage {
    private final WebDriver driver;
    private final WaitUtils wait;

    private final By myAccountLink = By.cssSelector("a[href*='customer/info'], a.account");
    private final By logoutLink = By.cssSelector("a[href*='logout']");
    private final By orderHistory = By.cssSelector("a[href*='order/history'], .order-list");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, Duration.ofSeconds(10));
    }

    public boolean isAt() {
        try {
            // Check one or more account-specific elements
            return wait.waitForElementVisible(myAccountLink) != null ||
                    wait.waitForElementVisible(orderHistory) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasAccountElementsVisible() {
        try {
            return wait.waitForElementVisible(myAccountLink) != null;
        } catch (Exception e) {
            return false;
        }
    }
}
