package com.example.pages;

import com.example.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Page Object representing a post-login account area. Exposes checks for account-specific controls.
public class AccountPage extends BasePage {

    private final By myAccountLink = By.linkText("My account");
    private final By logoutLink = By.linkText("Log out");
    private final By ordersLink = By.linkText("Orders");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isMyAccountVisible() {
        try {
            return WaitUtils.waitForElementVisible(driver, myAccountLink).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogoutVisible() {
        try {
            return WaitUtils.waitForElementVisible(driver, logoutLink).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean canAccessOrders() {
        try {
            return WaitUtils.waitForElementVisible(driver, ordersLink).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
