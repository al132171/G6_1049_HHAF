package controlador;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import modelo.datos.Actividad;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Before;
import org.junit.Test;


public class ActividadServiciosTest {

	private static final String uriBase = "http://localhost:8080/Natureadventure/gerente/actividades/";
	private Actividad actividad1, actividad2, actividad3;
	
	@PersistenceContext(unitName = "natureadventureJTA")
    EntityManager em;

	@Before
	public void setUp() {

		// Inicializo 3 actividades
		actividad1 = new Actividad(null, "ActividadPrueba1", "02:00", "10:00", "13-04-2015", "13-05-2015", 
				"Primera actividad de prueba para el método creaNuevaEntrada", "Bajo", 10.0f, 10, 5,
				"Lugar establecido", "Imagen de prueba1", "Bicicleta", "T");

		actividad2 = new Actividad(null, "ActividadPrueba2", "02:00", "10:00", "13-04-2015", "13-05-2015", 
				"Segunda actividad de prueba para el método creaNuevaEntrada", "Bajo", 10.0f, 10, 5,
				"Lugar establecido", "Imagen de prueba2", "Bicicleta", "T");

		actividad3 = new Actividad(null, "ActividadPrueba3", "02:00", "10:00", "13-04-2015", "13-05-2015", 
				"Tercera actividad de prueba para el método creaNuevaEntrada", "Bajo", 10.0f, 10, 5,
				"Lugar establecido", "Imagen de prueba3", "Bicicleta", "T");

		// Elimino las 5 actividades que se usan en las pruebas
		WebClient.create(uriBase)
		.path("ActividadPrueba1")
		.accept(MediaType.APPLICATION_JSON)
		.delete();

		WebClient.create(uriBase)
		.path("ActividadPrueba2")
		.accept(MediaType.APPLICATION_JSON)
		.delete();

		WebClient.create(uriBase)
		.path("ActividadPrueba3")
		.accept(MediaType.APPLICATION_JSON)
		.delete();

		WebClient.create(uriBase)
		.path("ActividadPrueba4")
		.accept(MediaType.APPLICATION_JSON)
		.delete();

		WebClient.create(uriBase)
		.path("ActividadPrueba5")
		.accept(MediaType.APPLICATION_JSON)
		.delete();

		// Inserto las 3 actividades inicializadas
		WebClient.create(uriBase)
		.path("ActividadPrueba1")
		.accept(MediaType.APPLICATION_JSON)
		.type(MediaType.APPLICATION_XML)
		.put(actividad1);

		WebClient.create(uriBase)
		.path("ActividadPrueba2")
		.accept(MediaType.APPLICATION_JSON)
		.type(MediaType.APPLICATION_XML)
		.put(actividad2);

		WebClient.create(uriBase)
		.path("ActividadPrueba3")
		.accept(MediaType.APPLICATION_JSON)
		.type(MediaType.APPLICATION_XML)
		.put(actividad3);
		
		
		
	}

	@Test
	public void testCreaNuevaEntradaOkXml() {
		Response response;
		int numero = (int) Math.floor(Math.random()*(10000000-1+1)+1);
		Actividad actividad4 = new Actividad(null, "ActividadPrueba" + numero, "01:00", "10:00", "12-04-2015", "12-05-2015", 
				"Cuarta actividad de prueba para el método creaNuevaEntrada", "Bajo", 10.0f, 10, 5,
				"Lugar establecido", "Imagen de prueba4", "Bicicleta", "T");

		// Test OK produciendo un XML 
		response = WebClient.create(uriBase)
				.path("ActividadPrueba" + numero)
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_XML)
				.put(actividad4);
		assertThat(response.getStatusInfo().getStatusCode(), is(Response.Status.OK.getStatusCode()));
	}
	
	@Test
	public void testCreaNuevaEntradaOkJson() {
		Response response;
		int numero = (int) Math.floor(Math.random()*(10000000-1+1)+1);
		Actividad actividad5 = new Actividad(null, "ActividadPrueba" + numero, "02:00", "10:00", "13-04-2015", "13-05-2015", 
				"Quinta actividad de prueba para el método creaNuevaEntrada", "Bajo", 10.0f, 10, 5,
				"Lugar establecido", "Imagen de prueba5", "Bicicleta","T");
		
		// Test OK produciendo un JSON 
				response = WebClient.create(uriBase)
						.path("ActividadPrueba" + numero)
						.accept(MediaType.APPLICATION_JSON)
						.type(MediaType.APPLICATION_JSON)
						.put(actividad5);
				assertThat(response.getStatusInfo().getStatusCode(), is(Response.Status.OK.getStatusCode()));
	}
	
	@Test
	public void testCreaNuevaEntradaBadRequest() {
		Response response;
		
		Actividad actividad5 = new Actividad(null, "ActividadPrueba5", "02:00", "10:00", "13-04-2015", "13-05-2015", 
				"Quinta actividad de prueba para el método creaNuevaEntrada", "Bajo", 10.0f, 10, 5,
				"Lugar establecido", "Imagen de prueba5", "Bicicleta", "T");


		// Test Bad request 
		response = WebClient.create(uriBase)
				.path("ActividadPrueba1")
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.put(actividad5);
		assertThat(response.getStatusInfo().getStatusCode(), is(Response.Status.BAD_REQUEST.getStatusCode()));
	}

	@Test
	public void testCreaNuevaEntrada() {
		Response response;

		Actividad actividad4 = new Actividad(null, "ActividadPrueba4", "01:00", "10:00", "12-04-2015", "12-05-2015", 
				"Cuarta actividad de prueba para el método creaNuevaEntrada", "Bajo", 10.0f, 10, 5,
				"Lugar establecido", "Imagen de prueba4", "Bicicleta", "T");

		// Test No Content
		response = WebClient.create(uriBase)
				.path("ActividadPrueba4")
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.put(actividad4);
		assertThat(response.getStatusInfo().getStatusCode(), is(Response.Status.NO_CONTENT.getStatusCode()));
	}

	@Test
	public void testBuscarActividadPorNombreOk() {
		Response response;

		// Test recupera actividad1 OK
		response = WebClient.create(uriBase)
				.path("ActividadPrueba1")
				.accept(MediaType.APPLICATION_JSON)
				.get();
		assertThat(response.getStatusInfo().getStatusCode(), is(Response.Status.OK.getStatusCode()));
	}


	@Test
	public void testBuscarActividadPorNombreNotFound() {
		Response response;

		// Test recupera actividad0 Not Found
		response = WebClient.create(uriBase)
				.path("ActividadPrueba0")
				.accept(MediaType.APPLICATION_JSON)
				.get();
		assertThat(response.getStatusInfo().getStatusCode(), is(Response.Status.NOT_FOUND.getStatusCode()));
	}


	@Test
	public void testListaTodasActividades() {
		Response response;

		// Test recupera todas las actividades OK
		response = WebClient.create(uriBase)
				.accept(MediaType.APPLICATION_JSON)
				.get();
		assertThat(response.getStatusInfo().getStatusCode(), is(Response.Status.OK.getStatusCode()));
	}
}
