package pages;

import org.openqa.selenium.By;
// import org.openqa.selenium.JavascriptExecutor; // נשאיר אם נרצה לחזור לזה
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//div[@id='Search']//input[@name='keyword']")
    private WebElement mainSearchInputBox;

    @FindBy(xpath = "//div[@id='Search']//form")
    private WebElement searchForm;

    @FindBy(name = "searchProducts")
    private WebElement searchSubmitButton;


    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public void enterSearchQuery(String query) {
        try {
            wait.until(ExpectedConditions.visibilityOf(mainSearchInputBox));
            mainSearchInputBox.clear();
            mainSearchInputBox.sendKeys(query);
        } catch (Exception e) {
            System.err.println("Error entering search query '" + query + "': " + e.getMessage());
            throw new RuntimeException("Failed to enter search query", e);
        }
    }

    public void clickSearch() {  
        try {
            
            wait.until(ExpectedConditions.visibilityOf(searchForm)); 
            searchForm.submit();
            System.out.println("Search form submitted.");

            Thread.sleep(500);

        } catch (Exception e) {
            System.err.println("Error submitting search form: " + e.getMessage());
            throw new RuntimeException("Failed to submit search form", e);
        }
    }
}
