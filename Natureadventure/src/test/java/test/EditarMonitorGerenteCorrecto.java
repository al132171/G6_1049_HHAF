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

public class EditarMonitorGerenteCorrecto {

	    WebDriver webDriver = null;
	    
	    @Given("^Hay monitores registrados en el sistema$")
	    public void list_displayed_no_rep() throws Throwable {
	        webDriver = new FirefoxDriver();
	        webDriver.navigate().to("http://localhost:8080/Natureadventure/html/gerente/gestionarMonitores.html");
	    }

	    @When("^Se editan los datos de un monitor$")
	    public void edit_monitor() throws Throwable {
	    	webDriver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
	    	webDriver.findElement(By.id("update:Monitor CUCUMBER")).click();
	    	webDriver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
	    	webDriver.findElement(By.id("nombre")).clear();
	    	webDriver.findElement(By.id("nombre")).sendKeys(" 2");
	    	webDriver.findElement(By.id("editar")).click();
	    }

	    @Then("^Se muestra en el listado de monitores del sistema$")
	    public void find_error() throws Throwable {
	        try {
		    	webDriver.findElement(By.id("Monitor CUCUMBER 2")); 
		    	webDriver.close();
	        } catch (NoSuchElementException e) {
	            fail("Editar monitor incorrecto");
	        }
	    }
	}

