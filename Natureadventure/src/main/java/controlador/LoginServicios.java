package controlador;

/**
 * @author appujimatica
 * Servicio de login para acceder al back-end de la plataforma
 */

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import modelo.dao.LoginJPA;
import modelo.datos.Usuario;

@Path("login")
@Stateless
public class LoginServicios {
    @Inject
    LoginJPA loginJPA;

    @Context
    private UriInfo uriInfo;

    public LoginServicios() {
        super();
    }

    @GET
    @Path("{username}/{password}")
    @Produces("application/json")
    public Response comprobarLogin(@PathParam("username") String username, @PathParam("password") String password) {
        Usuario usuario = loginJPA.buscaUsuario(username, password);
        if (usuario == LoginJPA.ENTRADA_NULL){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(usuario).build();
    }
}