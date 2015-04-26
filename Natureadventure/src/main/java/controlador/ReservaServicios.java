package controlador;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import modelo.dao.ReservaJPA;
import modelo.dao.UsuarioJPA;
import modelo.datos.Actividad;
import modelo.datos.Reserva;
import modelo.datos.Usuario;

@Path("gerente/reservas")
@Stateless
public class ReservaServicios {

	@Inject
	ReservaJPA reservaJPA;
	@Inject
	UsuarioJPA usuarioJPA;
	@Context
	private UriInfo uriInfo;

	public ReservaServicios() {
		super();
	}

	@GET
	@Path("pendientes")
	@Produces("application/json")
	public Response listaTodasReservasPendientes() {
		Reserva[] reservas = reservaJPA.listaTodasReservasPendientes();
		return Response.ok(reservas).build();
	}

	@GET
	@Path("aceptadas")
	@Produces("application/json")
	public Response listaTodasReservasAceptadas() {
		Reserva[] reservas = reservaJPA.listaTodasReservasAceptadas();
		return Response.ok(reservas).build();
	}

	@GET
	@Path("{dni}/{fechaReserva}")
	@Produces("application/json")
	public Response buscaReserva(@PathParam("dni") String dni, @PathParam("fechaReserva") String fechaReserva) {
		Reserva reserva = reservaJPA.buscaReserva(dni, fechaReserva);
		if (reserva == reservaJPA.ENTRADA_NULL){
			System.out.println(reserva.getNombre());
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(reserva).build();
	}

	@GET
	@Path("{especialidad}")
	@Produces("application/json")
	public Response buscarMonitorDisponible(@PathParam("especialidad") String especialidad) {
		Usuario[] usuarios = usuarioJPA.buscaMonitorDisponible(especialidad);
		if (usuarios.length == 0)
			return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(usuarios).build();
	}

	@PUT
	@Path("{dniMonitor}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizaActividad(@PathParam("dniMonitor") String dniMonitor, Reserva reserva) {
		Usuario u = usuarioJPA.buscaMonitorPorDni(dniMonitor);
		reserva.setUsuario(u);
		reservaJPA.actualizaReserva(reserva);
		return Response.status(Response.Status.NO_CONTENT).build();                


	}

	@DELETE
	@Path("{dni}/{fechaReserva}")
	@Produces("application/json")
	public Response borraEntrada(@PathParam("dni") String dni, @PathParam("fechaReserva") String fechaReserva) {
		if (reservaJPA.borraReserva(dni, fechaReserva) == true)
			return Response.status(Response.Status.ACCEPTED).build();
		else
			return Response.status(Response.Status.NOT_FOUND).build();

	}

}
