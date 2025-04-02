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
        } catch (Exception ignored) {
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
        js.executeScript("arguments[0].click();", linkElement); // Теперь клик через JS
        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));

        String newUrl = driver.getCurrentUrl();
        if (currentUrl.equals(newUrl)) {
            throw new AssertionError("URL не изменился после клика по ссылке");
        }
    }

    public void fillForm(String number, String number1, String mail) {
    }

    public void checkPlaceholders() {
    }

    public void checkPaymentDisplay(String number) {
        
    }
}
