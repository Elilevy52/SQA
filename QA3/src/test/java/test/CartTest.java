package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.CartPage;
import pages.HomePage;
import pages.SearchPage;

import static org.junit.Assert.assertTrue;

public class CartTest {
    private static WebDriver driver;
    private static HomePage homePage;
    private static SearchPage searchPage;
    private static CartPage cartPage;

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = PageFactory.initElements(driver, HomePage.class);
        searchPage = PageFactory.initElements(driver, SearchPage.class);
        cartPage = PageFactory.initElements(driver, CartPage.class);
        homePage.navigateToHomePage("https://jpetstore.aspectran.com/");
    }

    @Test
    public void testAddToCartAndQuantity() throws InterruptedException {
        System.out.println("Starting Add to Cart and Quantity Test...");

        // חיפוש מוצר כללי יותר
        searchPage.enterSearchQuery("Fish");
        searchPage.clickSearch();
        Thread.sleep(2000);

        // בחירת המוצר הראשון
        cartPage.clickOnFirstProduct();
        Thread.sleep(2000);

        // לחיצה על "Add to Cart"
        cartPage.clickAddToCart();
        Thread.sleep(3000);

        // בדיקה האם המוצר בעגלה
        boolean isInCart = cartPage.isProductInCart("Large Angelfish");
        assertTrue("Product was not added to the cart!", isInCart);

        // עדכון כמות ל-2 ובדיקה
        cartPage.updateProductQuantity(2);
        Thread.sleep(2000);
        int quantity = cartPage.getProductQuantity();
        assertTrue("Product quantity is incorrect!", quantity == 2);
        System.out.println("✅ Test Passed: Quantity updated successfully.");
    }


    @Test
    public void testRemoveFromCart() throws InterruptedException {
        System.out.println("Starting Remove from Cart Test...");

        // חיפוש מוצר
        searchPage.enterSearchQuery("Fish");
        searchPage.clickSearch();
        Thread.sleep(2000);

        // בחירת מוצר
        cartPage.clickOnFirstProduct();
        Thread.sleep(2000);

        // לחיצה על "Add to Cart"
        cartPage.clickAddToCart();
        Thread.sleep(3000);

        // הסרה של המוצר
        cartPage.removeProductFromCart();
        System.out.println("✅ Test Passed: Product removed successfully.");
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
