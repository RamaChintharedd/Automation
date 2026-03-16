package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Thread-safe WebDriver factory. Initializes and provides WebDriver instances.
 */
public class DriverFactory {
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    // Initialize a ChromeDriver instance and return it
    public static WebDriver initDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        // Add headless if needed: options.addArguments("--headless=new");
        tlDriver.set(new ChromeDriver(options));
        return tlDriver.get();
    }

    // Get the current thread's WebDriver
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    // Quit and remove the current thread's WebDriver
    public static void quitDriver() {
        WebDriver driver = tlDriver.get();
        if (driver != null) {
            driver.quit();
            tlDriver.remove();
        }
    }
}
