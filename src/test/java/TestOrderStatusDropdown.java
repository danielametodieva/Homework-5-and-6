import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;
import static org.testng.Assert.*;

import java.time.Duration;

public class TestOrderStatusDropdown {
    private WebDriver driver;
    private List<WebElement> orderStatusOptions;
    private List<String> expectedOptions = Arrays.asList(
        "Missing Orders",
        "Canceled",
        "Canceled Reversal",
        "Chargeback",
        "Complete",
        "Denied",
        "Expired",
        "Failed",
        "Pending",
        "Processed",
        "Processing",
        "Refunded",
        "Reversed",
        "Shipped",
        "Voided"
    );

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("http://shop.pragmatic.bg/admin");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private void login() {
        WebElement usernameAdminInput = driver.findElement(By.name("username"));
        usernameAdminInput.sendKeys("admin1 ");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("parola123!");

        WebElement loginButton = driver.findElement(By.cssSelector("button[type=\"submit\"].btn.btn-primary"));
        loginButton.click();
    }

    private void goToOrdersPage() {
        WebElement salesMenu = driver.findElement(By.cssSelector("#menu-sale a"));
        salesMenu.click();
        WebElement ordersMenuItem = driver.findElement(By.cssSelector("#menu-sale > ul > li:first-child a"));
        ordersMenuItem.click();
    }

    private void validateOrderStatusOptionByIndex(Integer index) {
        assertEquals(this.expectedOptions.get(index - 1), this.orderStatusOptions.get(index).getText());
    }

    @Test
    public void testOrdersDropdown() {
        this.login();
        this.goToOrdersPage();

        WebElement orderStatusElement = driver.findElement(By.cssSelector("#input-order-status"));
        Select orderStatusSelect = new Select(orderStatusElement);
        this.orderStatusOptions = orderStatusSelect.getOptions();

        // Assert the options
        assertEquals(this.expectedOptions.size() + 1, orderStatusOptions.size());
        for (int i = 1; i < orderStatusOptions.size(); i++) {
            this.validateOrderStatusOptionByIndex(i);
        }
    }

    @AfterMethod
    public void tearDown() { driver.quit(); }
}
