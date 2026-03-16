package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object representing the logged-in user's area. We check for presence
 * of typical account-specific elements like 'Log out' and 'My account' links.
 */
public class AccountPage extends BasePage {

    // 'Log out' link text and 'My account' link text are present after login on Demo Web Shop
    private By logoutLink = By.linkText("Log out");
    private By myAccountLink = By.linkText("My account");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoutVisible() {
        return !driver.findElements(logoutLink).isEmpty() && driver.findElement(logoutLink).isDisplayed();
    }

    public boolean isMyAccountVisible() {
        return !driver.findElements(myAccountLink).isEmpty() && driver.findElement(myAccountLink).isDisplayed();
    }
}
