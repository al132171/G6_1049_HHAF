package controlador;

import java.util.Calendar;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
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

import modelo.dao.ReservaJPA;
import modelo.dao.UsuarioJPA;
import modelo.datos.Reserva;
import modelo.datos.Usuario;
import controlador.utilidades.CrearFactura;
import controlador.utilidades.EnviaCorreo;

@Path("gerente/reservas")
@Stateless
public class ReservaServicios {

	@Inject
	ReservaJPA reservaJPA;
	@Inject
	UsuarioJPA usuarioJPA;
	@Inject
	EnviaCorreo enviaCorreo;
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
	@Path("reserva/{dni}/{fechaReserva}")
	@Produces("application/json")
	public Response buscaReserva(@PathParam("dni") String dni, @PathParam("fechaReserva") String fechaReserva) {
		Reserva reserva = reservaJPA.buscaReserva(dni, fechaReserva);

		if (reserva == ReservaJPA.ENTRADA_NULL){
			System.out.println(reserva.getNombre());
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(reserva).build();
	}

	@GET
	@Path("{especialidad}/{fechaActividad}")
	@Produces("application/json")
	public Response buscarMonitorDisponible(@PathParam("especialidad") String especialidad,
			@PathParam("fechaActividad") String fechaActividad) {
		Usuario[] usuarios = usuarioJPA.buscaMonitorDisponible(especialidad, fechaActividad);

		if (usuarios.length == 0)
			//return Response.status(Response.Status.NOT_FOUND).build();
			return Response.ok(usuarios).build();
		return Response.ok(usuarios).build();
	}

	@PUT
	@Path("{dniMonitor}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizaActividad(@PathParam("dniMonitor") String dniMonitor, Reserva reserva) {
		System.out.println("entro actualiza");
		//		CREAR CONTRATO
		Usuario u = usuarioJPA.buscaMonitorPorDni(dniMonitor);		
		reserva.setUsuario(u);
		//		Genero un ID para el contrato
		Calendar calendario = Calendar.getInstance();
		String idContrato = u.getDni() + calendario.getTimeInMillis();
		CrearFactura.createContrato(reserva, idContrato, u);

		//		ENVIAR CORREO
		//		try {
		//			enviaCorreo.generateAndSendEmail();
		//		} catch (AddressException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (MessagingException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		reserva.setContrato(idContrato);
		reservaJPA.actualizaReserva(reserva);
		System.out.println("acabo actualiza");		
		return Response.status(Response.Status.NO_CONTENT).build();                
	}

	@PUT
	@Path("{dni}/{fechaReserva}")
	@Produces("application/json")
	public Response cambiaEstado(@PathParam("dni") String dni, @PathParam("fechaReserva") String fechaReserva) {
		if (reservaJPA.cambiaEstado(dni, fechaReserva) == true)
			return Response.status(Response.Status.ACCEPTED).build();
		else
			return Response.status(Response.Status.NOT_FOUND).build();

	}

}
