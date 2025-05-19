package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(linkText = "FI-SW-01")
    private WebElement productLink;

    @FindBy(xpath = "//a[text()='Add to Cart']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//*[@id=\"Menu\"]/div[1]/a[1]/img")
    private WebElement cartTable;

    @FindBy(xpath = "//*[@id='Cart']/form/table/tbody/tr[2]/td[2]")
    private WebElement productName;

    @FindBy(xpath = "//*[@id=\"Cart\"]/form/table/tbody/tr[2]/td[5]/input")
    private WebElement productQuantityInput;

    @FindBy(xpath = "//*[@id='Cart']/form/table/tbody/tr[2]/td[8]/a")
    private WebElement removeButton;
    
    @FindBy(xpath = "//*[@id=\"Cart\"]/form/table/tbody/tr[3]/td[2]/button")
    private WebElement updateButton;
    
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    public void clickOnFirstProduct() {
        System.out.println("🔎 [DEBUG] Clicking on the first product link...");
        List<WebElement> products = driver.findElements(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[2]/td[1]/strong/a"));
        if (products.size() > 0) {
            wait.until(ExpectedConditions.elementToBeClickable(products.get(0)));
            products.get(0).click();
            System.out.println("✅ [DEBUG] First product link clicked.");
        } else {
            System.out.println("❌ [ERROR] No products found to click.");
        }
    }
    public void clickOnProduct(String productId) {
        System.out.println("🔎 [DEBUG] Clicking on product link...");
        WebElement productLink = driver.findElement(By.xpath("//a[text()='" + productId + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(productLink));
        productLink.click();
        System.out.println("✅ [DEBUG] Product link clicked.");
    }

    public void clickAddToCart() {
        System.out.println("🔎 [DEBUG] Trying to click 'Add to Cart'");
        
        try {
            WebElement addToCartButton = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[2]/td[5]/a"));
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
            addToCartButton.click();
            System.out.println("✅ Product added to cart.");
        } catch (Exception e) {
            System.out.println("❌ [ERROR] Could not click 'Add to Cart': " + e.getMessage());
        }
    }

    public boolean isProductInCart(String expectedProductName) {
        try {
        	wait.until(ExpectedConditions.elementToBeClickable(cartTable));
        	cartTable.click();
            WebElement productName = driver.findElement(By.xpath("//*[@id=\"Cart\"]/form/table/tbody/tr[2]/td[2]"));
            System.out.println("🔎 Found in cart: " + productName.getText());
            return productName.isDisplayed();
        } catch (Exception e) {
            System.out.println("❌ [ERROR] Product not found in cart.");
            return false;
        }
    }

    public int getProductQuantity() {
        wait.until(ExpectedConditions.visibilityOf(productQuantityInput));
        return Integer.parseInt(productQuantityInput.getAttribute("value"));
    }

    public void updateProductQuantity(int quantity) {
        wait.until(ExpectedConditions.visibilityOf(productQuantityInput));
        productQuantityInput.clear();
        productQuantityInput.sendKeys(String.valueOf(quantity));
        updateButton.click();
        System.out.println("🔄 Quantity updated to: " + getProductQuantity());
    }

    public void removeProductFromCart() {
        try {
        	wait.until(ExpectedConditions.elementToBeClickable(cartTable));
        	cartTable.click();
            WebElement removeButton = driver.findElement(By.xpath("//a[text()='Remove']"));
            wait.until(ExpectedConditions.elementToBeClickable(removeButton));
            removeButton.click();
            updateButton.click();
            System.out.println("❌ Product removed from cart.");
        } catch (Exception e) {
            System.out.println("❌ [ERROR] Could not remove product from cart: " + e.getMessage());
        }
    }
}
