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


public class LoginCorrectoScenario {

	    WebDriver webDriver = null;

	    @Given("^Un gerente registrado en el sistema$")
	    public void the_login_is_displayed() throws Throwable {
	        webDriver = new FirefoxDriver();
	        webDriver.navigate().to("http://localhost:8080/Natureadventure/html/login.html");
	    }

	    @When("^Se identifica en el sistema utilizando su usuario y contraseña$")
	    public void click_the_login_button() throws Throwable {
	    	webDriver.findElement(By.id("username")).sendKeys("gerente");
	    	webDriver.findElement(By.id("password")).sendKeys("aaa");
            webDriver.findElement(By.id("nuevo")).click();
        	webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	    }

	    @Then("^Se muestra el dashboard de gerencia$")
	    public void the_user_is_loged() throws Throwable {
	        try {
		    	webDriver.findElement(By.id("login:gerente")); //estoy dentro de gerencia
		    	webDriver.close();
	        } catch (NoSuchElementException e) {
	            fail("Login incorrecto");
	        }
	    }
	}

