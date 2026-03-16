package com.example.tests;

import com.example.pages.AccountPage;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.example.utils.Config;
import com.example.utils.WaitUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// Tests derived from the provided BDD scenarios. Each test maps to one scenario.
public class LoginTests extends BaseTest {

    @Test
    @DisplayName("Navigate to Login Page from Homepage")
    public void testNavigateToLoginPage() {
        HomePage home = new HomePage(driver);
        home.open(Config.BASE_URL);

        LoginPage login = home.clickLogin();
        // Wait for URL
        WaitUtils.waitForUrlContains(driver, "/login");
        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"), "Should be on login page");

        Assertions.assertTrue(login.isEmailDisplayed(), "Email: An array of objects, each with file_path and code. - https://github.com/RamaChintharedd/Automation: The GitHub repository name or URL. - seleniumCode: The branch to push to. - code pushed successfully.: The commit message.  Your task: For each file in files: 1. Create or update the file at file_path in the specified repository and branch. 2. Commit the changes with commit_message.  Return STRICT JSON only: {   "push_results": [     {       "file_path": "",       "commit_url": "",       "status": "File pushed"     }   ] } Do not return explanations. Do not return markdown. Only JSON.");
    }
}
