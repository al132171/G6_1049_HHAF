package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class LoginIncorrectoScenario {

	    WebDriver webDriver = null;

	    @Given("^Un gerente no registrado en el sistema$")
	    public void the_login_is_displayed() throws Throwable {
	        webDriver = new FirefoxDriver();
	        webDriver.navigate().to("http://localhost:8080/Natureadventure/html/login.html");
	    }

	    @When("^Se identifica en el sistema utilizando un usuario y/o contraseña incorrectos$")
	    public void click_the_login_button() throws Throwable {
	    	webDriver.findElement(By.id("username")).sendKeys("gerentefalso");
	    	webDriver.findElement(By.id("password")).sendKeys("123");
            webDriver.findElement(By.id("nuevo")).click();
        	webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	    }

	    @Then("^Se muestra un mensaje advirtiendo que los datos son erróneos$")
	    public void the_person_is_deleted_from_the_database() throws Throwable {
	        try {
		    	webDriver.findElement(By.id("login:gerente")); //si no lo encuentra es que no se ha logueado
	        } catch (NoSuchElementException e) {
		    	webDriver.close(); //correcto
	        }
	    }
	}

