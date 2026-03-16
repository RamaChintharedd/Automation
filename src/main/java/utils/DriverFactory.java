package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// If you have WebDriverManager available, uncomment and use it for automatic driver management:
// import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Simple DriverFactory implementing a singleton WebDriver instance.
 * Keeps WebDriver creation centralized so tests/pages can request driver via DriverFactory.getDriver().
 */
public final class DriverFactory {
    private static WebDriver driver;

    private DriverFactory() {
        // prevent instantiation
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            // WebDriverManager.chromedriver().setup(); // if using WebDriverManager
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            // Add headless if required: options.addArguments("--headless=new");
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
