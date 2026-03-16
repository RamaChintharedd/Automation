package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

// Simple DriverFactory following Singleton-like pattern for tests
public final class DriverFactory {
    private static WebDriver driver;

    private DriverFactory() {
        // utility class
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            // Use WebDriverManager if available in project to manage driver binaries
            try {
                // If WebDriverManager is present, uncomment the following line:
                // io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
            } catch (Throwable ignored) {
                // ignore if not available - caller must ensure chromedriver on PATH
            }

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            // options.addArguments("--headless=new"); // enable if headless needed
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {
            }
            driver = null;
        }
    }
}
