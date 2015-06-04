package controlador;

/**
 * @author ujimatica
 * Servicios de actividades para los clientes de la plataforma
 */

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

import modelo.dao.ActividadJPA;
import modelo.dao.ReservaJPA;
import modelo.datos.Actividad;
import modelo.datos.Reserva;

@Path("cliente/actividades")
@Stateless
public class ActividadClienteServicios {
	@Inject
	ActividadJPA actividadJPA;
	@Inject
	ReservaJPA reservaJPA;

	@Context
	private UriInfo uriInfo;

	public ActividadClienteServicios() {
		super();
	}

	@GET
	@Path("{nombre}")
	@Produces("application/json")
	public Response buscarActividadPorNombreActiva(@PathParam("nombre") String nombre) {
		Actividad actividad = actividadJPA.buscaActividadPorNombreActiva(nombre);

		if (actividad == ActividadJPA.ENTRADA_NULL)
			return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(actividad).build();
	}

	@GET
	@Path("ofertas")
	@Produces("application/json")
	public Response buscarOfertas() {
		Actividad[] actividades = actividadJPA.buscarOfertas();
		return Response.ok(actividades).build();
	}

	@GET
	@Path("novedades")
	@Produces("application/json")
	public Response buscarNovedades() {
		Actividad[] actividades = actividadJPA.buscarNovedades();
		return Response.ok(actividades).build();
	}

	@GET
	@Path("palabraClave/{palabraClave}")
	@Produces("application/json")
	public Response buscarActividadPorPalabraClave(@PathParam("palabraClave") String palabraClave) {
		Actividad[] actividades = actividadJPA.buscaActividadPorPalabraClave(palabraClave);
		return Response.ok(actividades).build();
	}

	@GET
	@Path("categoria/{categoria}")
	@Produces("application/json")
	public Response buscarActividadPorCategoria(@PathParam("categoria") String categoria) {
		Actividad[] actividades = actividadJPA.buscaActividadPorCategoria(categoria);
		return Response.ok(actividades).build();
	}

	@PUT
	@Path("reserva/{nombre}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_JSON)
	public Response creaNuevaEntrada(@PathParam("nombre") String nombre, Reserva reserva) {
		Actividad actividad = actividadJPA.buscaActividadPorNombre(nombre);
		reserva.setActividad(actividad);
		reserva.setUsuario(null);
		reservaJPA.nuevaReserva(reserva);
		return Response.ok(reserva).build();
	}

}
