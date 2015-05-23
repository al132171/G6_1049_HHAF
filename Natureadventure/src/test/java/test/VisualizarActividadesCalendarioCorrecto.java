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


public class VisualizarActividadesCalendarioCorrecto {

	    WebDriver webDriver = null;

	    @Given("^Hay una serie de reservas realizadas$")
	    public void list_displayed() throws Throwable {
	        webDriver = new FirefoxDriver();
	        webDriver.navigate().to("http://localhost:8080/Natureadventure/html/monitor/dashboard.html#/user?id=Monitor1");
	    }

	    @When("^Se consultan el calendario de reservas$")
	    public void get_actividades() throws Throwable {
        	webDriver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
	    }

	    @Then("^Se muestran marcados los días con alguna actividad programada$")
	    public void click_update() throws Throwable {
	        try {
		    	webDriver.findElement(By.className("event-item ng-binding"));
		    	webDriver.close();
	        } catch (NoSuchElementException e) {
	            fail("Listado de actividades incorrecto");
	        }
	    }
	}

