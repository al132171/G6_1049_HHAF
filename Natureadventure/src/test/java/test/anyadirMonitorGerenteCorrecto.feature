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


public class AnyadirMonitorGerenteCorrecto {

	    WebDriver webDriver = null;
	    
	    @Given("^No existe el monitor en el sistema$")
	    public void list_displayed_no_rep() throws Throwable {
	        webDriver = new FirefoxDriver();
	        webDriver.navigate().to("http://localhost:8080/Natureadventure/html/gerente/gestionarMonitores.html");
	    }

	    @When("^Se introduce un monitor nuevo$")
	    public void add_monitor() throws Throwable {
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

	    @Then("^se genera automáticamente un nombre de usuario y contraseña y se de de alta a ese monitor en el sistema$")
	    public void list_monitores() throws Throwable {
	        try {
		    	webDriver.findElement(By.id("DNI CUCUMBER"));
		    	webDriver.close();
	        } catch (NoSuchElementException e) {
	            fail("Añadir incorrecto");
	        }
	    }
	}

