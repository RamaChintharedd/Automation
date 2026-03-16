package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverFactory;

/**
 * Step definitions implementing the Gherkin steps for login-related scenarios.
 * Uses Page Object Model (HomePage, LoginPage, AccountPage) and DriverFactory.
 */
public class LoginSteps {

    private final WebDriver driver = DriverFactory.getDriver();
    private HomePage homePage;
    private LoginPage loginPage;
    private AccountPage accountPage;

    private String registeredEmail = "user@example.com"; // default; can be overridden in steps
    private String registeredPassword = "correct_password";

    // ---------- Navigation steps ----------

    @Given("I am on the Demo Web Shop homepage \"{string}\"")
    public void i_am_on_the_Demo_Web_Shop_homepage(String url) {
        homePage = new HomePage(driver);
        homePage.goToUrl(url);
    }

    @When("I locate and click the \"Log in\" link or button")
    public void i_locate_and_click_the_Log_in_link_or_button() {
        // Ensure homepage object exists
        if (homePage == null) homePage = new HomePage(driver);
        Assert.assertTrue("Login link is not present on homepage", homePage.isLoginLinkPresent());
        homePage.clickLogin();
        loginPage = new LoginPage(driver);
    }

    @Then("I should be navigated to the login page")
    public void i_should_be_navigated_to_the_login_page() {
        Assert.assertTrue("Expected to be on login page", loginPage.isOnLoginPage());
    }

    @Then("the browser URL should be \"{string}\"")
    public void the_browser_URL_should_be(String expectedUrl) {
        String current = driver.getCurrentUrl();
        Assert.assertEquals("Browser URL did not match", expectedUrl, current);
    }

    @Then("the login page should be displayed")
    public void the_login_page_should_be_displayed() {
        Assert.assertTrue("Login page not displayed", loginPage.isOnLoginPage());
    }

    // ---------- Login page field checks ----------

    @Given("I am on the login page \"{string}\"")
    public void i_am_on_the_login_page(String url) {
        loginPage = new LoginPage(driver);
        driver.get(url);
        Assert.assertTrue("Not on login page after navigation", loginPage.isOnLoginPage());
    }

    @When("I inspect the login form")
    public void i_inspect_the_login_form() {
        if (loginPage == null) loginPage = new LoginPage(driver);
        Assert.assertTrue("Email field not visible/enabled", loginPage.isEmailFieldVisibleAndEnabled());
    }

    @Then("the email input should be visible and enabled")
    public void the_email_input_should_be_visible_and_enabled() {
        Assert.assertTrue(loginPage.isEmailFieldVisibleAndEnabled());
    }

    // ---------- Login actions ----------

    @When("I submit valid credentials")
    public void i_submit_valid_credentials() {
        if (loginPage == null) loginPage = new LoginPage(driver);
        loginPage.enterEmail(registeredEmail);
        loginPage.enterPassword(registeredPassword);
        loginPage.clickLogin();
        accountPage = new AccountPage(driver);
    }

    @Then("I should be logged in")
    public void i_should_be_logged_in() {
        Assert.assertTrue("User should be logged in", accountPage.isLoggedIn());
    }

    @When("I attempt to login with invalid credentials")
    public void i_attempt_to_login_with_invalid_credentials() {
        if (loginPage == null) loginPage = new LoginPage(driver);
        loginPage.enterEmail("invalid@example.com");
        loginPage.enterPassword("wrong");
        loginPage.clickLogin();
    }

    @Then("I should see a validation message containing \"{string}\"")
    public void i_should_see_a_validation_message_containing(String expected) {
        Assert.assertTrue("Expected validation message not found", loginPage.hasValidationErrorContaining(expected));
    }

    @And("I use registered credentials \"{string}\" and \"{string}\"")
    public void i_use_registered_credentials_and(String email, String password) {
        this.registeredEmail = email;
        this.registeredPassword = password;
    }
}
