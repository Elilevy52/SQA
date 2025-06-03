package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import pages.SearchPage;
import utils.LoggerUtil;

import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class SearchTest {
    private WebDriver driver;
    private HomePage homePage;
    private SearchPage searchPage;
    private static final Logger logger = LoggerUtil.getLogger(SearchTest.class);

    @Before
    public void setUp() {
        logger.info("Launching Chrome and initializing pages...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = PageFactory.initElements(driver, HomePage.class);
        searchPage = PageFactory.initElements(driver, SearchPage.class);
        homePage.navigateToHomePage("https://jpetstore.aspectran.com/");
    }

    @Test
    public void testProductSearch() throws InterruptedException {
        logger.info("Starting Product Search Test...");

        searchPage.enterSearchQuery("Fish");
        logger.fine("Search query entered: Fish");

        searchPage.clickSearch();
        logger.fine("Search button clicked.");

        Thread.sleep(3000);

        boolean isFound = driver.getPageSource().contains("Fish");
        if (isFound) {
            logger.info("Test Passed: Product found in search results.");
        } else {
            logger.warning("Test Failed: Product not found.");
        }

        assertTrue("Product not found in search results.", isFound);
    }

    @After
    public void tearDown() {
        logger.info("Closing browser...");
        if (driver != null) {
            driver.quit();
        }
    }
}
