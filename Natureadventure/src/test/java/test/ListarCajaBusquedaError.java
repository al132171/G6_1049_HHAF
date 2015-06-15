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


public class ListarCajaBusquedaError {

	    WebDriver webDriver = null;

	    @Given("^Hay actividades en el sistema$")
	    public void list_displayed() throws Throwable {
	        webDriver = new FirefoxDriver();
	        webDriver.navigate().to("http://localhost:8080/Natureadventure/html/categorias.html");
	    }

	    @When("^Uso la búsqueda avanzada$")
	    @And("^No hay actividades activas relacionadas con los parametros de búsqueda$")
	    public void get_actividades() throws Throwable {
        	webDriver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
	    	webDriver.findElement(By.id("inputCategorias")).clear();
	    	webDriver.findElement(By.id("nombre")).sendKeys("bicicleta");
            webDriver.findElement(By.id("submitCategoria")).click();
	    }

	    @Then("^No se muestra ninguna actividad como resultado de la búsqueda$")
	    public void click_submit() throws Throwable {
	        try {
		    	webDriver.findElement(By.id("Bicicleta"));
	            fail("Búsqueda de actividades incorrecta");
	        } catch (NoSuchElementException e) {
		    	webDriver.close();
	        }
	    }
	}

