package controlador;

import java.net.URI;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import modelo.dao.ActividadJPA;
import modelo.datos.Actividad;

/**
 * Created by oscar on 27/11/14.
 */
@Path("actividades")
@Stateless
public class ActividadServicios {
    @Inject
    ActividadJPA actividadJPA;
    @Context
    private UriInfo uriInfo;

    public ActividadServicios() {
        super();
    }

    @GET
    @Path("{nombre}")
    @Produces("application/json")
    public Response buscarActividadPorNombre(@PathParam("nombre") String nombre) {
        Actividad actividad = actividadJPA.buscaActividadPorNombre(nombre);
        
        if (actividad == actividadJPA.ENTRADA_NULL)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(actividad).build();
    }

    @GET
    @Produces("application/json")
    public Response listaTodasActividades() {
        Actividad[] actividades = actividadJPA.listaTodasActividades();
        return Response.ok(actividades).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response nuevaEntradaDesdeFormulario(
            @FormParam("nombre") String nombre,
            @FormParam("apellidos") @DefaultValue("") String apellidos
            ) {
        if (actividadJPA.buscaActividadPorNombre(nombre) == ActividadJPA.ENTRADA_NULL) {
            Actividad actividad = new Actividad();
            actividadJPA.nuevaActividad(actividad);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            URI uri = uriBuilder.path(nombre).build();
            return Response.created(uri).entity(actividad).build();
        } else
            return Response.status(Response.Status.CONFLICT).build();
    }

    @PUT
    @Path("{nombre}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response creaNuevaEntrada(@PathParam("nombre") String nombre, Actividad actividad) {
        if(!nombre.equals(actividad.getNombre())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            if (actividadJPA.actualizaActividad(actividad) == true){
					return Response.status(Response.Status.NO_CONTENT).build();                
			}
            else {
                actividadJPA.nuevaActividad(actividad);
                return Response.ok(actividad).build();
            }
        }
    }

    @DELETE
    @Path("{nombre}")
    @Produces("application/json")
    public Response borraEntrada(@PathParam("nombre") String nombre) {
        if (nombre != null) {
            if (actividadJPA.borraActividad(nombre) == true)
                return Response.status(Response.Status.ACCEPTED).build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();

        } else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
