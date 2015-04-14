package modelo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import modelo.datos.Actividad;


@Stateless
public class ActividadJPA {
    public static Actividad ENTRADA_NULL = new Actividad();
    @PersistenceContext(unitName = "natureadventureJTA")
    EntityManager em;

    public void nuevaActividad(Actividad actividad) {
        em.persist(actividad);
    }

    public Actividad buscaActividadPorNombre(String nombre) {
        TypedQuery<Actividad> query = em.createNamedQuery("Actividad.encuentraPorNombre", Actividad.class);
        query.setParameter("nombre", nombre);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return ENTRADA_NULL;
        }
    }

    public Actividad[] listaTodasActividades() {
        TypedQuery<Actividad> query = em.createNamedQuery("Actividad.encuentraTodas", Actividad.class);
        List<Actividad> listaActividades = query.getResultList();
        Actividad[] actividades = new Actividad[listaActividades.size()];
        listaActividades.toArray(actividades);
        return actividades;
    }

    public boolean creaNuevaEntrada(Actividad actividad) {
        em.persist(actividad);
        return true;
    }

    public boolean actualizaActividad(Actividad actividad) {
        TypedQuery<Actividad> query = em.createNamedQuery("Actividad.encuentraPorNombre", Actividad.class);
        query.setParameter("nombre", actividad.getNombre());
        try {
            Actividad actividadBBDD = query.getSingleResult();
            actividadBBDD.setNombre(actividad.getNombre());
            actividadBBDD.setDuracion(actividad.getDuracion());
            actividadBBDD.setDescripcion(actividad.getDescripcion());
            actividadBBDD.setFechaFin(actividad.getFechaFin());
            actividadBBDD.setFechaInicio(actividad.getFechaInicio());
            actividadBBDD.setHoraInicio(actividad.getHoraInicio());
            actividadBBDD.setNivel(actividad.getNivel());
            actividadBBDD.setPrecio(actividad.getPrecio());
            actividadBBDD.setParticipantesMax(actividad.getParticipantesMax());
            actividadBBDD.setParticipantesMin(actividad.getParticipantesMin());
            actividadBBDD.setLugar(actividad.getLugar());
            actividadBBDD.setImagen(actividad.getImagen());
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public boolean borraActividad(String nombre) {
        TypedQuery<Actividad> query = em.createNamedQuery("Actividad.borraPorNombre", Actividad.class);
        query.setParameter("nombre", nombre);
        try {
            int deletedRows = query.executeUpdate();
            if(deletedRows == 1) return true;
            else return false;
        } catch (NoResultException e) {
            return false;
        }
    }
}
