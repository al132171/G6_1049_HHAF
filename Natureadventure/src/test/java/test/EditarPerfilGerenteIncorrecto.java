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


public class EditarPerfilGerenteIncorrecto {

	    WebDriver webDriver = null;

	    @Given("^Un gerente logueado en el sistema en editar perfil$")
	    public void perfil_displayed_empty() throws Throwable {
	        webDriver = new FirefoxDriver();
	        webDriver.navigate().to("http://localhost:8080/Natureadventure/html/gerente/editarPerfil.html#/user?id=gerente");
	    }

	    @When("^Modifico mis datos y dejo campos vacíos en el formulario$")
	    public void change_fields_empty() throws Throwable {
	    	webDriver.findElement(By.id("nombre")).clear();
	    	webDriver.findElement(By.id("apellidos")).clear();
	    	webDriver.findElement(By.id("email")).clear();
	    	webDriver.findElement(By.id("password")).clear();
	    	webDriver.findElement(By.id("telefono")).clear();

	    	webDriver.findElement(By.id("editarPerfil")).click();
        	webDriver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
	    }

	    @Then("^El sistema me muestra un error$")
	    public void click_update_empty() throws Throwable {
	        try {
		    	webDriver.findElement(By.id("error"));
		    	webDriver.close();
	        } catch (NoSuchElementException e) {
	            fail("Perfil correcto");
	        }
	    }
	}

