import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SecurityTests {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSQLInjectionInLogin() {
        driver.get("http://localhost:4200/login");

        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.tagName("button"));

        // Enter SQL injection payload
        usernameField.sendKeys("admin'--");
        passwordField.sendKeys("anything");
        loginButton.click();

        // Wait briefly for response
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that access is denied or an error is shown
        WebElement errorMessage = driver.findElement(By.cssSelector(".notification.is-danger"));
        assertEquals("Invalid username or password. Please try again.", errorMessage.getText(),
                "SQL injection succeeded, which is a security risk!");
    }

    @Test
    public void testXSSInRegistration() {
        driver.get("http://localhost:4200/register");

        WebElement emailField = driver.findElement(By.name("email"));
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement confirmPasswordField = driver.findElement(By.name("confirmPassword"));
        WebElement registerButton = driver.findElement(By.tagName("button"));

        // Enter XSS payload
        emailField.sendKeys("<script>alert('XSS')</script>");
        usernameField.sendKeys("testUser");
        passwordField.sendKeys("testPassword123");
        confirmPasswordField.sendKeys("testPassword123");
        registerButton.click();

        // Wait briefly for response
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the script is executed
        boolean isAlertPresent = false;
        try {
            driver.switchTo().alert().accept();
            isAlertPresent = true;
        } catch (Exception e) {
            // No alert was triggered
        }

        // Assert that the script is not executed
        assertFalse(isAlertPresent, "XSS payload executed, which is a security risk!");
    }
}
