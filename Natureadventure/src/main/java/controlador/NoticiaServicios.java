package controlador;

import java.net.URI;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import modelo.datos.Noticia;
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
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response nuevaEntradaDesdeFormulario(
            @FormParam("id") Long id,
            @FormParam("fecha") String fecha,
            @FormParam("titulo") String titulo,
            @FormParam("subtitulo") String subtitulo,
            @FormParam("descripcion") String descripcion) {
        if (noticiaJPA.buscaNoticiaPorId(id) == NoticiaJPA.ENTRADA_NULL) {
            Noticia noticia = new Noticia(id, fecha, titulo, subtitulo, descripcion);
            noticiaJPA.nuevaNoticia(noticia);;
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            URI uri = uriBuilder.path(Long.toString(id)).build();
            return Response.created(uri).entity(noticia).build();
        } else
            return Response.status(Response.Status.CONFLICT).build();
    }
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response creaNuevaEntrada(@PathParam("id") Long id, Noticia noticia) {
        if(!id.equals(noticia.getId())) {
            System.out.println(id);
            System.out.println(noticia.getFecha());
            System.out.println(noticia.getTitulo());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            if (noticiaJPA.actualizaNoticia(noticia) == true)
                return Response.status(Response.Status.NO_CONTENT).build();
            else {
                noticiaJPA.nuevaNoticia(noticia);
                return Response.ok(noticia).build();
            }
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response borraEntrada(@PathParam("id") Long id) {
        if (id != null) {
            if (noticiaJPA.borraNoticia(id) == true)
                return Response.status(Response.Status.ACCEPTED).build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();

        } else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
