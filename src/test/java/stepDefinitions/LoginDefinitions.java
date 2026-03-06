package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class LoginDefinitions {
    @Given("User is on Home Page")
    public void user_is_on_home_page() {

        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\ramadevi_chintharedd\\IdeaProjects\\TestProject\\src\\test\\Drivers\\chromedriver.exe");
        // Instantiate a ChromeDriver class.
        WebDriver driver = new ChromeDriver();

        // Maximize the browser
        driver.manage().window().maximize();

        // Launch Website
        driver.get("https://www.geeksforgeeks.org/");

    }

    @When("User Navigate to LogIn Page")
    public void user_navigate_to_log_in_page() {

    }

    @When("User enters UserName and Password")
    public void user_enters_user_name_and_password() {

    }

    @Then("Message displayed Login Successfully")
    public void message_displayed_login_successfully() {

    }

    @When("User LogOut from the Application")
    public void user_log_out_from_the_application() {

    }
    @When("User enters Credentials to LogIn")
    public void user_enters_credentials_to_log_in(DataTable dataTable) {
        List<List<String>> data= dataTable.asLists();
        System.out.println("Getting data from datatable :"+data.get(0).get(0));
        System.out.println("Getting Password from datatable  :"+data.get(0).get(1));
    }
    @And("User enters {string} and {string}")
    public void userEntersAnd(String arg0, String arg1) {
        System.out.println(arg0+" "+arg1);
    }
    @Then("Message displayed LogOut Successfully")
    public void message_displayed_log_out_successfully() {

    }

}
