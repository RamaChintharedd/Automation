package com.example.pages;

import com.example.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Page Object representing the homepage of Demo Web Shop.
public class HomePage extends BasePage {

    private final By loginLink = By.linkText("Log in");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Navigate to base url (handled by tests or calling code)
    public void open(String baseUrl) {
        driver.get(baseUrl);
    }

    // Click the "Log in" link
    public LoginPage clickLogin() {
        WebElement link = WaitUtils.waitForElementVisible(driver, loginLink);
        link.click();
        return new LoginPage(driver);
    }
}
