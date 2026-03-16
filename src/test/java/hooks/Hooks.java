package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverFactory;

// Cucumber Hooks to manage WebDriver lifecycle
public class Hooks {

    @Before
    public void beforeScenario() {
        // Create WebDriver before each scenario
        DriverFactory.createDriver();
    }

    @After
    public void afterScenario() {
        // Quit WebDriver after each scenario
        DriverFactory.quitDriver();
    }
}
