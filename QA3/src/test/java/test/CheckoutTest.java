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

import static org.junit.Assert.assertTrue;

public class CheckoutTest {
    private static WebDriver driver;
    private static HomePage homePage;
    private static SearchPage searchPage;
    private static CartPage cartPage;
    private static CheckoutPage checkoutPage;
    private static SignInPage signInPage;

    @BeforeClass
    public static void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // אתחול של הדפים
        homePage = PageFactory.initElements(driver, HomePage.class);
        searchPage = PageFactory.initElements(driver, SearchPage.class);
        cartPage = PageFactory.initElements(driver, CartPage.class);
        checkoutPage = PageFactory.initElements(driver, CheckoutPage.class);
        signInPage = PageFactory.initElements(driver, SignInPage.class);

        // מעבר לדף הבית
        homePage.navigateToHomePage("https://jpetstore.aspectran.com/");
        
        // התחברות מסודרת
        homePage.clickSignIn();
        Thread.sleep(2000);
        signInPage.enterUsername("j2ee");
        signInPage.enterPassword("j2ee");
        signInPage.clickSubmit();
        Thread.sleep(2000);

        System.out.println("✅ User logged in successfully.");
    }

    @Test
    public void testCheckoutProcess() throws InterruptedException {
        System.out.println("Starting Checkout Test...");

        // חיפוש מוצר
        searchPage.enterSearchQuery("Fish");
        searchPage.clickSearch();
        Thread.sleep(2000);

        // בחירת המוצר הראשון
        cartPage.clickOnFirstProduct();
        Thread.sleep(2000);

        // לחיצה על "Add to Cart"
        cartPage.clickAddToCart();
        Thread.sleep(3000);

        // לחיצה על "Proceed to Checkout"
        checkoutPage.proceedToCheckout();
        Thread.sleep(2000);
        
        // מילוי פרטי המשלוח
        checkoutPage.fillShippingDetails(
            "John",
            "Doe",
            "123 Main St",
            "New York",
            "NY",
            "10001",
            "USA"
        );

        // אישור הזמנה
        checkoutPage.confirmOrder();
        System.out.println("✅ Test Passed: Checkout completed successfully.");
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
