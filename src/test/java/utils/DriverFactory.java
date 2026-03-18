package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Simple WebDriver factory providing a singleton WebDriver instance.
 * Uses WebDriverManager to resolve browser driver binaries.
 * Keep this class minimal to allow swapping drivers (SOLID: Single Responsibility).
 */
public class DriverFactory {
    private static WebDriver driver;

    private DriverFactory() {
        // private constructor to prevent instantiation
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            // Add headless or CI-friendly flags when needed
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
