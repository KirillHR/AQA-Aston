import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestClass {
    private WebDriver driver;
    private TestPage testPage;

    @RegisterExtension
    ScreenshotOnFailure screenshotOnFailure = new ScreenshotOnFailure(driver);

    @BeforeAll
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        testPage = new TestPage(driver);
        // Важно: теперь ScreenshotOnFailure будет использовать этот экземпляр driver
        screenshotOnFailure = new ScreenshotOnFailure(driver);
    }

    @BeforeEach
    public void openPage() {
        driver.get("https://www.mts.by/");
        testPage.acceptCookies();
    }

    @Test
    @AllureId("001")
    @Story("Проверка заголовка страницы")
    @Description("Этот тест проверяет, что заголовок страницы правильный")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка названия")
    public void checkName() {
        assertEquals("Онлайн пополнение без комиссии", testPage.getPageTitle());
    }

    @Test
    @AllureId("002")
    @Story("Проверка логотипов")
    @Description("Этот тест проверяет, что все логотипы на странице отображаются корректно")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка логотипов")
    public void checkLogo() {
        testPage.checkLogos();
    }

    @Test
    @AllureId("003")
    @Story("Проверка ссылки")
    @Description("Этот тест проверяет, что ссылка на странице работает корректно")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Проверка ссылки")
    public void checkLink() {
        testPage.checkLink();
    }

    @Test
    @AllureId("004")
    @Story("Проверка полей формы")
    @Description("Этот тест проверяет, что все поля формы работают корректно")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка полей формы")
    public void checkForm() {
        testPage.fillForm("297777777", "100", "Example@yandex.ru");
    }

    @Test
    @AllureId("005")
    @Story("Проверка плейсхолдеров в разных формах")
    @Description("Этот тест проверяет правильность отображения плейсхолдеров в формах на странице")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка плейсхолдеров в разных формах")
    public void checkPlaceholdersInDifferentPayments() {
        testPage.checkPlaceholders();
    }

    @Test
    @AllureId("006")
    @Story("Проверка отображения элементов окна оплаты")
    @Description("Этот тест проверяет, что элементы окна оплаты отображаются корректно")
    @Severity(SeverityLevel.CRITICAL)
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