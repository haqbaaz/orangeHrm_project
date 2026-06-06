package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

/**
 * Page Object Class for OrangeHRM Login Page
 */
public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators using @FindBy annotation
    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//h5[@class='oxd-text oxd-text--h5 orangehrm-login-title']")
    private WebElement loginTitle;

    @FindBy(xpath = "//div[@class='oxd-alert oxd-alert--error']//div[@class='oxd-alert-content']")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[contains(text(), 'Forgot your password')]")
    private WebElement forgotPasswordLink;

    /**
     * Constructor to initialize WebDriver and PageFactory
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Get the page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Enter username in the username field
     */
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    /**
     * Enter password in the password field
     */
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    /**
     * Click the login button
     */
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    /**
     * Perform login with username and password
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    /**
     * Check if login title is displayed
     */
    public boolean isLoginTitleDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get login title text
     */
    public String getLoginTitleText() {
        wait.until(ExpectedConditions.visibilityOf(loginTitle));
        return loginTitle.getText();
    }

    /**
     * Check if error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get error message text
     */
    public String getErrorMessageText() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    /**
     * Click on Forgot Password link
     */
    public void clickForgotPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink));
        forgotPasswordLink.click();
    }

    /**
     * Check if all login page elements are visible
     */
    public boolean areAllElementsVisible() {
        return wait.until(ExpectedConditions.visibilityOf(usernameField)) != null &&
                wait.until(ExpectedConditions.visibilityOf(passwordField)) != null &&
                wait.until(ExpectedConditions.visibilityOf(loginButton)) != null &&
                wait.until(ExpectedConditions.visibilityOf(loginTitle)) != null;
    }

}
