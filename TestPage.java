import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TestPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public TestPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.js = (JavascriptExecutor) driver;
    }

    public void acceptCookies() {
        try {
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-close]")));
            cookieButton.click();
        } catch (Exception e) {
        }
    }

    public String getPageTitle() {
        WebElement h2Element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Онлайн пополнение')]")));
        return h2Element.getText().replaceAll("\\s+", " ").trim();
    }

    public void checkLogos() {
        String[] logoXpaths = {
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[1]/img",
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[2]/img",
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[3]/img",
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[4]/img",
                "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul/li[5]/img"
        };

        for (String xpath : logoXpaths) {
            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            if (!logo.isDisplayed()) {
                throw new AssertionError("Логотип не отображается: " + xpath);
            }
        }
    }

    public void checkLink() {
        String linkXpath = "//a[@href='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']";
        WebElement linkElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(linkXpath)));

        String currentUrl = driver.getCurrentUrl();
        linkElement.click();
        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));

        String newUrl = driver.getCurrentUrl();
        if (currentUrl.equals(newUrl)) {
            throw new AssertionError("URL не изменился после клика по ссылке");
        }
    }

    public void fillForm(String phone, String sum, String email) {
        js.executeScript("document.getElementById('bxdynamic_pay-form_start').style.display='block';");

        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@class='phone']")));
        js.executeScript("arguments[0].value='" + phone + "'; arguments[0].dispatchEvent(new Event('input'));", phoneField);

        WebElement sumField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-sum")));
        sumField.sendKeys(sum);

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-email")));
        emailField.sendKeys(email);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Продолжить')]")));
        js.executeScript("arguments[0].click();", submitButton);
    }

    public void checkPlaceholders() {
        checkPlaceholder(By.id("connection-phone"), "Номер телефона");
        checkPlaceholder(By.id("connection-sum"), "Сумма");
        checkPlaceholder(By.id("connection-email"), "E-mail для отправки чека");
    }

    public void checkPaymentDisplay(String expectedPhone) {
        js.executeScript("document.getElementById('bxdynamic_pay-form_start').style.display='block';");

        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@class='phone']")));
        actionsInput(phoneField, expectedPhone);

        WebElement sumField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-sum")));
        actionsInput(sumField, "100");

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-email")));
        actionsInput(emailField, "Example@yandex.ru");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Продолжить')]")));
        submitButton.click();

        switchToIframeAndCheckPaymentDetails(expectedPhone);
    }

    private void switchToIframeAndCheckPaymentDetails(String expectedPhone) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe.bepaid-iframe")));

        WebElement displayedSum = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[1]/div[1]/span")));
        String actualSum = displayedSum.getText().trim();
        actualSum = actualSum.replaceAll("\\.00", "");
        if (!actualSum.equals("100 BYN")) {
            throw new AssertionError("Некорректная сумма в окне подтверждения");
        }

        WebElement displayedPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[2]/span")));
        String actualPhone = displayedPhone.getText().replaceAll("[^0-9]", "");
        if (!actualPhone.equals("375" + expectedPhone)) {
            throw new AssertionError("Некорректный номер телефона в окне подтверждения");
        }

        WebElement payButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/app-root/div/div/div/app-payment-container/section/div/app-card-page/div/div[1]/button")));
        String payButtonText = payButton.getText().trim();
        payButtonText = payButtonText.replaceAll("\\.00", "");
        if (!payButtonText.contains("100 BYN")) {
            throw new AssertionError("Некорректная сумма на кнопке 'Оплатить'");
        }

        checkCardPlaceholders();
        driver.switchTo().defaultContent();
    }

    private void checkPlaceholder(By locator, String expectedPlaceholder) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String actualPlaceholder = element.getAttribute("placeholder");
        if (!actualPlaceholder.equals(expectedPlaceholder)) {
            throw new AssertionError("Плейсхолдер не совпадает");
        }
    }

    private void actionsInput(WebElement element, String input) {
        new Actions(driver).moveToElement(element).click().sendKeys(input).perform();
    }

    private void checkCardPlaceholders() {
        String[] labels = {
                "Номер карты", "Срок действия", "CVC", "Имя держателя (как на карте)"
        };

        for (String label : labels) {
            WebElement labelElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[contains(text(),'" + label + "')]")));
            if (labelElement == null) {
                throw new AssertionError("Плейсхолдер '" + label + "' не найден");
            }
        }
    }
}
