package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

/**
 * Cucumber hooks to initialize and quit WebDriver before/after each scenario.
 */
public class TestHooks {

    @Before
    public void setUp() {
        WebDriver driver = DriverFactory.getDriver();
        // additional setup can be added here
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
