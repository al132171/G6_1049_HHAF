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


public class AnyadirMonitorGerenteRepetido {

	    WebDriver webDriver = null;
	    
	    @Given("^Existen monitores en el sistema$")
	    public void list_displayed_no_rep() throws Throwable {
	        webDriver = new FirefoxDriver();
	        webDriver.navigate().to("http://localhost:8080/Natureadventure/html/gerente/gestionarMonitores.html");
	    }

	    @When("^Se introducen los datos de un monitor que ya existe$")
	    public void add_monitor_rep() throws Throwable {
	    	webDriver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
	    	webDriver.findElement(By.id("añadirModal")).click();
	    	webDriver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
	    	webDriver.findElement(By.id("nombre")).sendKeys("Monitor CUCUMBER");
	    	webDriver.findElement(By.id("apellidos")).sendKeys("Apellidos CUCUMBER");
	    	webDriver.findElement(By.id("correo")).sendKeys("correo@CUCUMBER");
	    	webDriver.findElement(By.id("telefono")).sendKeys("TLF CUCUMBER");
	    	webDriver.findElement(By.id("dni")).sendKeys("DNI CUCUMBER");
	    	webDriver.findElement(By.id("username")).sendKeys("usr CUCUMBER");
	    	webDriver.findElement(By.id("password")).sendKeys("passwd CUCUMBER");
	    	webDriver.findElement(By.id("especialidad")).sendKeys("Especialidad CUCUMBER");

	    	webDriver.findElement(By.id("añadir")).click();
	    }

	    @Then("^Se muestra mensaje de avisando de que ese monitor ya existe$")
	    public void find_error() throws Throwable {
	        try {
		    	webDriver.findElement(By.id("errorDNI")); 
		    	webDriver.close();
	        } catch (NoSuchElementException e) {
	            fail("Añadir correcto");
	        }
	    }
	}

