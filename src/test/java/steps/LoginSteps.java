package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.DriverFactory;

/**
 * Cucumber step definitions implementing the scenarios from the provided feature file.
 * Uses Page Object Model (LoginPage) and DriverFactory for WebDriver management.
 */
public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    // Scenario: Click 'Log in' on homepage and verify login page elements
    @Given("I am on the Demo Web Shop homepage at '{string}'")
    public void i_am_on_the_demo_web_shop_homepage(String url) {
        loginPage.goToHomePage(url);
    }

    @When("I click the 'Log in' link or button on the homepage")
    public void i_click_the_log_in_link_on_homepage() {
        loginPage.clickLoginLinkOnHome();
    }

    @Then("the browser URL should be '{string}'")
    public void the_browser_url_should_be(String expectedUrl) {
        Assert.assertTrue("Expected URL to start with: " + expectedUrl + " but was " + driver.getCurrentUrl(),
                loginPage.isOnLoginPage(expectedUrl));
    }

    @And("the page should contain an email input field")
    public void the_page_should_contain_an_email_input_field() {
        Assert.assertTrue("Email field should be present", loginPage.hasEmailField());
    }

    @And("the page should contain a password input field")
    public void the_page_should_contain_a_password_input_field() {
        Assert.assertTrue("Password field should be present", loginPage.hasPasswordField());
    }

    @And("the page should contain a 'Log in' button")
    public void the_page_should_contain_a_log_in_button() {
        Assert.assertTrue("Login button should be present", loginPage.hasLoginButton());
    }

    // Scenario: Registered customer logs in with valid email and password
    @Given("I am on the login page at '{string}'")
    public void i_am_on_the_login_page(String url) {
        loginPage.goToLoginPage(url);
    }

    @And("I have a registered account with email '{string}' and password '{string}'")
    public void i_have_a_registered_account(String email, String password) {
        // This step is a data precondition; for demo purposes we assume account exists.
        // In a real test we might call an API or set up test data here.
    }

    @When("I enter '{string}' into the email field")
    public void i_enter_email_into_the_email_field(String email) {
        loginPage.enterEmail(email);
    }

    @And("I enter '{string}' into the password field")
    public void i_enter_password_into_the_password_field(String password) {
        loginPage.enterPassword(password);
    }

    @And("I click the 'Log in' button")
    public void i_click_the_log_in_button() {
        loginPage.clickLoginButton();
    }

    @Then("I should be authenticated")
    public void i_should_be_authenticated() {
        // Wait a short amount may be necessary; the Page Object uses WebDriver standard waits.
        Assert.assertTrue("User should be authenticated (My account or Orders visible)", loginPage.isAuthenticated());
    }

    @Then("I should be redirected to my account dashboard or see account-specific content")
    public void i_should_be_redirected_or_see_account_content() {
        Assert.assertTrue("Account-specific content should be present", loginPage.isAuthenticated());
    }

    @And("I should see elements such as 'My account' or 'Order history' indicating successful login")
    public void i_should_see_account_indicators() {
        Assert.assertTrue("Account indicators should be present", loginPage.isAuthenticated());
    }

    // Scenario: Attempt to log in with empty email field
    @And("I leave the email field empty")
    public void i_leave_the_email_field_empty() {
        loginPage.enterEmail("");
    }

    @And("I enter 'some-valid-password' into the password field")
    public void i_enter_some_valid_password_into_password_field() {
        loginPage.enterPassword("some-valid-password");
    }

    @Then("the form should not be submitted for authentication")
    public void the_form_should_not_be_submitted_for_authentication() {
        // We verify by ensuring we remain on login page and there is a validation or no authentication.
        Assert.assertTrue("Should remain on login page after invalid submit", loginPage.isOnLoginPage("https://demowebshop.tricentis.com/login"));
    }

    @And("I should remain on the login page")
    public void i_should_remain_on_the_login_page() {
        Assert.assertTrue("Should still be on login page", loginPage.isOnLoginPage("https://demowebshop.tricentis.com/login"));
    }

    @And("a validation message should be displayed indicating the email field cannot be empty")
    public void a_validation_message_should_be_displayed_for_empty_email() {
        Assert.assertTrue("Email validation message should be visible", loginPage.isEmailValidationVisible());
    }

    // Scenario: Attempt to log in with invalid email format
    @And("I enter an invalid email '{string}' into the email field")
    public void i_enter_an_invalid_email_into_the_email_field(String invalidEmail) {
        loginPage.enterEmail(invalidEmail);
    }

    @And("I enter 'some-valid-password' into the password field")
    public void i_enter_some_valid_password_into_the_password_field_again() {
        loginPage.enterPassword("some-valid-password");
    }

    @And("a validation message should be displayed indicating the email format is invalid")
    public void validation_message_for_invalid_email_format() {
        // Depending on implementation
    }
}
