package pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id='Menu']/div[1]/a[2]")
    private WebElement signInButton;

    @FindBy(xpath = "//*[@id='Menu']/div[1]/a[3]")
    private WebElement registerButton;

    @FindBy(xpath = "//*[@id='Menu']/div[2]/a[1]")
    private WebElement cartButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage(String url) {
        driver.get(url);
    }

    public void clickSignIn() {
        signInButton.click();
    }
    
    public void clickCategory(String categoryName) {
        try {
            driver.findElement(By.linkText(categoryName)).click();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Category '" + categoryName + "' not found on the home page.");
        }
    }

    public void clickRegister() {
        registerButton.click();
    }

    public void clickCart() {
        cartButton.click();
    }
}
