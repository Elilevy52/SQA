package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.SearchPage;
import pages.SignInPage;
import utils.LoggerUtil;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class CheckoutTest {
    private static WebDriver driver;
    private static HomePage homePage;
    private static SearchPage searchPage;
    private static CartPage cartPage;
    private static CheckoutPage checkoutPage;
    private static SignInPage signInPage;
    private static final Logger logger = LoggerUtil.getLogger(CheckoutTest.class);

    @BeforeClass
    public static void setUp() throws InterruptedException {
        logger.info("Setup started. Launching Chrome...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        logger.fine("Initializing Page Objects...");
        homePage = PageFactory.initElements(driver, HomePage.class);
        searchPage = PageFactory.initElements(driver, SearchPage.class);
        cartPage = PageFactory.initElements(driver, CartPage.class);
        checkoutPage = PageFactory.initElements(driver, CheckoutPage.class);
        signInPage = PageFactory.initElements(driver, SignInPage.class);

        logger.fine("Navigating to home page...");
        homePage.navigateToHomePage("https://jpetstore.aspectran.com/");

        logger.fine("Performing login...");
        homePage.clickSignIn();
        Thread.sleep(2000);
        signInPage.enterUsername("j2ee");
        signInPage.enterPassword("j2ee");
        signInPage.clickLogin();
        Thread.sleep(2000);

        logger.info("User logged in successfully.");
    }

    @Test
    public void testCheckoutProcess() throws InterruptedException {
        logger.info("Starting Checkout Test...");

        logger.fine("Searching for product category: Fish");
        searchPage.enterSearchQuery("Fish");
        searchPage.clickSearch();
        Thread.sleep(2000);

        logger.fine("Selecting product: FI-SW-01 (Large Angelfish)");
        cartPage.clickOnProductById("FI-SW-01");
        cartPage.clickAddToCartOnItemPage();
        Thread.sleep(3000);

        logger.fine("Proceeding to checkout...");
        checkoutPage.proceedToCheckout();
        Thread.sleep(2000);

        logger.fine("Filling shipping details...");
        checkoutPage.fillShippingDetails(
            "John", "Doe", "123 Main St", "New York", "NY", "10001", "USA"
        );

        logger.fine("Confirming order...");
        checkoutPage.confirmOrder();

        logger.info("Test Passed: Checkout completed successfully.");
    }

    @AfterClass
    public static void tearDown() {
        logger.info("Cleaning up and closing browser.");
        if (driver != null) {
            driver.quit();
        }
    }
}