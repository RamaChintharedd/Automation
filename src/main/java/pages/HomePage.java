package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

// Page Object for the homepage of Demo Web Shop
public class HomePage extends BasePage {
    private final By loginLink = By.linkText("Log in");
    private final By myAccountLink = By.linkText("My account");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void clickLogin() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        el.click();
    }

    public boolean isMyAccountVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(myAccountLink));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
