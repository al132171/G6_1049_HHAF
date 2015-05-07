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


public class ConsultarReservasAceptadasCorrecto {

	    WebDriver webDriver = null;

	    @Given("^Existen reservas que han sido asignadas a un monitor$")
	    public void list_displayed() throws Throwable {
	        webDriver = new FirefoxDriver();
	        webDriver.navigate().to("http://localhost:8080/Natureadventure/html/gerente/gestionarReservas.html");
	    }

	    @When("^Voy al apartado de reservas aceptadas$")
	    public void get_reservas() throws Throwable {
	    	webDriver.findElement(By.id("aceptadas")).click();
        	webDriver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
	    }

	    @Then("^El sistema muestra las reservas que han sido aceptadas$")
	    public void see_reservas() throws Throwable {
	        try {
	        	webDriver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
		    	webDriver.findElement(By.id("Motor Storm")); //tiene que estar Running
		    	webDriver.close();
	        } catch (NoSuchElementException e) {
	            fail("Listado de reservas incorrecto");
	        }
	    }
	}

