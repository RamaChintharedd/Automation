package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverFactory;

public class LoginSteps {

    private WebDriver driver = DriverFactory.getDriver();
    private HomePage homePage = new HomePage(DriverFactory.getDriver());
    private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

    // --- Navigation scenarios ---
    @Given("I am on the Demo Web Shop homepage '{string}'")
    public void i_am_on_the_demo_web_shop_homepage(String url) {
        homePage.open(url);
        Assert.assertTrue("Homepage should load and show login link", homePage.isLoginLinkPresent());
    }

    @When("I locate and click the 'Log in' link or button on the homepage")
    public void i_locate_and_click_the_log_in_link_or_button_on_the_homepage() {
        homePage.clickLogin();
    }

    @Then("the browser navigates to '{string}'")
    public void the_browser_navigates_to(String expectedUrl) {
        Assert.assertTrue("Expected URL after clicking login", DriverFactory.getDriver().getCurrentUrl().startsWith(expectedUrl));
    }

    @Then("the login page is displayed successfully")
    public void the_login_page_is_displayed_successfully() {
        Assert.assertTrue("Login page should be displayed", loginPage.isAt());
    }

    @Then("the login form is visible and ready for input")
    public void the_login_form_is_visible_and_ready_for_input() {
        Assert.assertTrue("Login form elements should be visible", loginPage.isLoginFormVisible());
    }

    // --- Login Page Elements ---
    @Given("I navigate to the login page '{string}'")
    public void i_navigate_to_the_login_page(String url) {
        loginPage.open(url);
        Assert.assertTrue("Should be on login page", loginPage.isAt());
    }

    @Then("I should see an input field for the user's email address (label 'Email' or input type 'email')")
    public void i_should_see_email_input_field() {
        Assert.assertTrue("Email input should be visible", loginPage.isLoginFormVisible());
    }

    @And("the email input field can receive focus")
    public void the_email_input_field_can_receive_focus() {
        Assert.assertTrue("Email field should accept focus", loginPage.emailFieldCanReceiveFocus());
    }

    @And("I can type text into the email input field")
    public void i_can_type_text_into_the_email_input_field() {
        loginPage.enterEmail("test@example.com");
        // If no exception thrown, typing is successful. Optionally assert value attribute if needed.
    }

    // --- Login Validation - Required Email ---
    @Given("I am on the login page '{string}'")
    public void i_am_on_the_login_page(String url) {
        loginPage.open(url);
        Assert.assertTrue("Should be on login page", loginPage.isAt());
    }

    @Given("the email field is empty")
    public void the_email_field_is_empty() {
        loginPage.enterEmail("");
    }

    @And("I enter a valid password into the password field")
    public void i_enter_a_valid_password_into_the_password_field() {
        loginPage.enterPassword("ValidPassword123");
    }

    @When("I click the 'Log in' button")
    public void i_click_the_log_in_button() {
        loginPage.clickLogin();
    }

    @Then("the login attempt is blocked")
    public void the_login_attempt_is_blocked() {
        // For blocked attempts we expect to remain on login page and not be authenticated
        Assert.assertTrue("Should remain on login page after blocked attempt", loginPage.isOnLoginPage());
        Assert.assertFalse("User should not be authenticated after blocked attempt", loginPage.isAuthenticated());
    }

    @Then("a validation error message is displayed indicating the email field cannot be empty")
    public void a_validation_error_message_is_displayed_for_empty_email() {
        Assert.assertTrue("Validation error mentioning email should be present", loginPage.isValidationErrorPresentForEmail());
    }

    @Then("I remain on the login page")
    public void i_remain_on_the_login_page() {
        Assert.assertTrue("Should still be on login page", loginPage.isOnLoginPage());
    }

    @Then("I am not authenticated")
    public void i_am_not_authenticated() {
        Assert.assertFalse("User must not be authenticated", loginPage.isAuthenticated());
    }

    // --- Login Validation - Email Format (Scenario Outline) ---
    @Given("I enter '{string}' into the email field")
    public void i_enter_invalid_email_into_the_email_field(String email) {
        loginPage.enterEmail(email);
    }

    @Then("a validation error message is displayed indicating the email format is invalid")
    public void a_validation_error_message_is_displayed_for_invalid_format() {
        Assert.assertTrue("Validation about invalid email format should be present", loginPage.isValidationErrorPresentForEmail());
    }

    // --- Successful Login ---
    @Given("a registered user exists with email '{string}' and password '{string}'")
    public void
