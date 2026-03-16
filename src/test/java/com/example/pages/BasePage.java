package com.example.pages;

import org.openqa.selenium.WebDriver;

// Base page for common behavior. Follows Single Responsibility - holds driver only.
public abstract class BasePage {
    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
