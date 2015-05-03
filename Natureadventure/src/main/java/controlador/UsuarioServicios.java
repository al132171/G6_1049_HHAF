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

@Path("usuario/perfil")
@Stateless
public class UsuarioServicios {

	 @Inject
	    UsuarioJPA usuarioJPA;
	    @Context
	    private UriInfo uriInfo;

	    public UsuarioServicios() {
	        super();
	    }
	    
	    @GET
	    @Path("{username}")
	    @Produces("application/json")
	    public Response perfilUsuario(@PathParam("username") String username) {
	        Usuario usuario = usuarioJPA.perfilUsuario(username);
	        if (usuario == UsuarioJPA.ENTRADA_NULL){
	            return Response.status(Response.Status.NOT_FOUND).build();
	        }
	        return Response.ok(usuario).build();
	    }
	    
	    @PUT
	    @Path("{username}")
	    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response creaNuevaEntrada(@PathParam("username") String username, Usuario usuario) {
	        usuarioJPA.actualizaUsuario(usuario);
			return Response.status(Response.Status.NO_CONTENT).build();                
				
	}
}
