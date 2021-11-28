package pages;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class prueba {

    WebDriver driver;
    String pathBase = System.getProperty("user.dir");
    String pathDriver = pathBase + "/src/test/resources/drivers/chromedriver.exe";

    @Test
    public void choucair() {
        System.setProperty("webdriver.chrome.driver", pathDriver);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.choucairtesting.com/empleos-testing/");

        Assert.assertTrue("there area broken links", checkPageLinks());


/*
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 6);

        WebElement locator1 = driver.findElement(By.xpath("(//div[@class='elementor-container elementor-column-gap-default'])[1]"));
        WebElement locator = driver.findElement(By.xpath("(//a[@role='button'])[1]"));

        js.executeScript("arguments[0].scrollIntoView();", locator1);
        locator.click();
        WebElement continuar =  driver.findElement(By.xpath("//a[@class='elementor-button-link elementor-button elementor-size-sm']"));
        wait.until(ExpectedConditions.visibilityOf(continuar)).click();
        String pixel = "0,150";
        System.out.println("window.scrollBy"+"("+pixel+")");

        // Web element padre
        WebElement section = driver.findElement(By.xpath("(//div[@class='elementor-row'])[2]"));

        String opcionContainer = "elementor-widget-container";

        // Lista de options
        List<WebElement> options = section.findElements(By.className(opcionContainer));
        System.out.println("lista de opciones : "+options.size());

        System.out.println(opcionContainer);

        for (WebElement option : options) {
            if (Objects.equals(option.getText(), "¿Qué es ser Choucair?")){
                System.out.println("La opcion seleccionada es : " + option.getText());
                option.click();
            }
        }
*/

    }

    public boolean checkPageLinks(){
        WebElement cursos = driver.findElement(By.xpath("(//div[@class='elementor-row'])[11]"));
        List<WebElement> links = cursos.findElements(By.tagName("a"));
        String url = "";
        List<String> brokenLinks = new ArrayList<String>();
        List<String> okLinks = new ArrayList<String>();

        HttpURLConnection httpURLConnection = null;
        int responseCode = 200;

        for (WebElement link : links) {
            url = link.getAttribute("href");
            if (url == null || url.isEmpty()) {
                System.out.println(url + "url no esta configurada o esta vacía");
                continue;
            }
            try {
                httpURLConnection = (HttpURLConnection) (new URL(url).openConnection());
                httpURLConnection.setRequestMethod("HEAD");
                httpURLConnection.connect();
                responseCode = httpURLConnection.getResponseCode();

                if (responseCode > 400) {
                    System.out.println("ERROR BROKEN LINK: -- " + url);
                    brokenLinks.add(url);
                } else {
                    System.out.println("VALID LINK: -- " + url);
                    okLinks.add(url);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(" Valid links: "+ okLinks.size());
        System.out.println(" Invalid Link: " + brokenLinks.size());

        if (brokenLinks.size() > 0){
            System.out.println("***** ERROR -------------------- Broken Links");
            for (String brokenLink : brokenLinks) {
                System.out.println(brokenLink);
            }
            return false;
        }else {
            return  true;
        }

    }

}

