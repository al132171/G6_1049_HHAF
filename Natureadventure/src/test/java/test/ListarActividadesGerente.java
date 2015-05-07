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


public class ListarActividadesGerente {

	    WebDriver webDriver = null;

	    @Given("^Tengo actividades en el sistema$")
	    public void list_displayed() throws Throwable {
	        webDriver = new FirefoxDriver();
	        webDriver.navigate().to("http://localhost:8080/Natureadventure/html/gerente/gestionarActividades.html");
	    }

	    @When("^Accedo al catálogo de actividades$")
	    public void get_actividades() throws Throwable {
        	webDriver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
	    }

	    @Then("^El sistema muestra en pantalla las actividades que se ofrecen$")
	    public void click_update() throws Throwable {
	        try {
		    	webDriver.findElement(By.id("Running")); //tiene que estar Running
		    	webDriver.close();
	        } catch (NoSuchElementException e) {
	            fail("Listado de actividades incorrecto");
	        }
	    }
	}

