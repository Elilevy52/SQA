package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import pages.SignInPage;
import utils.LoggerUtil;

import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class SignInTest {
    private WebDriver driver;
    private HomePage homePage;
    private SignInPage signInPage;
    private static final Logger logger = LoggerUtil.getLogger(SignInTest.class);

    @Before
    public void setUp() {
        logger.info("Launching Chrome and initializing pages...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = PageFactory.initElements(driver, HomePage.class);
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        homePage.navigateToHomePage("https://jpetstore.aspectran.com/");
    }

    @Test
    public void testValidLogin() throws InterruptedException {
        logger.info("Starting Valid Login Test...");

        homePage.clickSignIn();
        signInPage.enterUsername("j2ee");
        signInPage.enterPassword("j2ee");
        signInPage.clickLogin();

        Thread.sleep(3000); 

        boolean isLoggedIn = driver.getPageSource().contains("Sign Out");

        if (isLoggedIn) {
            logger.info("Test Passed: User successfully logged in.");
        } else {
            logger.warning("Test Failed: User did not log in.");
        }

        assertTrue("User should be logged in successfully.", isLoggedIn);
    }

    @After
    public void tearDown() {
        logger.info("Closing browser...");
        if (driver != null) {
            driver.quit();
        }
    }
}
