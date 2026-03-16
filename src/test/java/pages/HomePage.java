package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object representing the Demo Web Shop homepage.
 */
public class HomePage extends BasePage {

    @FindBy(linkText = "Log in")
    private WebElement loginLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    public boolean isLoginLinkDisplayed() {
        try {
            return loginLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
