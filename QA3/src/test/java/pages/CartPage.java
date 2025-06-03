package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[contains(text(),'Add to Cart')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//*[@id='Menu']/div[1]/a[1]/img")
    private WebElement cartIconMenuLink;

    @FindBy(xpath = "//*[@id='Cart']/form")
    private WebElement cartFormContainer;

    @FindBy(xpath = "//*[@id=\"Cart\"]/form/table/tbody/tr[3]/td[2]/button")
    private WebElement updateCartButton;

    @FindBy(xpath = "//input[@type='text' and contains(@name,'quantity')]")
    private List<WebElement> quantityInputs;

    @FindBy(xpath = "//table//tr")
    private List<WebElement> cartRows;

    @FindBy(xpath = "//*[@id='Cart']//*[contains(text(),'Your cart is empty.')]")
    private WebElement emptyCartMessage;

    @FindBy(xpath = "//*[@id='Cart']//table/tbody/tr[td/a[text()='Remove']]")
    private List<WebElement> cartItemRows;

    @FindBy(xpath = "//*[@id='Cart']//input[@type='number']")
    private List<WebElement> quantityFields;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // Helper method to dynamically find product link by its ID
    private WebElement getProductLinkById(String productId) {
        String xpath = "//a[normalize-space()='" + productId + "']";
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    // Helper method to dynamically find cart row by product name
    private WebElement getCartRowByProductName(String productName) {
        for (WebElement row : cartRows) {
            if (row.getText().toLowerCase().contains(productName.toLowerCase())) {
                return row;
            }
        }
        return null;
    }

    public void clickOnProductById(String productId) {
        getProductLinkById(productId).click();
    }

    public void clickAddToCartOnItemPage() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    private void navigateToCartPage() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIconMenuLink)).click();
        wait.until(ExpectedConditions.visibilityOf(cartFormContainer));
    }

    public boolean isProductInCart(String productName) {
        try {
            return wait.until(driver -> cartRows.stream()
                    .anyMatch(row -> row.getText().toLowerCase().contains(productName.toLowerCase())));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public int getProductQuantityInCart(String productName) {
        navigateToCartPage();
        WebElement row = getCartRowByProductName(productName);
        if (row != null) {
            try {
                WebElement quantityField = row.findElement(By.xpath(".//input[@type='number']"));
                return Integer.parseInt(quantityField.getAttribute("value"));
            } catch (Exception e) {
                System.err.println("Error getting quantity for '" + productName + "': " + e.getMessage());
            }
        }
        return 0;
    }

    public void updateProductQuantityInCart(String productName, int quantity) {
        navigateToCartPage();
        WebElement row = getCartRowByProductName(productName);
        if (row != null) {
            try {
                WebElement quantityField = row.findElement(By.xpath(".//input[@type='number']"));
                quantityField.clear();
                quantityField.sendKeys(String.valueOf(quantity));
                wait.until(ExpectedConditions.elementToBeClickable(updateCartButton)).click();
                return;
            } catch (Exception e) {
                throw new RuntimeException("Error updating quantity for '" + productName + "': " + e.getMessage());
            }
        }
        throw new RuntimeException("Product '" + productName + "' not found in cart.");
    }

    public void removeProductByNameFromCart(String productNameToRemove) {
        navigateToCartPage();
        WebElement row = getCartRowByProductName(productNameToRemove);
        if (row != null) {
            try {
                WebElement removeLink = row.findElement(By.xpath(".//a[contains(text(),'Remove')]"));
                wait.until(ExpectedConditions.elementToBeClickable(removeLink)).click();
                wait.until(ExpectedConditions.stalenessOf(row));
                return;
            } catch (Exception e) {
                throw new RuntimeException("Error removing product '" + productNameToRemove + "': " + e.getMessage());
            }
        }
        System.err.println("Product '" + productNameToRemove + "' not found in cart.");
    }

    public boolean isCartEmpty() {
        navigateToCartPage();
        try {
            return emptyCartMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public int getProductCountInCart() {
        navigateToCartPage();
        try {
            return cartItemRows.size();
        } catch (Exception e) {
            System.err.println("Error getting product count from cart: " + e.getMessage());
            return -1;
        }
    }
}
