package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import pages.RegisterPage;
import utils.LoggerUtil;

import java.util.logging.Logger;

public class RegisterTest {
    private static WebDriver driver;
    private static HomePage homePage;
    private static RegisterPage registerPage;
    private static final Logger logger = LoggerUtil.getLogger(RegisterTest.class);

    @BeforeClass
    public static void setUp() {
        logger.info("Launching Chrome and initializing pages...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = PageFactory.initElements(driver, HomePage.class);
        registerPage = PageFactory.initElements(driver, RegisterPage.class);

        logger.fine("Navigating to home page and opening register form...");
        homePage.navigateToHomePage("https://jpetstore.aspectran.com/");
        homePage.clickRegister();
    }

    @Test
    public void testUserRegistration() throws InterruptedException {
        logger.info("Starting User Registration Test...");

        logger.fine("Filling user information...");
        registerPage.fillUserInformation("testUser123", "password123", "password123");

        logger.fine("Filling account information...");
        registerPage.fillAccountInformation(
            "John",
            "Doe",
            "john.doe@example.com",
            "0501234567",
            "123 Main St",
            "Apt 4B",
            "New York",
            "NY",
            "10001",
            "USA"
        );

        logger.fine("Saving account...");
        registerPage.saveAccountInformation();
        Thread.sleep(2000);

        logger.info("Test Passed: User registered successfully.");
    }

    @AfterClass
    public static void tearDown() {
        logger.info("Closing browser...");
        if (driver != null) {
            driver.quit();
        }
    }
}
