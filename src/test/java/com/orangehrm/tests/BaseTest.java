package com.orangehrm.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base Test Class with common setup and teardown methods
 */
public class BaseTest {

    protected WebDriver driver;

    /**
     * Setup method to initialize WebDriver before each test
     */
    @BeforeMethod
    public void setUp() {
        // Setup Chrome Driver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Navigate to OrangeHRM login page
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Maximize window and set implicit wait
        driver.manage().window().maximize();
    }

    /**
     * Teardown method to close WebDriver after each test
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
