import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class TestClass {
    private static WebDriver driver;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    @DisplayName("Проверка названия")
    public void checkName() {
        driver.get("https://www.mts.by/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement h2Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Онлайн пополнение')]")));

        String actualText = h2Element.getText().replaceAll("\\s+", " ").trim();

        assertEquals("Онлайн пополнение без комиссии", actualText);
    }


    @Test
    @DisplayName("Проверка логотипов")
    public void checkLogo() {
        driver.get("https://www.mts.by/");

        String[] logoXpaths = {
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[1]/img",
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[2]/img",
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[3]/img",
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[4]/img",
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[5]/img"
        };

        for (String xpath : logoXpaths) {
            WebElement logo = driver.findElement(By.xpath(xpath));
            assertTrue(logo.isDisplayed());
        }
    }

    @Test
    @DisplayName("Проверка ссылки")
    public void checkLink() {
        driver.get("https://www.mts.by/");

        String linkXpath = "//a[@href='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']";

        WebElement linkElement = driver.findElement(By.xpath(linkXpath));

        String currentUrl = driver.getCurrentUrl();

        linkElement.click();

        String newUrl = driver.getCurrentUrl();
        assertNotEquals(currentUrl, newUrl);
    }

    @Test
    @DisplayName("Проверка полей формы")
    public void checkForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='input-wrapper input-wrapper_label-left']//input[@class='phone']")));
        phoneField.sendKeys("297777777");

        WebElement sumField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-sum")));
        sumField.sendKeys("100");

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-email")));
        emailField.sendKeys("Example@yandex.ru");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Продолжить')]")));
        submitButton.click();
    }

    @AfterAll
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
