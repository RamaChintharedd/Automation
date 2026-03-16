package com.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// Factory for creating WebDriver instances. Keeps creation logic single-responsibility.
public final class WebDriverFactory {

    private WebDriverFactory() {
        // utility class
    }

    public static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        // Add headless if needed: options.addArguments("--headless=new");
        return new ChromeDriver(options);
    }
}
