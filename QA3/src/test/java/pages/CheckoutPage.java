package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static SignInPage signInPage;
    
    @FindBy(xpath = "//*[@id=\"Cart\"]/a")
    private WebElement proceedToCheckoutButton;

    @FindBy(xpath = "//*[@id=\"CenterForm\"]/form/table[2]/tbody/tr[1]/td[2]/input")
    private WebElement firstNameField;

    @FindBy(xpath = "//*[@id=\"CenterForm\"]/form/table[2]/tbody/tr[2]/td[2]/input")
    private WebElement lastNameField;

    @FindBy(xpath = "//*[@id=\"CenterForm\"]/form/table[2]/tbody/tr[3]/td[2]/input")
    private WebElement addressField;

    @FindBy(xpath = "//*[@id=\"CenterForm\"]/form/table[2]/tbody/tr[5]/td[2]/input")
    private WebElement cityField;

    @FindBy(xpath = "//*[@id=\"CenterForm\"]/form/table[2]/tbody/tr[6]/td[2]/input")
    private WebElement stateField;

    @FindBy(xpath = "//*[@id=\"CenterForm\"]/form/table[2]/tbody/tr[7]/td[2]/input")
    private WebElement zipField;

    @FindBy(xpath = "//*[@id=\"CenterForm\"]/form/table[2]/tbody/tr[8]/td[2]/input")
    private WebElement countryField;

    @FindBy(xpath = "//*[@id=\"CenterForm\"]/form/div/button[1]")
    private WebElement confirmButton;
    
    @FindBy(xpath = "//*[@id=\"CenterForm\"]/form/div/button[1]")
    private WebElement continueButton;
    
    @FindBy(xpath = "//*[@id=\"Signon\"]/form/div/div/button")
    private WebElement loginButton;
    
    @FindBy(xpath = "//*[@id='Signon']/form/div/label[1]/input")
    private WebElement usernameField;

    @FindBy(xpath = "//*[@id='Signon']/form/div/label[2]/input")
    private WebElement passwordField;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    public void proceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
        proceedToCheckoutButton.click();
        System.out.println("✅ Proceed to Checkout clicked.");
    }
    public void loginBeforeCheckout() {
    	signInPage.enterUsername("j2ee");
        signInPage.enterPassword("j2ee");
    	wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    	loginButton.click();
    }
    public void fillShippingDetails(String firstName, String lastName, String address, String city, String state, String zip, String country) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        addressField.clear();
        addressField.sendKeys(address);

        cityField.clear();
        cityField.sendKeys(city);

        stateField.clear();
        stateField.sendKeys(state);

        zipField.clear();
        zipField.sendKeys(zip);

        countryField.clear();
        countryField.sendKeys(country);
        
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
        System.out.println("✅ ContiueButton Clicked.");
        
        System.out.println("✅ Shipping details filled.");
    }

    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmButton.click();
        System.out.println("✅ Order confirmed.");
    }
}
