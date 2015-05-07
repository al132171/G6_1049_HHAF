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


public class AnyadirActividadGerenteRepetida {

	    WebDriver webDriver = null;
	    
	    @Given("^Tengo actividades repetidas en el sistema$")
	    public void list_displayed_no_rep() throws Throwable {
	        webDriver = new FirefoxDriver();
	        webDriver.navigate().to("http://localhost:8080/Natureadventure/html/gerente/gestionarActividades.html");
	    }

	    @When("^Añado una actividad repetida al sistema$")
	    public void add_actividad_rep() throws Throwable {
	    	webDriver.findElement(By.id("añadir1")).click();
	    	webDriver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
	    	webDriver.findElement(By.id("nombre")).sendKeys("Actividad CUCUMBER");
	    	webDriver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
	    	webDriver.findElement(By.id("descripcion")).sendKeys("Aprende a CUCUMBER en una hora");
	    	webDriver.findElement(By.id("duracion")).sendKeys("60");
	    	webDriver.findElement(By.id("horaInicio")).sendKeys("16:00");
	    	webDriver.findElement(By.id("fechaInicio")).sendKeys("15-10-2015");
	    	webDriver.findElement(By.id("fechaFin")).sendKeys("15-10-2015");
	    	webDriver.findElement(By.id("precio")).sendKeys("2");
	    	webDriver.findElement(By.id("participantesMin")).sendKeys("1");
	    	webDriver.findElement(By.id("participantesMax")).sendKeys("10");
	    	webDriver.findElement(By.id("lugar")).sendKeys("TD0206");
	    	webDriver.findElement(By.id("imagen")).sendKeys("cucumber.jpg");
	    	webDriver.findElement(By.id("añadir")).click();
	    }

	    @Then("^El sistema muestra un mensaje de error$")
	    public void find_error() throws Throwable {
	        try {
		    	webDriver.findElement(By.id("errorNombre")); 
		    	webDriver.close();
	        } catch (NoSuchElementException e) {
	            fail("Añadir correcto");
	        }
	    }
	}

