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


public class ConsultarActividadesActivasCategoriaError {

	    WebDriver webDriver = null;

	    @Given("^Una categoría de actividades en el sistema$")
	    public void list_displayed() throws Throwable {
	        webDriver = new FirefoxDriver();
	        webDriver.navigate().to("http://localhost:8080/Natureadventure/html/categorias.html");
	    }

	    @When("^Busco dicha categoría$")
	    @And("^No xisten actividades activas en el sistema pertenecientes a esa categoría$")
	    public void get_actividades() throws Throwable {
        	webDriver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
            webDriver.findElement(By.id("actividadesCategoria-Bicicleta")).click();
	    }

	    @Then("^No se muestra ninguna actividad$")
	    public void click_submit() throws Throwable {
	        try {
		    	webDriver.findElement(By.id("Bicicleta de montaña"));
	            fail("Búsqueda de actividades incorrecta");
	        } catch (NoSuchElementException e) {
		    	webDriver.close();
	        }
	    }
	}

