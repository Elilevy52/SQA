package demo;

import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import java.util.HashMap;
import java.util.Map;

public class PetStore {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // סוגר את הדפדפן לאחר סיום הבדיקה
        }
    }

    @Test
    public void openWebpage() throws InterruptedException {
        driver.get("https://jpetstore.aspectran.com/");
        driver.manage().window().setSize(new Dimension(1004, 724));
        Thread.sleep(2000);
    }

    @Test
    public void SignIn() throws InterruptedException {
        openWebpage(); // קריאה לפונקציה הראשית כדי להימנע מפתיחה כפולה
        driver.findElement(By.xpath("//*[@id=\"Menu\"]/div[1]/a[2]")).click();
        Thread.sleep(2000); 
        driver.findElement(By.xpath("//*[@id=\"Signon\"]/form/div/label[1]/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"Signon\"]/form/div/label[1]/input")).sendKeys("j2ee");
        driver.findElement(By.xpath("//*[@id=\"Signon\"]/form/div/label[2]/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"Signon\"]/form/div/label[2]/input")).sendKeys("j2ee");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"Signon\"]/form/div/div/button")).click();
        Thread.sleep(2000);

        try {
            WebElement errorMsg = driver.findElement(By.xpath("//*[@id=\"Signon\"]/form/div/div[2]"));
            System.out.println("Error message: " + errorMsg.getText());
        } catch (Exception e) {
            System.out.println("No error message found.");
        }
    }

    public static void main(String args[]) {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        org.junit.runner.Result result = junit.run(PetStore.class); // קריאה נכונה למחלקה PetStore
        if (result.getFailureCount() > 0) {
            System.out.println("Test failed.");
            System.exit(1);
        } else {
            System.out.println("Test finished successfully.");
            System.exit(0);
        }
    }
}
