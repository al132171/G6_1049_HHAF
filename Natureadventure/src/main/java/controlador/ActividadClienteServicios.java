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
@Path("cliente/actividades")
@Stateless
public class ActividadClienteServicios {
    @Inject
    ActividadJPA actividadJPA;
    @Context
    private UriInfo uriInfo;

    public ActividadClienteServicios() {
        super();
    }

    @GET
    @Path("{nombre}")
    @Produces("application/json")
    public Response buscarActividadPorNombreActiva(@PathParam("nombre") String nombre) {
        Actividad actividad = actividadJPA.buscaActividadPorNombreActiva(nombre);
        
        if (actividad == actividadJPA.ENTRADA_NULL)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(actividad).build();
    }
    
    @GET
    @Path("palabraClave/{palabraClave}")
    @Produces("application/json")
    public Response buscarActividadPorPalabraClave(@PathParam("palabraClave") String palabraClave) {
    	Actividad[] actividades = actividadJPA.buscaActividadPorPalabraClave(palabraClave);
        return Response.ok(actividades).build();
    }
    
    @GET
    @Path("categoria/{categoria}")
    @Produces("application/json")
    public Response buscarActividadPorCategoria(@PathParam("categoria") String categoria) {
    	Actividad[] actividades = actividadJPA.buscaActividadPorCategoria(categoria);
        return Response.ok(actividades).build();
    }
    
}
