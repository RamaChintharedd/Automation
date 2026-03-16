package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page object for a logged-in user's account area/dashboard.
 */
public class AccountPage extends BasePage {
    private final By logoutLink = By.linkText("Log out");
    private final By accountName = By.cssSelector("a.account");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoggedIn() {
        // Either account name link or logout should be present
        boolean hasLogout = !driver.findElements(logoutLink).isEmpty();
        boolean hasAccount = !driver.findElements(accountName).isEmpty();
        return hasLogout || hasAccount;
    }
}
