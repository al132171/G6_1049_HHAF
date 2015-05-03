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

public class AsignarMonitorNoDisponible {
	WebDriver webDriver = null;

	@Given("^Reservas sin asignar monitor y existen monitores$")
	public void list_reservas_pendientes() throws Throwable {
		webDriver = new FirefoxDriver();
		webDriver.navigate().to("http://localhost:8080/Natureadventure/html/gerente/gestionarReservas.html");
	}


	@When("^Pulso el botón de asignar un monitor$")
	public void click_button_asignar_monitor() throws Throwable {
		webDriver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		webDriver.findElement(By.id("asignar:Motor Storm")).click();
		try {
			webDriver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
			webDriver.findElement(By.id("cancelar")).click();		
			webDriver.close();
		} catch (NoSuchElementException e) {
			fail("Añadir incorrecto");
		}
	}

	@Then("^Se muestra un mensaje indicando que no existen monitores y cancelo la reserva$")
	public void show_in_aceptadas() throws Throwable {
		try {
			webDriver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
			webDriver.findElement(By.id("cancelar")).click();
			webDriver.close();
		} catch (NoSuchElementException e) {
			fail("Añadir correcto");
		}
	}
}

