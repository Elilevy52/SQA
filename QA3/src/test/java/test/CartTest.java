package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.HomePage;
import utils.LoggerUtil;

import java.time.Duration;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class CartTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static HomePage homePage;
    private static CartPage cartPage;
    private static final Logger logger = LoggerUtil.getLogger(CartTest.class);

    @BeforeClass
    public static void setUp() {
        logger.info("🚀 Launching Chrome and setting up...");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        homePage = PageFactory.initElements(driver, HomePage.class);
        cartPage = PageFactory.initElements(driver, CartPage.class);
        homePage.navigateToHomePage("https://jpetstore.aspectran.com/");
        logger.info("✅ Setup complete.");
    }

    private void addProductToCartFlow(String category, String productId, String expectedProductNameInCart) {
        homePage.clickCategory(category);

        logger.fine("Opening product: " + productId);
        cartPage.clickOnProductById(productId);

        logger.fine("Clicking Add to Cart for: " + expectedProductNameInCart);
        cartPage.clickAddToCartOnItemPage();

        logger.fine("Checking if product is in cart: " + expectedProductNameInCart);
        boolean inCart = cartPage.isProductInCart(expectedProductNameInCart);
        logger.fine("In cart? " + inCart);

        assertTrue("Product '" + expectedProductNameInCart + "' should be in cart after adding.", inCart);
    }

    @Test
    public void testAddToCartAndVerifyQuantity() {
        String productName = "Large Angelfish";
        String category = "Fish";
        String productId = "FI-SW-01";

        logger.info("Running test: testAddToCartAndVerifyQuantity");
        addProductToCartFlow(category, productId, productName);

        assertEquals("Quantity for '" + productName + "' should be 1.",
                1, cartPage.getProductQuantityInCart(productName));

        cartPage.updateProductQuantityInCart(productName, 2);
        assertEquals("Quantity for '" + productName + "' should be 2 after update.",
                2, cartPage.getProductQuantityInCart(productName));

        cartPage.removeProductByNameFromCart(productName);
        assertTrue("Cart should be empty after cleanup.", cartPage.isCartEmpty());
    }

    @Test
    public void testRemoveLastItemMakesCartEmpty() {
        String productName = "Spotted Koi";
        String category = "Fish";
        String productId = "FI-FW-01";

        logger.info("Running test: testRemoveLastItemMakesCartEmpty");
        addProductToCartFlow(category, productId, productName);
        assertEquals("Cart should have 1 item before removal.",
                1, cartPage.getProductCountInCart());

        cartPage.removeProductByNameFromCart(productName);

        assertFalse("Product '" + productName + "' should have been removed.",
                cartPage.isProductInCart(productName));
        assertTrue("Cart should be empty after removing the last item.",
                cartPage.isCartEmpty());
        assertEquals("Cart should have 0 items after removal.",
                0, cartPage.getProductCountInCart());
    }

    @Test
    public void testRemoveOneItemWhenMultipleInCart() {
        String productToKeep = "Amazon Parrot";
        String productToRemove = "Adult Male Finch";
        String parrotProductId = "AV-CB-01";
        String finchProductId = "AV-SB-02";

        logger.info("Running test: testRemoveOneItemWhenMultipleInCart");
        addProductToCartFlow("Birds", parrotProductId, productToKeep);
        addProductToCartFlow("Birds", finchProductId, productToRemove);

        assertTrue("Product '" + productToKeep + "' should be in cart.",
                cartPage.isProductInCart(productToKeep));
        assertTrue("Product '" + productToRemove + "' should be in cart.",
                cartPage.isProductInCart(productToRemove));
        assertEquals("Cart should have 2 items before removal.",
                2, cartPage.getProductCountInCart());

        cartPage.removeProductByNameFromCart(productToRemove);

        assertFalse("Product '" + productToRemove + "' should have been removed.",
                cartPage.isProductInCart(productToRemove));
        assertTrue("Product '" + productToKeep + "' should still be in cart.",
                cartPage.isProductInCart(productToKeep));
        assertEquals("Cart should have 1 item after removal.",
                1, cartPage.getProductCountInCart());
        assertFalse("Cart should not be empty.", cartPage.isCartEmpty());

        cartPage.removeProductByNameFromCart(productToKeep);
        assertTrue("Cart should be empty after final cleanup.", cartPage.isCartEmpty());
    }

    @AfterClass
    public static void tearDown() {
        logger.info("Closing browser...");
        if (driver != null) {
            driver.quit();
        }
    }
}
