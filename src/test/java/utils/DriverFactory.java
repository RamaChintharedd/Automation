package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Simple Driver factory using ThreadLocal to support parallel execution later.
 * Uses WebDriverManager to avoid requiring a local chromedriver binary path.
 * Ensure the project has the dependency io.github.bonigarcia:webdrivermanager in the build file.
 */
public final class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {
        // utility
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            // options.addArguments("--headless=new"); // enable for CI if needed
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            driver.set(new ChromeDriver(options));
        }
        return driver.get();
    }

    public static void quitDriver() {
        WebDriver wd = driver.get();
        if (wd != null) {
            wd.quit();
            driver.remove();
        }
    }
}
