package pages;

import generic.WebDriverDOM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


public class EmpleoPage extends WebDriverDOM {

    public EmpleoPage(){
        super(driver);
    }

    //Localizador boton menu empleos
    String seccionEmpleos = "(//a[normalize-space()='Empleos'])[1]";

    // Localizador Boton ir al portal de empleos
    String botonIrEmpleos = "(//a[@role='button'])[1]";

    //* Localizador boton continuar del modal que redirecciona al portal magneto
    String botonContinuar = "//a[@class='elementor-button-link elementor-button elementor-size-sm']";

    // Localizador container seccion ¿Qué es ser Choucair? / Prepararse para aplicar
   // String containerOptions = "(//div[@class='elementor-container elementor-column-gap-default'])[1]";

    // Localizador Seccion ¿Qué es ser Choucair? / Prepararse para aplicar
    //WebElement infoSeccion = driver.findElement(By.xpath("(//div[@class='elementor-row'])[2]"));
    String infoSeccion = "(//div[@class='elementor-row'])[2]";

    // Localizador del contenedor de las opciones ¿Qué es ser Choucair? / Prepararse para aplica
    String opcionContainer = "elementor-widget-container";

    // Localizador seccion SER CHOUCAIR
    String serChoucair = "(//h2[normalize-space()='SER CHOUCAIR'])[1]";

    // Localizador seccion PREPARARSE PARA APLICAR
    String prepararseAplicar = "(//div[@class='elementor-widget-container'])[18]";

    public void navigateToChoucairtesting(){
        navigateTo("https://www.choucairtesting.com/");
    }
    public void clicMenuEmpleos(){
        clickElement(seccionEmpleos);
    }
    public void clicBotonIrEmpleos(){
        scrollPage("0,150");
        clickElement(botonIrEmpleos);
    }
    public String getTextBotonIrEmpleos(){
        return textFromElement(botonIrEmpleos);
    }
    public void clicBtnContinuar(){
        clickElement(botonContinuar);
    }
    public String getTextBotonIrContinuar(){
        return textFromElement(botonContinuar);
    }

    public String getTitlePage (){
       return driver.getTitle();
    }


    public List<String> obtenerInformacion (){
        List<WebElement> options = getAllElements(Find(infoSeccion) , opcionContainer);
        List<String> stringsOptions = new ArrayList<>();
        for (WebElement element :options) {
            stringsOptions.add(element.getText());
        }
        return stringsOptions;
    }

    public void irASeleccion(String option){
        List<WebElement> list = getAllElements(Find(infoSeccion),opcionContainer);
        for (WebElement element: list){
            if(Objects.equals(element.getText(), option)) element.click();
        }
    }

    public String getTextSerChoucair(){
        return textFromElement(serChoucair);
    }
    public String getTextPrepararseAplica(){
        return textFromElement(prepararseAplicar);
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

    public void close(){
        driver.close();
    }

}
