package test;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class EliminarMonitorGerenteCorrecto {

	    WebDriver webDriver = null;
	    
	    @Given("^Tenemos monitores registrados en el sistema$")
	    public void list_displayed_no_rep() throws Throwable {
	        webDriver = new FirefoxDriver();
	        webDriver.navigate().to("http://localhost:8080/Natureadventure/html/gerente/gestionarMonitores.html");
	    }

	    @When("^Se da de baja un monitor del sistema$")
	    public void add_delete_monitor() throws Throwable {
	    	webDriver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);

	    	webDriver.findElement(By.id("darDeBaja")).click();
	    }

	    @Then("^Su clave y contraseña de usuario ya no son válidas$")
	    public void list_monitores() throws Throwable {
	        try {
		    	webDriver.findElement(By.id("DNI CUCUMBER"));
		    	fail("Eliminar monitor incorrecto");
	        } catch (NoSuchElementException e) {
		    	webDriver.close();
	        }
	    }
	}

