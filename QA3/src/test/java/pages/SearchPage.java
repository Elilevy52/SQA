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

    // ה-WebElement של הטופס עצמו.
    // ה-action שלו הוא /actions/Catalog.action
    @FindBy(xpath = "//div[@id='Search']//form")
    private WebElement searchForm;

    // עדיין נשאיר את כפתור החיפוש, למקרה שנצטרך אותו או שנמצא דרך אחרת
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

    public void clickSearch() { // שם המתודה נשאר, אך הלוגיקה משתנה
        try {
            // במקום ללחוץ על הכפתור, נשלח את הטופס
            wait.until(ExpectedConditions.visibilityOf(searchForm)); // ודא שהטופס גלוי
            searchForm.submit();
            System.out.println("Search form submitted.");

            // המתנה קצרה לדיבאג, החלף בהמתנה מפורשת לתוצאות
            Thread.sleep(500);

        } catch (Exception e) {
            System.err.println("Error submitting search form: " + e.getMessage());
            throw new RuntimeException("Failed to submit search form", e);
        }
    }
}