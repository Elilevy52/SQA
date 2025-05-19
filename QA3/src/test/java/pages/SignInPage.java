
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id='Signon']/form/div/label[1]/input")
    private WebElement usernameField;

    @FindBy(xpath = "//*[@id='Signon']/form/div/label[2]/input")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='Signon']/form/div/div/button")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@id='Signon']/form/div/div[2]")
    private WebElement errorMessage;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
