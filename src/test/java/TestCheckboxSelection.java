import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class TestCheckboxSelection {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("http://pragmatic.bg/automation/lecture13/Config.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testCheckboxSelection() {
        Actions builder = new Actions(driver);

        WebElement airbags = driver.findElement(By.name("airbags"));
        WebElement parksensor = driver.findElement(By.name("parksensor"));

        Action checkAirbags = builder.click(airbags).build();
        Action checkParksensor = builder.click(parksensor).build();

        checkAirbags.perform();
        checkParksensor.perform();

        assertTrue(airbags.isSelected());
        assertTrue(parksensor.isSelected());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
