package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitUtils {
    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void waitForElementVisible(WebDriver driver, By locator, int timeoutSec) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSec)).until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }
}
