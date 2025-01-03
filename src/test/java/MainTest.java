import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run Chrome in headless mode
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
        // Locate the input fields and login button
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.tagName("button"));

        // Enter valid credentials
        usernameField.sendKeys("tassano");
        passwordField.sendKeys("tassano123");
        loginButton.click();

        // Wait for redirection to complete (use an implicit wait or Thread.sleep for simplicity)
        try {
            Thread.sleep(3000); // Adjust based on actual redirection duration
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Assert that the current URL is the home page
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:4200/home", currentUrl, "Redirection to home page failed.");
    }


    @Test
    public void testInvalidLogin() {
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.tagName("button"));

        usernameField.sendKeys("aze");
        passwordField.sendKeys("azeaze");
        loginButton.click();

        // Wait for the error message to appear
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".notification.is-danger")));

        // Assert the error message text
        assertEquals("Invalid username or password. Please try again.", errorMessage.getText());
    }

    @Test
    public void testEmptyUsername() {
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.tagName("button"));

        usernameField.sendKeys("");
        passwordField.sendKeys("password");
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector(".notification.is-danger"));
        assertEquals("Username and password are required.", errorMessage.getText());
    }

    @Test
    public void testEmptyPassword() {
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.tagName("button"));

        usernameField.sendKeys("validUsername");
        passwordField.sendKeys("");
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector(".notification.is-danger"));
        assertEquals("Username and password are required.", errorMessage.getText());
    }

    @Test
    public void testBothFieldsEmpty() {
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.tagName("button"));

        usernameField.sendKeys("");
        passwordField.sendKeys("");
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector(".notification.is-danger"));
        assertEquals("Username and password are required.", errorMessage.getText());
    }

    @Test
    public void testRegisterWithValidDetails() {
        // Initialize WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        // Wait for fields to be visible
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement confirmPasswordField = driver.findElement(By.name("confirmPassword"));
        WebElement registerButton = driver.findElement(By.tagName("button"));

        // Enter valid registration details
        emailField.sendKeys("neveruser@gmail.com");
        usernameField.sendKeys("neverused");
        passwordField.sendKeys("neverused123");
        confirmPasswordField.sendKeys("neverused123");
        registerButton.click();

        // Wait for redirection to login page
        wait.until(ExpectedConditions.urlToBe("http://localhost:4200/login"));

        // Assert that the current URL is the login page
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:4200/login", currentUrl, "Redirection to login page failed.");
    }


}