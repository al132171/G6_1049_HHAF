package controlador;

/**
 * @author appujimatica
 * Servicio de monitores para que use el gerente de la plataforma
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

import modelo.dao.MonitorJPA;
import modelo.datos.Reserva;

@Path("monitor/reservas")
@Stateless
public class MonitorAdminServicios {

	@Inject
    MonitorJPA monitorJPA;
    @Context
    private UriInfo uriInfo;

    public MonitorAdminServicios() {
        super();
    }
    
    @GET
    @Path("supervisar/{username}")
    @Produces("application/json")
    public Response listaTodasReservasSupervisar(@PathParam("username") String username) {
        Reserva[] reservas = monitorJPA.listaTodasReservasSupervisar(username);
        return Response.ok(reservas).build();
    }

    @GET
    @Path("pasadas/{username}")
    @Produces("application/json")
    public Response listaTodasReservasPasadas(@PathParam("username") String username) {
    	Reserva[] reservas = monitorJPA.listaTodasReservasPasadas(username);
        return Response.ok(reservas).build();
    }


}
