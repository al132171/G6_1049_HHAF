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

public class AsignarMonitorReserva {
	WebDriver webDriver = null;

	@Given("^Reservas sin asignar monitor$")
	public void list_reservas_pendientes() throws Throwable {
		webDriver = new FirefoxDriver();
		webDriver.navigate().to("http://localhost:8080/Natureadventure/html/gerente/gestionarReservas.html");
	}


	@When("^Pulso de botón de asignar monitor y se muestran los monitores disponibles para esa actividad y selecciono uno$")
	public void click_button_asignar_monitor() throws Throwable {
		webDriver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		webDriver.findElement(By.id("asignar:Motor Storm")).click();
	
		try {
			webDriver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
			webDriver.findElement(By.id("selectMonitor")).click();
			webDriver.findElement(By.id("345")).click();		
			webDriver.findElement(By.id("aceptarMonitor")).click();			

			webDriver.close();
		} catch (NoSuchElementException e) {
			fail("Añadir incorrecto");
		}
	}

	@Then("^La reserva se muestra en el apartado de aceptadas$")
	public void show_in_aceptadas() throws Throwable {
		try {
			webDriver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
			webDriver.findElement(By.id("aceptadas")).click();
			webDriver.findElement(By.id("Motor Storm")).click();		
			webDriver.close();
		} catch (NoSuchElementException e) {
			fail("Añadir correcto");
		}
	}
}

