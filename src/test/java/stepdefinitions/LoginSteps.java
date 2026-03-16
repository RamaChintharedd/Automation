package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
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
 * Cucumber Step Definitions for login-related feature files.
 * Uses Page Object Model to interact with pages.
 */
public class LoginSteps {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private AccountPage accountPage;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    // --- HomePage to Login navigation ---
    @Given("I am on the Demo Web Shop homepage \"{string}\"")
    public void i_am_on_the_demo_web_shop_homepage(String url) {
        homePage.navigateTo(url);
    }

    @When("I locate and click the \"Log in\" link or button")
    public void i_locate_and_click_the_login_link_or_button() {
        // Ensure locator exists and click
        Assert.assertTrue("Login link should be visible on homepage", homePage.isLoginLinkDisplayed());
        homePage.clickLogin();
    }

    @Then("the browser URL should be \"{string}\"")
    public void the_browser_url_should_be(String expectedUrl) {
        String actual = driver.getCurrentUrl();
        Assert.assertEquals("URL mismatch", expectedUrl, actual);
    }

    @And("the page should display an email input field")
    public void the_page_should_display_an_email_input_field() {
        Assert.assertTrue("Email input should be displayed", loginPage.isEmailDisplayed());
    }

    @And("the page should display a password input field")
    public void the_page_should_display_a_password_input_field() {
        Assert.assertTrue("Password input should be displayed", loginPage.isPasswordDisplayed());
    }

    @And("the page should display a \"Log in\" button")
    public void the_page_should_display_a_login_button() {
        Assert.assertTrue("Login button should be displayed", loginPage.isLoginButtonDisplayed());
    }

    // --- Successful Login ---
    @Given("I am on the login page \"{string}\"")
    public void i_am_on_the_login_page(String url) {
        driver.get(url);
    }

    @When("I enter a registered email \"{string}\" into the email field")
    public void i_enter_a_registered_email_into_the_email_field(String email) {
        loginPage.enterEmail(email);
    }

    @And("I enter the correct password \"{string}\" into the password field")
    public void i_enter_the_correct_password_into_the_password_field(String password) {
        loginPage.enterPassword(password);
    }

    @And("I click the \"Log in\" button")
    public void i_click_the_log_in_button() {
        loginPage.clickLogin();
    }

    @Then("I should be authenticated")
    public void i_should_be_authenticated() {
        // Authentication will be inferred by presence of account elements
        Assert.assertTrue("My account link should be visible after login", accountPage.isMyAccountVisible());
        Assert.assertTrue("Logout should be visible after login", accountPage.isLogoutVisible());
    }

    @And("I should be redirected to my account or dashboard page")
    public void i_should_be_redirected_to_my_account_or_dashboard_page() {
        String url = driver.getCurrentUrl();
        Assert.assertTrue("Expected account/dashboard in url", url.contains("/customer") || url.contains("/account") || accountPage.isMyAccountVisible());
    }

    @And("the page should display account-specific elements such as \"My account\" or order history")
    public void the_page_should_display_account_specific_elements() {
        Assert.assertTrue("Account-specific element 'My account' should be visible", accountPage.isMyAccountVisible());
    }

    @And("a \"Logout\" option should be visible")
    public void a_logout_option_should_be_visible() {
        Assert.assertTrue("Logout should be visible", accountPage.isLogoutVisible());
    }

    @And("no validation or authentication error messages should be shown")
    public void no_validation_or_authentication_error_messages_should_be_shown() {
        // If a global error exists, fail
        String globalErr = loginPage.getGlobalErrorText();
        Assert.assertTrue("No global error should be shown", globalErr.isEmpty());
    }

    // --- Empty email validation ---
    @When("I leave the email field empty")
    public void i_leave_the_email_field_empty() {
        loginPage.enterEmail("");
    }

    @And("I enter a valid password \"{string}\" into the password field")
    public void i_enter_a_valid_password_into_the_password_field(String password) {
        loginPage.enterPassword(password);
    }

    @Then("the login should not be performed")
    public void the_login_should_not_be_performed() {
        // We assert we are still on login page
        Assert.assertTrue("Should remain on login page after invalid attempt", driver.getCurrentUrl().contains("/login"));
    }

    @And("I should remain on the login page")
    public void i_should_remain_on_the_login_page() {
        Assert.assertTrue("Should remain on login page", driver.getCurrentUrl().contains("/login"));
    }

    @And("a validation error should be displayed indicating the email field cannot be empty or prompting \"Please enter your email\"")
    public void a_validation_error_should_be_displayed_indicating_the_email_field_cannot_be_empty() {
        String message = loginPage.getEmailValidationMessage();
        // message could be empty depending on site; if empty, check inline validations
        if (message == null || message.isEmpty()) {
            Assert.assertTrue("Inline field validation should be present", loginPage.hasFieldValidationMessages());
        } else {
            Assert.assertTrue("Validation message should indicate email required", message.toLowerCase().contains("enter") || message.toLowerCase().contains("email"));
        }
    }

    // --- Invalid email format ---
    @When("I enter an invalid email \"{string}\" into the email field")
    public void i_enter_an_invalid_email_into_the_email_field(String email) {
        loginPage.enterEmail(email);
    }

    @And("I enter a valid password \"{string}\" into the password field")
    public void i_enter_a_valid_password_into_the_password_field_2(String password) {
        loginPage.enterPassword(password);
    }

    @And("a validation error should be displayed indicating the email format is invalid (for example, \"Please enter a valid email address\")")
    public void a_validation_error_should_be_displayed_indicating_the_email_format_is_invalid() {
        String message = loginPage.getEmailValidationMessage();
        if (message == null || message.isEmpty()) {
            Assert.assertTrue("Inline field validation should be present", loginPage.hasFieldValidationMessages());
        } else {
            Assert.assertTrue("Validation message should indicate invalid email", message.toLowerCase().contains("valid") || message.toLowerCase().contains("email"));
        }
    }
}
