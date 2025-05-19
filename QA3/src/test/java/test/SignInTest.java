
package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import pages.SignInPage;

import static org.junit.Assert.assertTrue;

public class SignInTest {
    private WebDriver driver;
    private HomePage homePage;
    private SignInPage signInPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = PageFactory.initElements(driver, HomePage.class);
        signInPage = PageFactory.initElements(driver, SignInPage.class);
        homePage.navigateToHomePage("https://jpetstore.aspectran.com/");
    }

    @Test
    public void testValidLogin() throws InterruptedException {
        System.out.println("Starting Valid Login Test...");
        homePage.clickSignIn();
        signInPage.enterUsername("j2ee");
        signInPage.enterPassword("j2ee");
        signInPage.clickSubmit();

        // המתנה לטעינת הדף מחדש
        Thread.sleep(3000);

        // הדפסה של ה-URL כדי שנראה לאן הגענו
        boolean isLoggedIn = driver.getPageSource().contains("Sign Out");

        // בדיקה האם כתובת ה-URL השתנתה לאזור של המשתמש
        if (isLoggedIn) {
            System.out.println("✅ Test Passed: User successfully logged in.");
        } else {
            System.out.println("❌ Test Failed: User did not log in.");
        }
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
