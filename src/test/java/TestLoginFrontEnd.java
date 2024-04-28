import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class TestLoginFrontEnd {

    private WebDriver driver;
    private String searchTerm = "iPhone";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("http://shop.pragmatic.bg");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testSearchTermUrl() {
        Actions builder = new Actions(driver);

        WebElement searchInput = driver.findElement(By.name("search"));

        builder.click(searchInput)
               .sendKeys(this.searchTerm)
               .sendKeys(Keys.ENTER)
               .perform();


        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://shop.pragmatic.bg/index.php?route=product/search&search=" + this.searchTerm;

        // Assert if the current URL matches the expected URL
        assertEquals(currentUrl, expectedUrl, "URL doesn't match the expected value");
    }

    @AfterMethod
    public void tearDown() { driver.quit(); }
}
