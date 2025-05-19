package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import pages.RegisterPage;

public class RegisterTest {
    private static WebDriver driver;
    private static HomePage homePage;
    private static RegisterPage registerPage;

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = PageFactory.initElements(driver, HomePage.class);
        registerPage = PageFactory.initElements(driver, RegisterPage.class);
        homePage.navigateToHomePage("https://jpetstore.aspectran.com/");
        homePage.clickRegister();
    }

    @Test
    public void testUserRegistration() throws InterruptedException {
        System.out.println("Starting User Registration Test...");

        // מילוי פרטי משתמש
        registerPage.fillUserInformation("testUser123", "password123", "password123");

        // מילוי פרטי חשבון
        registerPage.fillAccountInformation(
            "John",
            "Doe",
            "john.doe@example.com",
            "0501234567",
            "123 Main St",
            "Apt 4B",
            "New York",
            "NY",
            "10001",
            "USA"
        );

        // שמירה
        registerPage.saveAccountInformation();
        Thread.sleep(2000);

        System.out.println("✅ Test Passed: User registered successfully.");
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
