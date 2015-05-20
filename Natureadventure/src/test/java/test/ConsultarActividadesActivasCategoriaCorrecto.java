package test;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class ConsultarActividadesActivasCategoriaCorrecto {

	    WebDriver webDriver = null;

	    @Given("^Una cotegoría de actividades en el sistema$")
	    public void list_displayed() throws Throwable {
	        webDriver = new FirefoxDriver();
	        webDriver.navigate().to("http://localhost:8080/Natureadventure/html/categorias.html");
	    }

	    @When("^Consulto dicha categoría$")
	    @And("^Existen actividades activas en el sistema pertenecientes a esa categoría$")
	    public void get_actividades() throws Throwable {
        	webDriver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
            webDriver.findElement(By.id("actividadesCategoria-Bicicleta")).click();
	    }

	    @Then("^Se muestran todas las actividades disponibles en esa categoría$")
	    public void click_submit() throws Throwable {
	        try {
		    	webDriver.findElement(By.id("masinfo"));
		    	webDriver.close();
	        } catch (NoSuchElementException e) {
	            fail("Búsqueda de actividades incorrecta");
	        }
	    }
	}

