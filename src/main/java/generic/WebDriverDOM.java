package generic;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import parameters.Parameters;

public class WebDriverDOM {

    protected static WebDriver driver;
    private static WebDriverWait wait;
    JavascriptExecutor js = (JavascriptExecutor) driver;


    static {
        System.setProperty("webdriver.chrome.driver", Parameters.pathDriverChrome);
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
    }

    public WebDriverDOM(WebDriver driver){
        WebDriverDOM.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

    public static void navigateTo(String url){
        driver.get(url);
    }

    public WebElement Find(String locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    public void scrollPage(String pixel ){
        js.executeScript ("window.scrollBy"+"("+pixel+")");

    }

    public void clickElement(String locator){
        Find(locator).click();
    }


    public String textFromElement(String locator){
        return Find(locator).getText();
    }

    public boolean elementIsDisplayed (String locator){
        return Find(locator).isDisplayed();
    }


    public List<WebElement> getAllElements(WebElement listLocators , String locator){
        return listLocators.findElements(By.className(locator));
    }

}
