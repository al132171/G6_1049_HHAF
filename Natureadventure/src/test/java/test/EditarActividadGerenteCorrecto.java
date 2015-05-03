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


public class EditarActividadGerenteCorrecto {

	    WebDriver webDriver = null;
	    
	    @Given("^Tengo actividades el sistema$")
	    public void list_displayed_no_rep() throws Throwable {
	        webDriver = new FirefoxDriver();
	        webDriver.navigate().to("http://localhost:8080/Natureadventure/html/gerente/gestionarActividades.html");
	    }

	    @When("^Modifico una actividad existente en el sistema$")
	    public void edit_actividad() throws Throwable {
	    	webDriver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
	    	webDriver.findElement(By.id("update:Actividad CUCUMBER")).click();
	    	webDriver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
	    	webDriver.findElement(By.id("nombre")).clear();
	    	webDriver.findElement(By.id("nombre")).sendKeys(": segunda edición");
	    	webDriver.findElement(By.id("editar")).click();
	    }

	    @Then("^Se muestra el listado de actividades en el sistema$")
	    public void find_error() throws Throwable {
	        try {
		    	webDriver.findElement(By.id("Actividad CUCUMBER: segunda edición")); 
		    	webDriver.close();
	        } catch (NoSuchElementException e) {
	            fail("Añadir correcto");
	        }
	    }
	}

