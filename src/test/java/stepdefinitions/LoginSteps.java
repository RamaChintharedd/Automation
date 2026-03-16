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
 * Step definitions implementing the Gherkin steps for login scenarios.
 * Uses Page Object Model classes for page interactions.
 */
public class LoginSteps {

    private WebDriver driver = DriverFactory.getDriver();
    private HomePage homePage = new HomePage(driver);
    private LoginPage loginPage = new LoginPage(driver);
    private AccountPage accountPage = new AccountPage(driver);

    // Simple in-memory storage for credentials used across steps
    private String storedEmail;
    private String storedPassword;

    @Given("I am on the Demo Web Shop homepage '{string}'")
    public void i_am_on_the_demo_web_shop_homepage(String url) {
        homePage.open(url);
    }

    @When("I click the 'Log in' link on the homepage")
    public void i_click_the_log_in_link_on_the_homepage() {
        homePage.clickLoginLink();
    }

    @Then("the browser is redirected to a login page with a URL that contains '/login'")
    public void the_browser_is_redirected_to_a_login_page_with_a_url_that_contains_login() {
        Assert.assertTrue("URL should contain /login", loginPage.getCurrentUrl().contains("/login"));
    }

    @And("I see an email input field on the page")
    public void i_see_an_email_input_field_on_the_page() {
        Assert.assertTrue("Email field should be visible", loginPage.isEmailFieldVisible());
    }

    @And("I see a password input field on the page")
    public void i_see_a_password_input_field_on_the_page() {
        Assert.assertTrue("Password field should be visible", loginPage.isPasswordFieldVisible());
    }

    @Given("a registered account exists with email '{string}' and password '{string}'")
    public void a_registered_account_exists_with_email_and_password(String email, String password) {
        // In real automation we'd ensure the account exists via API or test setup.
        // For these step definitions we store the values and assume the account exists.
        this.storedEmail = email;
        this.storedPassword = password;
    }

    @Given("I am on the login page '{string}'")
    public void i_am_on_the_login_page(String url) {
        driver.get(url);
    }

    @When("I enter '{string}' into the email field")
    public void i_enter_into_the_email_field(String email) {
        loginPage.enterEmail(email);
    }

    @When("I enter '{string}' into the password field")
    public void i_enter_into_the_password_field(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click the 'Log in' button")
    public void i_click_the_log_in_button() {
        loginPage.submitLogin();
    }

    @Then("I should be authenticated successfully")
    public void i_should_be_authenticated_successfully() {
        // Check account-specific elements
        Assert.assertTrue("Logout link should be visible after successful login", accountPage.isLogoutVisible());
    }

    @Then("I should be redirected to my account or dashboard page")
    public void i_should_be_redirected_to_my_account_or_dashboard_page() {
        // Typically URL or presence of My account link indicates successful login
        Assert.assertTrue("My account link should be visible", accountPage.isMyAccountVisible());
    }

    @Then("I should see account-specific elements such as a 'Log out' link and a 'My account' link or my user name")
    public void i_should_see_account_specific_elements_such_as_a_log_out_link_and_a_my_account_link_or_my_user_name() {
        Assert.assertTrue("Logout or My account should be visible after login", accountPage.isLogoutVisible() || accountPage.isMyAccountVisible());
    }

    @When("I leave the email field empty")
    public void i: An array of objects, each with file_path and code. - https://github.com/RamaChintharedd/Automation: The GitHub repository name or URL. - seleniumCode: The branch to push to. - code pushed successfully.: The commit message.  Your task: For each file in files: 1. Create or update the file at file_path in the specified repository and branch. 2. Commit the changes with commit_message.  Return STRICT JSON only: {   "push_results": [     {       "file_path": "",       "commit_url": "",       "status": "File pushed"     }   ] } Do not return explanations. Do not return markdown. Only JSON.
