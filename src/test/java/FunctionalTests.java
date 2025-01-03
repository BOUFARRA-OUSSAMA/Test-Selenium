import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class FunctionalTests {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLoginAndLibrarySearch() {
        // Navigate to the login page
        driver.get("http://localhost:4200/login");

        // Perform login
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.tagName("button"));

        usernameField.sendKeys("assamo");
        passwordField.sendKeys("assamo123");
        loginButton.click();

        // Wait for redirection to home page
        try {
            Thread.sleep(3000); // Adjust this based on application behavior
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click on the Library link in the navbar
        WebElement libraryLink = driver.findElement(By.linkText("Library"));
        libraryLink.click();

        // Wait for the library page to load
        try {
            Thread.sleep(2000); // Adjust this based on application behavior
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Locate the search bar and enter a query
        WebElement searchBar = driver.findElement(By.cssSelector(".search-bar-container input"));
        searchBar.sendKeys("A Cat for Santa");

        // Wait briefly for results to populate dynamically
        try {
            Thread.sleep(2000); // Adjust based on actual search delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Simulate pressing Enter key in the search bar
        searchBar.sendKeys(Keys.RETURN);

        // Allow some time for final results to load
        try {
            Thread.sleep(3000); // Adjust this based on actual application behavior
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Locate book cards in the result
        List<WebElement> bookCards = driver.findElements(By.cssSelector(".books-grid .book-card"));

        // Assert that at least one book card is displayed
        assertTrue(bookCards.size() > 0, "No book cards found for the search query.");
    }
}
