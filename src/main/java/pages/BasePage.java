package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

/**
 * BasePage provides common actions and is the parent for page-specific objects.
 * Keeps single responsibility: basic interactions, and exposes WaitUtils.
 */
public abstract class BasePage {
    protected final WebDriver driver;
    protected final WaitUtils waitUtils;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, 10);
    }

    protected void click(By locator) {
        WebElement el = waitUtils.waitForClickable(locator);
        el.click();
    }

    protected void type(By locator, String text) {
        WebElement el = waitUtils.waitForVisibility(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected boolean isElementVisible(By locator) {
        try {
            WebElement el = waitUtils.waitForVisibility(locator);
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void focusElement(By locator) {
        WebElement el = waitUtils.waitForVisibility(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", el);
    }

    protected String getElementAttribute(By locator, String attribute) {
        WebElement el = waitUtils.waitForVisibility(locator);
        return el.getAttribute(attribute);
    }
}
