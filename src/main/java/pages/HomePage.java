package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object for the Demo Web Shop homepage.
 * Provides interactions to reach the login page.
 */
public class HomePage extends BasePage {

    private By loginLink = By.linkText("Log in"); // Link text present on the site

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void clickLoginLink() {
        WebElement e = driver.findElement(loginLink);
        e.click();
    }
}
