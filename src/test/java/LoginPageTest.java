import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode
        driver = new ChromeDriver(options);
        driver.get("http://localhost:4200/login");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testValidLogin() {
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.tagName("button"));

        usernameField.sendKeys("assamo");
        passwordField.sendKeys("assamo123");
        loginButton.click();

        // Wait for redirection to home page (e.g., using a Thread.sleep or WebDriverWait)
        try {
            Thread.sleep(3000); // Adjust based on your application speed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify the current URL is the home page
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:4200/home", currentUrl, "Redirection to home page failed.");
    }


    @Test
    public void testEmptyFields() {
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.tagName("button"));

        usernameField.clear();
        passwordField.clear();
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector(".notification.is-danger"));
        assertEquals("Username and password are required.", errorMessage.getText());
    }

    @Test
    public void testPasswordVisibilityToggle() {
        // Locate the password input field and the toggle button (eye icon)
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement toggleButton = driver.findElement(By.id("togglePassword"));

        // Verify the password is initially hidden
        String initialType = passwordField.getAttribute("type");
        assertEquals("password", initialType, "Password should initially be hidden.");

        // Click the toggle button to make the password visible
        toggleButton.click();

        // Wait briefly to ensure the UI updates
        try {
            Thread.sleep(500); // Adjust the time as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify the password is now visible
        String visibleType = passwordField.getAttribute("type");
        assertEquals("text", visibleType, "Password should be visible after toggling.");

        // Click the toggle button again to hide the password
        toggleButton.click();

        // Wait briefly to ensure the UI updates
        try {
            Thread.sleep(500); // Adjust the time as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify the password is hidden again
        String hiddenTypeAgain = passwordField.getAttribute("type");
        assertEquals("password", hiddenTypeAgain, "Password should be hidden after toggling again.");
    }

}
