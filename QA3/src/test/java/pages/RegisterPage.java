package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    private WebDriver driver;

    // User Information
    @FindBy(xpath = "//*[@id='CenterForm']/form/table[1]/tbody/tr[1]/td[2]/input")
    private WebElement usernameField;

    @FindBy(xpath = "//*[@id='CenterForm']/form/table[1]/tbody/tr[2]/td[2]/input")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='CenterForm']/form/table[1]/tbody/tr[3]/td[2]/input")
    private WebElement confirmPasswordField;

    // Account Information
    @FindBy(xpath = "//*[@id='CenterForm']/form/table[2]/tbody/tr[1]/td[2]/input")
    private WebElement firstNameField;

    @FindBy(xpath = "//*[@id='CenterForm']/form/table[2]/tbody/tr[2]/td[2]/input")
    private WebElement lastNameField;

    @FindBy(xpath = "//*[@id='CenterForm']/form/table[2]/tbody/tr[3]/td[2]/input")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='CenterForm']/form/table[2]/tbody/tr[4]/td[2]/input")
    private WebElement phoneField;

    @FindBy(xpath = "//*[@id='CenterForm']/form/table[2]/tbody/tr[5]/td[2]/input")
    private WebElement address1Field;

    @FindBy(xpath = "//*[@id='CenterForm']/form/table[2]/tbody/tr[6]/td[2]/input")
    private WebElement address2Field;

    @FindBy(xpath = "//*[@id='CenterForm']/form/table[2]/tbody/tr[7]/td[2]/input")
    private WebElement cityField;

    @FindBy(xpath = "//*[@id='CenterForm']/form/table[2]/tbody/tr[8]/td[2]/input")
    private WebElement stateField;

    @FindBy(xpath = "//*[@id='CenterForm']/form/table[2]/tbody/tr[9]/td[2]/input")
    private WebElement zipField;

    @FindBy(xpath = "//*[@id='CenterForm']/form/table[2]/tbody/tr[10]/td[2]/input")
    private WebElement countryField;

    // Save Information
    @FindBy(xpath = "//*[@id=\"CenterForm\"]/form/div/button")
    private WebElement saveButton;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillUserInformation(String username, String password, String confirmPassword) {
        usernameField.clear();
        usernameField.sendKeys(username);

        passwordField.clear();
        passwordField.sendKeys(password);

        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void fillAccountInformation(String firstName, String lastName, String email, String phone, String address1, String address2, String city, String state, String zip, String country) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        emailField.clear();
        emailField.sendKeys(email);

        phoneField.clear();
        phoneField.sendKeys(phone);

        address1Field.clear();
        address1Field.sendKeys(address1);

        address2Field.clear();
        address2Field.sendKeys(address2);

        cityField.clear();
        cityField.sendKeys(city);

        stateField.clear();
        stateField.sendKeys(state);

        zipField.clear();
        zipField.sendKeys(zip);

        countryField.clear();
        countryField.sendKeys(country);
    }

    public void saveAccountInformation() {
        saveButton.click();
        System.out.println("Account information saved successfully.");
    }
}
 