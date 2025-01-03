package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();


        try {
            // Navigate to Charika website
            driver.get("https://www.charika.ma/");

            // Locate and interact with the search fields
            WebElement sectorField = driver.findElement(By.id("secteur"));
            WebElement regionField = driver.findElement(By.id("region"));
            WebElement searchButton = driver.findElement(By.id("btnSearch"));

            // Enter search criteria
            sectorField.sendKeys("génie civil");
            regionField.sendKeys("Casablanca");
            searchButton.click();

            // Wait for the results to load

            // Extract and display the results
            List<WebElement> results = driver.findElements(By.className("result-item"));
            for (WebElement result : results) {
                String companyName = result.findElement(By.className("company-name")).getText();
                String companyAddress = result.findElement(By.className("company-address")).getText();
                System.out.println("Company: " + companyName + ", Address: " + companyAddress);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
