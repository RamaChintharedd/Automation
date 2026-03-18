package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverFactory;

import java.time.Duration;

/**
 * Cucumber step definitions implementing the scenarios provided.
 * Uses Page Objects for interactions and DriverFactory for WebDriver lifecycle.
 */
public class LoginSteps {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        // instantiate page objects
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @After
    public void tearDown() {
        // Clean up - do not quit between examples if test runner handles lifecycle differently.
        DriverFactory.quitDriver();
    }

    // ---------- Navigation Steps ----------

    @Given("I am on the Demo Web Shop homepage")
    public void i_am_on_the_demo_web_shop_homepage() {
        homePage.navigateTo("https://demowebshop.tricentis.com/");
    }

    @When("I locate and click the 'Log in' link or button")
    public void i_locate_and_click_the_log_in_link_or_button() {
        Assert.assertTrue("Login link should be present on homepage", homePage.isLoginLinkPresent());
        homePage.clickLoginLink();
    }

    @Then("the browser URL should change to 'https://demowebshop.tricentis.com/login'")
    public void the_browser_url_should_change_to_login() {
        // give some time for navigation
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        String current = driver.getCurrentUrl();
        Assert.assertTrue("Expected to be on login URL, but was: " + current,
                current.startsWith("https://demowebshop.tricentis.com/login"));
    }

    @Then("the login page should be displayed")
    public void the_login_page_should_be_displayed() {
        Assert.assertTrue("Login page should display its header", loginPage.isDisplayed());
    }

    // ---------- Email input presence & behavior ----------

    @Given("I navigate to 'https://demowebshop.tricentis.com/login'")
    public void i_navigate_to_login_url() {
        homePage.navigateTo("https://demowebshop.tricentis.com/login");
    }

    @When("I inspect the page for an input labeled or placeholder 'Email'")
    public void i_inspect_the_page_for_email_input() {
        // this step will be validated by subsequent Then steps
    }

    @Then("an email input field should be present")
    public void an_email_input_field_should_be_present() {
        Assert.assertTrue("Email input should be present on login page", loginPage.isEmailInputPresent());
    }

    @And("I should be able to focus the email input field")
    public void i_should_be_able_to_focus_the_email_input_field() {
        loginPage.focusEmail();
        // If no exception thrown assume success
    }

    @And("I should be able to type text into the email input field")
    public void i_should_be_able_to_type_text_into_the_email_input_field() {
        loginPage.typeEmail("test@example.com");
    }

    // ---------- Successful login ----------

    @Given("I have a registered email 'registered@example.com' and the correct password 'correctPassword'")
    public void i_have_registered_credentials() {
        // test data is provided by the scenario; no action needed here other than storing if necessary
    }

    @When("I enter the registered email into the Email field")
    public void i_enter_registered_email_into_email_field() {
        loginPage.typeEmail("registered@example.com");
    }

    @And("I enter the correct password into the Password field")
    public void i_enter_correct_password_into_password_field() {
        loginPage.typePassword("correctPassword");
    }

    @And("I click the 'Log in' button")
    public void i_click_the_log_in_button() {
        loginPage.clickLogin();
    }

    @Then("I should be authenticated")
    public void i_should_be_authenticated() {
        // Authentication detection: check for account-related elements on the page after login
        // Wait briefly for redirect
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        Assert.assertTrue("User should be at dashboard or account page after successful login", dashboardPage.isAt());
    }

    @And("I should be redirected to my account or dashboard page")
    public void i_should_be_redirected_to_account_or_dashboard() {
        String url = driver.getCurrentUrl();
        Assert.assertTrue("URL should reflect account/dashboard area", url.contains("/customer") || url.contains("/account") || dashboardPage.isAt());
    }

    @And("account specific elements such as 'My account' or order history should be visible")
    public void account_specific_elements_should_be_visible() {
        Assert.assertTrue("My account or order history not visible", dashboardPage.hasAccountElementsVisible());
    }

    // ---------- Empty Email validation ----------

    @And("I leave the Email field empty")
    public void i_leave_the_email_field_empty() {
        loginPage.typeEmail(
