package com.example.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Small utility wrapper around explicit waits to keep tests readable.
public final class WaitUtils {
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    private WaitUtils() {}

    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForUrlContains(WebDriver driver, String fraction) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.urlContains(fraction));
    }

    public static void waitForCondition(WebDriver driver, ExpectedCondition<Boolean> condition) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(condition);
    }
}
