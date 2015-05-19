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


public class ListarMonitoresGerenteCorrecto {

	    WebDriver webDriver = null;

	    @Given("^Hay monitores registrados en el sistema$")
	    public void list_displayed() throws Throwable {
	        webDriver = new FirefoxDriver();
	        webDriver.navigate().to("http://localhost:8080/Natureadventure/html/gerente/gestionarMonitores.html");
	    }

	    @When("^Se consultan los monitores del sistema$")
	    public void get_monitores() throws Throwable {
        	webDriver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
	    }

	    @Then("^Se muestra una lista con los monitores existentes en el sistema$")
	    public void click_update() throws Throwable {
	        try {
		    	webDriver.findElement(By.id("DNI CUCUMBER"));
		    	webDriver.close();
	        } catch (NoSuchElementException e) {
	            fail("Listado de monitores incorrecto");
	        }
	    }
	}

