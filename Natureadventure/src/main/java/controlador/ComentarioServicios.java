package controlador;

/**
 * @author appujimatica
 * Servicios de comentarios para los clientes de la plataforma
 */

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

import modelo.dao.ComentarioJPA;
import modelo.datos.Comentario;


@Path("comentarios")
@Stateless
public class ComentarioServicios {
    @Inject
    ComentarioJPA ComentarioJPA;
    @Context
    private UriInfo uriInfo;

    public ComentarioServicios() {
        super();
    }
    
    @GET
    @Path("A/{idActividad}")
    @Produces("application/json")
    public Response buscaComentarioPorActividad(@PathParam("idActividad") Long idActividad) {
        Comentario[] comentario = ComentarioJPA.buscaComentarioPorActividad(idActividad);
        
        return Response.ok(comentario).build();
    }

//    @GET
//    @Path("U/{idUsuario}")
//    @Produces("application/json")
//    public Response buscaComentarioPorUsuario(@PathParam("idUsuario") Long idUsuario) {
//        Comentario[] comentario = ComentarioJPA.buscaComentarioPorActividad(idUsuario);
//        return Response.ok(comentario).build();
//    }


    @PUT
    @Path("{idActividad}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response creaNuevaEntrada(@PathParam("idActividad") Long idActividad, Comentario comentario) {
                ComentarioJPA.nuevoComentario(comentario);
                return Response.ok(comentario).build();
    }

    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public Response borraEntrada(@PathParam("id") Long id) {
            if (ComentarioJPA.borraComentario(id) == true)
                return Response.status(Response.Status.ACCEPTED).build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();
    }
}
