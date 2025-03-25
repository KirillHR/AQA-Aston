import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestClass {
    private WebDriver driver;
    private TestPage testPage;
    private Actions actions;

    @BeforeAll
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        actions = new Actions(driver);
        testPage = new TestPage(driver);
    }

    @BeforeEach
    public void openPage() {
        driver.get("https://www.mts.by/");
        testPage.acceptCookies();
    }

    @Test
    @DisplayName("Проверка названия")
    public void checkName() {
        assertEquals("Онлайн пополнение без комиссии", testPage.getPageTitle());
    }

    @Test
    @DisplayName("Проверка логотипов")
    public void checkLogo() {
        testPage.checkLogos();
    }

    @Test
    @DisplayName("Проверка ссылки")
    public void checkLink() {
        testPage.checkLink();
    }

    @Test
    @DisplayName("Проверка полей формы")
    public void checkForm() {
        testPage.fillForm("297777777", "100", "Example@yandex.ru");
    }

    @Test
    @DisplayName("Проверка плейсхолдеров в разных формах")
    public void checkPlaceholdersInDifferentPayments() {
        testPage.checkPlaceholders();
    }

    @Test
    @DisplayName("Проверка отображения элементов окна оплаты")
    public void checkPaymentDisplay() {
        testPage.checkPaymentDisplay("297777777");
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
