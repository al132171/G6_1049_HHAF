package controlador;

/**
 * @author appujimatica
 * Servicio para la suscripción de noticias de la plataforma
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

import modelo.dao.UsuarioSuscritoNoticiasJPA;
import modelo.datos.UsuarioSuscritoNoticias;


@Path("usuarioSuscritoNoticias")
@Stateless
public class UsuarioSuscritoNoticiasServicios {
	
	@Inject
	UsuarioSuscritoNoticiasJPA usnJPA;
	@Context
	private UriInfo uriInfo;
	
	public UsuarioSuscritoNoticiasServicios() {
		super();
	}
	
	@GET
	@Path("{email}")
	@Produces("application/json")
	public Response buscaSuscripcionEmail(@PathParam("email") String email) {
		UsuarioSuscritoNoticias usn = usnJPA.buscaSuscripcion(email);
		if(usn == UsuarioSuscritoNoticiasJPA.ENTRADA_NULL){
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(usn).build();
	}
	
	@PUT
	@Path("{email}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response suscripcion(@PathParam("email") String email, UsuarioSuscritoNoticias usn) {
		usnJPA.nuevaSuscripcion(usn);
		return Response.ok(usn).build();
	}
	
}
