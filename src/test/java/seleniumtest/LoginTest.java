package seleniumtest;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {

        // Set up ChromeDriver with headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run Chrome in headless mode
        options.addArguments("--disable-gpu"); // Disables GPU hardware acceleration, useful for headless mode
        options.addArguments("--no-sandbox"); // Disables sandbox security, required for certain Linux distributions
        options.addArguments("--disable-dev-shm-usage"); // avoids using shared memeory, overcomes limited resource problems
        driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @Test
    public void testValidLogin() {
        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("submit")).click();

        // Assertion for successful login
        WebElement loginMessage = driver.findElement(By.className("post-title"));
        Assert.assertTrue(loginMessage.isDisplayed(), "Login message is displayed");

        String capturedLoginMessage = loginMessage.getText();
        String expectedLoginMessage = "Logged In Successfully";
        Assert.assertEquals(expectedLoginMessage, capturedLoginMessage,
                "Expected login message matches captured login message");

        System.out.println("Positive test case passed - Should login successfully with valid credentials");
    }

    @Test
    public void testInvalidLogin() {
        driver.findElement(By.id("username")).sendKeys("student1");
        driver.findElement(By.id("password")).sendKeys("invalidPassword");
        driver.findElement(By.id("submit")).click();

        // Assertion for error message with explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='error']")));

        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is displayed");

        String capturedErrorMessage = errorMessage.getText();
        String expectedErrorMessage = "Your username is invalid!";
        Assert.assertEquals(expectedErrorMessage, capturedErrorMessage,
                "Expected error message matches captured error message");

        System.out.println("Negative test case passed - Should display an error message with invalid credentials");
    }

    @AfterMethod
    public void endTest() {
        driver.quit();
    }
}
