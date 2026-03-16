package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Simple WebDriver factory implementing a single-responsibility: create and manage WebDriver instance.
 * Reads CHROMEDRIVER_PATH env var if provided or expects driver on PATH.
 */
public class DriverFactory {
    private static WebDriver driver;

    private DriverFactory() { }

    public static WebDriver getDriver() {
        if (driver == null) {
            // If a path to chromedriver is provided via env var, use it. Otherwise assume chromedriver is on PATH.
            String chromeDriverPath = System.getenv("CHROMEDRIVER_PATH");
            if (chromeDriverPath != null && !chromeDriverPath.isEmpty()) {
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            }

            ChromeOptions options = new ChromeOptions();
            // headless can be enabled by setting env var HEADLESS=true
            String headless = System.getenv("HEADLESS");
            if ("true".equalsIgnoreCase(headless)) {
                options.addArguments("--headless=new");
            }
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }
}
