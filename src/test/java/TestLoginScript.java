import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.time.Duration;

public class TestLoginScript {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("http://shop.pragmatic.bg/admin");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void loginWithAdminUser() {
        WebElement usernameAdminInput = driver.findElement(By.name("username"));
        usernameAdminInput.sendKeys("admin ");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("parola123!");

        WebElement loginButton = driver.findElement(By.cssSelector("button[type=\"submit\"].btn.btn-primary"));
        loginButton.click();

        // Successful login
        WebElement dashboardTitle = driver.findElement(By.xpath("//h1[text()=\"Dashboard\"]"));
        Assert.assertEquals(dashboardTitle.getText(), "Dashboard");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
