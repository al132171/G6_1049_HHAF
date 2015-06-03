package controlador;

import java.net.URI;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import controlador.utilidades.EnviaNoticia;
import modelo.datos.Noticia;
import modelo.datos.UsuarioSuscritoNoticias;
import modelo.dao.NoticiaJPA;
import modelo.dao.UsuarioSuscritoNoticiasJPA;

@Path("gerente/noticias")
@Stateless
public class NoticiaServicios {
	@Inject
    NoticiaJPA noticiaJPA;
	@Inject
	EnviaNoticia enviaNoticia;
	@Inject
	UsuarioSuscritoNoticiasJPA usnJPA;
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
    
    /*
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response nuevaEntradaDesdeFormulario(
            @FormParam("id") Long id,
            @FormParam("user") String user,
            @FormParam("fecha") String fecha,
            @FormParam("titulo") String titulo,
            @FormParam("subtitulo") String subtitulo,
            @FormParam("descripcion") String descripcion) {
        if (noticiaJPA.buscaNoticiaPorId(id) == NoticiaJPA.ENTRADA_NULL) {
        	Noticia noticia = new Noticia(id, user, fecha, titulo, subtitulo, descripcion);
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
    */
    
    @PUT
    @Path("{user}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response creaNuevaEntrada(@PathParam("user") String user, Noticia noticia) {
        noticiaJPA.nuevaNoticia(noticia);
        
        UsuarioSuscritoNoticias[] suscritos = usnJPA.listaTodosSuscritos();
        String[] emailSuscritos = new String[suscritos.length];
        
        for (int i = 0; i < suscritos.length; i++) {
			emailSuscritos[i] = suscritos[i].getEmail();
		}
        
        try {
			enviaNoticia.generateAndSendEmail(noticia, emailSuscritos);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
        return Response.ok(noticia).build();
    }
    
    @PUT
	@Path("actualizar/{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizaNoticia(@PathParam("id") Integer id, Noticia noticia) {
		noticiaJPA.actualizaNoticia(noticia);
		
		UsuarioSuscritoNoticias[] suscritos = usnJPA.listaTodosSuscritos();
        String[] emailSuscritos = new String[suscritos.length];
        
        for (int i = 0; i < suscritos.length; i++) {
			emailSuscritos[i] = suscritos[i].getEmail();
		}
        
        try {
			enviaNoticia.generateAndSendEmail(noticia, emailSuscritos);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
