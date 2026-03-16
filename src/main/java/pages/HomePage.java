package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Page Object representing the Demo Web Shop homepage
public class HomePage extends BasePage {
    private By loginLink = By.linkText("Log in");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        navigateTo(url);
    }

    public void clickLogin() {
        click(loginLink);
    }

    public boolean isLoginLinkPresent() {
        return isDisplayed(loginLink);
    }
}
