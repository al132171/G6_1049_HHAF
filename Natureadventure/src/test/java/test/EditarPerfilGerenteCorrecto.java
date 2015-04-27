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


public class EditarPerfilGerenteCorrecto {

	    WebDriver webDriver = null;

	    @Given("^Un gerente logueado en el sistema$")
	    public void perfil_displayed() throws Throwable {
	        webDriver = new FirefoxDriver();
	        webDriver.navigate().to("http://localhost:8080/Natureadventure/html/gerente/editarPerfil.html#/user?id=gerente");
	    }

	    @When("^Modifico mis datos desde el formulario para editar mi perfil$")
	    public void change_fields() throws Throwable {
	    	webDriver.findElement(By.id("nombre")).clear();
	    	webDriver.findElement(By.id("apellidos")).clear();
	    	webDriver.findElement(By.id("email")).clear();
	    	webDriver.findElement(By.id("password")).clear();
	    	webDriver.findElement(By.id("telefono")).clear();
        	webDriver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);

	    	webDriver.findElement(By.id("nombre")).sendKeys("Timmy");
	    	webDriver.findElement(By.id("apellidos")).sendKeys("Turner");
	    	webDriver.findElement(By.id("email")).sendKeys("padrinosmagicos@natureadventure.es");
	    	webDriver.findElement(By.id("password")).sendKeys("aaa");
	    	webDriver.findElement(By.id("telefono")).sendKeys("964-22-11-00");
            webDriver.findElement(By.id("editarPerfil")).click();
	    }

	    @Then("^Se actualizan mis cambios y se muestra mi perfil actualizado$")
	    public void click_update() throws Throwable {
	        try {
		    	webDriver.findElement(By.id("exito"));
		    	webDriver.close();
	        } catch (NoSuchElementException e) {
	            fail("Perfil incorrecto");
	        }
	    }
	}

