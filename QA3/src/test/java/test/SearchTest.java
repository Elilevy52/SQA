package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import pages.SearchPage;

import static org.junit.Assert.assertTrue;

public class SearchTest {
    private WebDriver driver;
    private HomePage homePage;
    private SearchPage searchPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = PageFactory.initElements(driver, HomePage.class);
        searchPage = PageFactory.initElements(driver, SearchPage.class);
        homePage.navigateToHomePage("https://jpetstore.aspectran.com/");
    }

    @Test
    public void testProductSearch() throws InterruptedException {
        System.out.println("Starting Product Search Test...");
        
        // הכנסת ערך לחיפוש
        searchPage.enterSearchQuery("Fish");
        System.out.println("Search query entered: Fish");
        
        // לחיצה על כפתור חיפוש
        searchPage.clickSearch();
        System.out.println("Search button clicked.");

        // המתנה לטעינת התוצאות
        Thread.sleep(3000);
        
        // בדיקה אם המוצר נמצא בעמוד
        boolean isFound = driver.getPageSource().contains("Fish");
        if (isFound) {
            System.out.println("✅ Test Passed: Product found in search results.");
        } else {
            System.out.println("❌ Test Failed: Product not found.");
        }

        // וידוא שהתוצאה מופיעה
        assertTrue("Product not found in search results.", isFound);
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
