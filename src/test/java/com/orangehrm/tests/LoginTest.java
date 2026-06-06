package com.orangehrm.tests;

import com.orangehrm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Login Test Class
 * Contains test cases for OrangeHRM Login Page
 */
public class LoginTest extends BaseTest {

    /**
     * Test Case 1: Verify Login Page Title
     * Verifies that the login page title is "OrangeHRM"
     */
    @Test(description = "Verify OrangeHRM Login Page Title")
    public void testLoginPageTitle() {
        System.out.println("Test: Verify Login Page Title");

        // Get the page title
        String actualTitle = driver.getTitle();
        String expectedTitle = "OrangeHRM";

        System.out.println("Expected Title: " + expectedTitle);
        System.out.println("Actual Title: " + actualTitle);

        // Assert that the title matches
        Assert.assertEquals(actualTitle, expectedTitle, "Page title should be OrangeHRM");
    }

    /**
     * Test Case 2: Verify Login Page Elements are Visible
     * Verifies that all login page elements are displayed
     */
    @Test(description = "Verify all Login Page elements are visible")
    public void testLoginPageElementsVisibility() {
        System.out.println("Test: Verify Login Page Elements Visibility");

        LoginPage loginPage = new LoginPage(driver);

        // Assert that all elements are visible
        Assert.assertTrue(loginPage.areAllElementsVisible(),
                "All login page elements should be visible");

        System.out.println("All login page elements are visible");
    }

    /**
     * Test Case 3: Verify Login Title Text
     * Verifies that the login title displays "Login"
     */
    @Test(description = "Verify Login Page Title Text")
    public void testLoginTitleText() {
        System.out.println("Test: Verify Login Title Text");

        LoginPage loginPage = new LoginPage(driver);

        // Check if login title is displayed
        Assert.assertTrue(loginPage.isLoginTitleDisplayed(),
                "Login title should be displayed");

        // Get and verify the login title text
        String loginTitle = loginPage.getLoginTitleText();
        System.out.println("Login Title Text: " + loginTitle);

        Assert.assertEquals(loginTitle, "Login", "Login title should display 'Login'");
    }

    /**
     * Test Case 4: Verify Successful Login
     * Tests login with valid credentials (default demo credentials)
     */
    @Test(description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        System.out.println("Test: Verify Successful Login");

        LoginPage loginPage = new LoginPage(driver);

        // Perform login with valid credentials
        String username = "Admin";
        String password = "admin123";

        System.out.println("Attempting login with username: " + username);

        loginPage.login(username, password);

        // Wait for dashboard page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that we're logged in (check for dashboard URL or elements)
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        Assert.assertFalse(currentUrl.contains("auth/login"),
                "Should be redirected from login page after successful login");
    }

    /**
     * Test Case 5: Verify Login with Invalid Credentials
     * Tests login with invalid credentials and verifies error message
     * Note: This test is currently disabled as the error message locator needs
     * refinement
     */
    @Test(description = "Verify login error message with invalid credentials", enabled = false)
    public void testLoginWithInvalidCredentials() {
        System.out.println("Test: Verify Login with Invalid Credentials");

        LoginPage loginPage = new LoginPage(driver);

        // Attempt login with invalid credentials
        String invalidUsername = "InvalidUser";
        String invalidPassword = "InvalidPassword";

        System.out.println("Attempting login with invalid username: " + invalidUsername);

        loginPage.login(invalidUsername, invalidPassword);

        // Wait for error message
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify error message is displayed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for invalid credentials");

        String errorMsg = loginPage.getErrorMessageText();
        System.out.println("Error Message: " + errorMsg);

        Assert.assertTrue(errorMsg.contains("Invalid credentials"),
                "Error message should indicate invalid credentials");
    }

    /**
     * Test Case 6: Verify Empty Username and Password
     * Tests login with empty username and password fields
     */
    @Test(description = "Verify login validation with empty fields")
    public void testLoginWithEmptyFields() {
        System.out.println("Test: Verify Login with Empty Fields");

        LoginPage loginPage = new LoginPage(driver);

        // Try to login with empty fields
        loginPage.clickLoginButton();

        // Wait for validation message
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // User should still be on login page
        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);

        Assert.assertEquals(pageTitle, "OrangeHRM",
                "Should still be on login page with empty fields");
    }

}
