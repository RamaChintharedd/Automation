package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverFactory;

// Step definitions implementing the Gherkin scenarios using POM
public class LoginSteps {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @After
    public void tearDown() {
        // Keep driver alive across scenarios if needed. For now, quit to keep tests isolated.
        DriverFactory.quitDriver();
    }

    @Given("I am on the Demo Web Shop homepage at \"{string}\"")
    public void i_am_on_homepage(String url) {
        homePage.open(url);
    }

    @When("I locate and click the \"Log in\" link or button")
    public void i_click_login_from_homepage() {
        homePage.clickLogin();
    }

    @Then("the browser URL should be \"{string}\"")
    public void browser_url_should_be(String expectedUrl) {
        // Allow redirect time
        String actual = driver.getCurrentUrl();
        Assert.assertEquals("URL did not match expected", expectedUrl, actual);
    }

    @Then("the login page heading or title indicating \"Log in\" should be visible")
    public void login_heading_should_be_visible() {
        String heading = loginPage.getHeadingText();
        Assert.assertTrue("Login heading did not contain 'Log in' but was: " + heading, heading.toLowerCase().contains("log in"));
    }

    @Given("I navigate to the login page at \"{string}\"")
    public void navigate_to_login(String url) {
        driver.get(url);
    }

    @When("I inspect the login form area")
    public void inspect_login_form() {
        // No-op here; presence checks in Then
    }

    @Then("I should see an input field labeled or identified for email (for example type=\"email\" or label \"Email\")")
    public void email_input_field_present() {
        Assert.assertTrue("Email input is not visible and enabled", loginPage.isEmailVisibleAndEnabled());
    }

    @And("the email input field should be visible and enabled for user input")
    public void email_input_visible_and_enabled() {
        Assert.assertTrue(loginPage.isEmailVisibleAndEnabled());
    }

    @Given("a registered user exists with email \"{string}\" and password \"{string}\"")
    public void registered_user_exists(String email, String password) {
        // In real test you might call API or DB to ensure user exists.
        // Here we assume the user already exists as per test data.
    }

    @When("I enter \"{string}\" into the email field")
    public void enter_email(String email) {
        loginPage.enterEmail(email);
    }

    @And("I enter \"{string}\" into the password field")
    public void enter_password(String password) {
        loginPage.enterPassword(password);
    }

    @And("I click the \"Log in\" button")
    public void click_login_button() {
        loginPage.clickLogin();
    }

    @Then("I should be authenticated and redirected per application behavior")
    public void should_be_authenticated_and_redirected() {
        // Typically application redirects to home; check logout or account presence
        Assert.assertTrue("Logout or account link not visible after login", loginPage.isLogoutVisible());
    }

    @And("account-specific elements such as the user name, a \"Log out\" link or \"My account\" link should be visible")
    public void account_elements_visible() {
        Assert.assertTrue("Logout link not visible", loginPage.isLogoutVisible());
    }

    @And("I should be able to access protected pages like \"My account\" or \"Order history\"")
    public void access_protected_pages() {
        // Try to navigate to My account
        driver.get("https://demowebshop.tricentis.com/customer/info");
        String current = driver.getCurrentUrl();
        Assert.assertTrue("Did not reach my account page after login", current.contains("customer"));
    }

    @When("I enter an incorrect password \"{string}\" into the password field")
    public void enter_incorrect_password(String wrongPass) {
        loginPage.enterPassword(wrongPass);
    }

    @Then("the login attempt should be rejected")
    public void login_should_be_rejected() {
        // Error message or staying on login page
        Assert.assertTrue("Expected error message to be displayed for failed login", loginPage.isErrorMessageDisplayed());
    }

    @And("an explicit error message indicating incorrect credentials should be displayed")
    public void explicit_error_message() {
        String msg = loginPage.getErrorMessage();
        Assert.assertTrue("Error message did not indicate bad credentials: " + msg, msg.toLowerCase().contains("login was unsuccessful") || msg.toLowerCase().contains("incorrect"));
    }

    @And("I should remain on the login page without access to account features")
    public void remain_on_login_page() {
        Assert.assertTrue("Still on login page expected", driver.getCurrentUrl().contains("/login"));
        Assert.assertFalse("Logout should not be visible after failed login", loginPage.isLogoutVisible());
    }

    @When("I leave the email input field blank")
    public void leave_email_blank() {
        loginPage.enterEmail("");
    }

    @Then("the system should prevent submission or show validation errors")
    public void system_should_prevent_submission_or_show_validation() {
        login: An array of objects, each with file_path and code. - https://github.com/RamaChintharedd/Automation: The GitHub repository name or URL. - seleniumCode: The branch to push to. - code pushed successfully.: The commit message.  Your task: For each file in files: 1. Create or update the file at file_path in the specified repository and branch. 2. Commit the changes with commit_message.  Return STRICT JSON only: {   "push_results": [     {       "file_path": "",       "commit_url": "",       "status": "File pushed"     }   ] } Do not return explanations. Do not return markdown. Only JSON.
