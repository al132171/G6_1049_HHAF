package controlador;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import modelo.dao.UsuarioJPA;
import modelo.datos.Usuario;

/**
 * @author appujimatica
 * Servicio de monitores para que usen los monitores de la plataforma
 */

@Path("gerente/monitores")
@Stateless
public class MonitorServicios {
	@Inject
	UsuarioJPA monitorJPA;
	@Context
	private UriInfo uriInfo;

	public MonitorServicios() {
		super();
	}

	@GET
	@Path("{dni}")
	@Produces("application/json")
	public Response buscarMonitorPorDni(@PathParam("dni") String dni) {
		Usuario monitor = monitorJPA.buscaMonitorPorDni(dni);
		if (monitor == UsuarioJPA.ENTRADA_NULL)
			return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(monitor).build();
	}

	@GET
	@Path("activos")
	@Produces("application/json")
	public Response listaTodosMonitoresActivos() {
		Usuario[] monitores = monitorJPA.listaTodosMonitoresActivos();
		return Response.ok(monitores).build();
	}
	
	@GET
	@Path("nodisponibles")
	@Produces("application/json")
	public Response listaTodosMonitoresNoDisponibles() {
		Usuario[] monitores = monitorJPA.listaTodosMonitoresNoDisponibles();
		return Response.ok(monitores).build();
	}


	@PUT
	@Path("anyadir/{dni}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_JSON)
	public Response creaNuevaEntrada(@PathParam("dni") String dni, Usuario monitor) {
		monitorJPA.nuevoMonitor(monitor);
		return Response.ok(monitor).build();
	}
	
	
	@PUT
	@Path("actualizar/{dni}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizaMonitor(@PathParam("dni") String dni, Usuario monitor) {
		monitorJPA.actualizaMonitor(monitor);
		return Response.ok(monitor).build();
	}

	@PUT
	@Path("{dni}")
	@Produces("application/json")
	public Response cambiaEstado(@PathParam("dni") String dni) {
		if (monitorJPA.cambiaEstado(dni) == true)
			return Response.status(Response.Status.ACCEPTED).build();
		else
			return Response.status(Response.Status.NOT_FOUND).build();

	}
}
