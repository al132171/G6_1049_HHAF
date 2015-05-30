package controlador;

import java.net.URI;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import modelo.datos.Noticia;
import modelo.datos.Usuario;
import modelo.dao.NoticiaJPA;

@Path("gerente/noticias")
@Stateless
public class NoticiaServicios {
	@Inject
    NoticiaJPA noticiaJPA;
    @Context
    private UriInfo uriInfo;
    
    public NoticiaServicios() {
    	super();
    }
    
    @GET
    @Produces("application/json")
    public Response listaTodasNoticias() {
        Noticia[] noticias = noticiaJPA.listaTodasNoticias();
        return Response.ok(noticias).build();
    }
    
    @GET
    @Path("/ultimas")
    @Produces("application/json")
    public Response listaUltimasNoticias() {
        Noticia[] noticias = noticiaJPA.listaUltimasNoticias();
        return Response.ok(noticias).build();
    }
    
    @GET
    @Path("{titulo}")
    @Produces("application/json")
    public Response recuperarNoticia(@PathParam("titulo") String titulo) {
        Noticia noticia = noticiaJPA.recuperarNoticia(titulo);
        System.out.println(noticia.toString());
        if(noticia == NoticiaJPA.ENTRADA_NULL)
        	return Response.status(Response.Status.NOT_FOUND).build();
    	return Response.ok(noticia).build();
    }
    
    @PUT
    @Path("{user}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response creaNuevaEntrada(@PathParam("user") String user, Noticia noticia) {
        noticiaJPA.nuevaNoticia(noticia);
        return Response.ok(noticia).build();
    }
    
    @PUT
	@Path("actualizar/{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizaNoticia(@PathParam("id") Integer id, Noticia noticia) {
		noticiaJPA.actualizaNoticia(noticia);
		return Response.ok(noticia).build();
	}
    
    
    @DELETE
    @Path("/{titulo}")
    @Produces("application/json")
    public Response borraEntrada(@PathParam("titulo") String titulo) {
        if (titulo != null) {
            if (noticiaJPA.borraNoticia(titulo) == true)
                return Response.status(Response.Status.ACCEPTED).build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();

        } else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
