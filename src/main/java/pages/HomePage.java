package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page object for the application's homepage.
 */
public class HomePage extends BasePage {
    // The demo web shop homepage has a "Log in" link with link text "Log in".
    private final By loginLink = By.linkText("Log in");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void clickLogin() {
        click(loginLink);
    }

    public boolean isLoginLinkVisible() {
        return isElementVisible(loginLink);
    }
}
