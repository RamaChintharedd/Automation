package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page object representing a logged-in user's account or dashboard page.
 */
public class AccountPage extends BasePage {

    @FindBy(linkText = "My account")
    private WebElement myAccountLink;

    @FindBy(linkText = "Log out")
    private WebElement logoutLink;

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isMyAccountVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(myAccountLink)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogoutVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(logoutLink)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
