package pages;

import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

/**
 * Base page that all page objects extend. Holds common utilities.
 */
public abstract class BasePage {
    protected final WebDriver driver;
    protected final WaitUtils wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
