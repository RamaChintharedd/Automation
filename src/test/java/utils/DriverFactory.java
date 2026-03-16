package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

/**
 * Simple WebDriver factory/singleton. Centralizes driver creation and teardown.
 * SOLID: Single Responsibility - only manages WebDriver lifecycle.
 */
public final class DriverFactory {
    private static WebDriver driver;

    private DriverFactory() { }

    public static WebDriver getDriver() {
        if (driver == null) {
            // Basic Chrome setup. In CI, set path via webdriver.chrome.driver or use WebDriverManager in real projects.
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
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
