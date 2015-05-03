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


public class ConsultarReservasActivasCorrecto {

	    WebDriver webDriver = null;

	    @Given("^Tengo reservas en el sistema$")
	    public void list_displayed() throws Throwable {
	        webDriver = new FirefoxDriver();
	        webDriver.navigate().to("http://localhost:8080/Natureadventure/html/gerente/gestionarReservas.html");
	    }

	    @When("^Voy al apartado de reservas activas$")
	    public void get_reservas() throws Throwable {
	    	webDriver.findElement(By.id("activas")).click();
        	webDriver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
	    }

	    @Then("^Las reservas activas se muestran en pantalla$")
	    public void see_reservas() throws Throwable {
	        try {
		    	webDriver.findElement(By.id("Motor Storm")); //tiene que estar Running
		    	webDriver.close();
	        } catch (NoSuchElementException e) {
	            fail("Listado de reservas incorrecto");
	        }
	    }
	}

