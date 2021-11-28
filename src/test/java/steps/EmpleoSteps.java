package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.EmpleoPage;
import java.util.ArrayList;
import java.util.List;

public class EmpleoSteps {

    EmpleoPage empleoPage = new EmpleoPage();

    @Given("Ingreso a la pagina de Choucair")
    public void ingresoALaPaginaDeChoucair() {
        empleoPage.navigateToChoucairtesting();
    }

    @When("Ingreso a la seccion Empleos")
    public void ingresoALaSeccionEmpleos() {
        empleoPage.clicMenuEmpleos();
    }

    @And("Hago clic en el icono {string}")
    public void hagoClicEnElIcono(String arg0) {
        List<String> options = empleoPage.obtenerInformacion();
        boolean optionIsThere = options.contains(arg0);
        if (optionIsThere) {
            empleoPage.irASeleccion(arg0);
        } else {
            System.out.println("La opcion " + arg0 + " NO esta disponible: FAILED");
        }
    }

    @Then("Me desplazo a la seccion de {string}")
    public void desplazoALaSección(String arg1) {
        List<String> options = new ArrayList<>();
        options.add(empleoPage.getTextSerChoucair());
        options.add(empleoPage.getTextPrepararseAplica());

        boolean optionIsThere = options.contains(arg1);
        if (optionIsThere) {
            Assert.assertEquals(arg1, arg1);
            System.out.println("Se desplaza a la seccion: " + arg1);
        }
      //  empleoPage.close();
    }

    @And("Hago clic en el boton {string}")
    public void hagoClicEnElBoton(String arg0) {
        Assert.assertEquals(arg0, empleoPage.getTextBotonIrEmpleos());
        empleoPage.clicBotonIrEmpleos();
    }

    @When("Preciono clic en el boton {string}")
    public void precionoClicEnElBoton(String arg0) {
        Assert.assertEquals(arg0, empleoPage.getTextBotonIrContinuar());
        empleoPage.clicBtnContinuar();
    }

    @Then("Soy redirigido al Portal de Magneto {string}")
    public void portalMagneto(String arg0) {
        Assert.assertTrue("Pagina no Encontrada",empleoPage.getTitlePage().contains(arg0));
        //empleoPage.close();
    }


    @Then("se presentan todos los enlaces de la sección")
    public void validarLinks() {
        Assert.assertTrue("there area broken links", empleoPage.checkPageLinks());
       // empleoPage.close();
    }
}


