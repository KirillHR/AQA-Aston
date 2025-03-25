import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestClass {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @BeforeAll
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        js = (JavascriptExecutor) driver;
    }

    @BeforeEach
    public void openPage() {
        driver.get("https://www.mts.by/");
    }

    @Test
    @DisplayName("Проверка названия")
    public void checkName() {
        WebElement h2Element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Онлайн пополнение')]")));
        String actualText = h2Element.getText().replaceAll("\\s+", " ").trim();
        assertEquals("Онлайн пополнение без комиссии", actualText);
    }

    @Test
    @DisplayName("Проверка логотипов")
    public void checkLogo() {
        String[] logoXpaths = {
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[1]/img",
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[2]/img",
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[3]/img",
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[4]/img",
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[5]/img"
        };

        for (String xpath : logoXpaths) {
            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            assertTrue(logo.isDisplayed(), "Логотип не отображается: " + xpath);
        }
    }

    @Test
    @DisplayName("Проверка ссылки")
    public void checkLink() {
        String linkXpath = "//a[@href='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']";
        WebElement linkElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(linkXpath)));

        String currentUrl = driver.getCurrentUrl();
        linkElement.click();
        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));

        String newUrl = driver.getCurrentUrl();
        assertNotEquals(currentUrl, newUrl, "URL не изменился после клика по ссылке.");
    }

    @Test
    @DisplayName("Проверка полей формы")
    public void checkForm() {
        js.executeScript("document.getElementById('bxdynamic_pay-form_start').style.display='block';");

        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@class='phone']")));
        js.executeScript("arguments[0].value='297777777'; arguments[0].dispatchEvent(new Event('input'));", phoneField);

        WebElement sumField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-sum")));
        sumField.sendKeys("100");

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-email")));
        emailField.sendKeys("Example@yandex.ru");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Продолжить')]")));
        js.executeScript("arguments[0].click();", submitButton);
    }

    @AfterAll
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
