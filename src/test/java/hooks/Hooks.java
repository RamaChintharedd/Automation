package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverFactory;

/**
 * Cucumber hooks to initialize and dispose WebDriver before/after scenarios.
 */
public class Hooks {

    @Before
    public void beforeScenario() {
        DriverFactory.initDriver();
    }

    @After
    public void afterScenario() {
        DriverFactory.quitDriver();
    }
}
