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
    	actividad.setEstado("T"); //Siempre esta dada de alta cuando se añade
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
    
    // Método de ActividadClienteServicios
    public Actividad buscaActividadPorNombreActiva(String nombre) {
        TypedQuery<Actividad> query = em.createNamedQuery("Actividad.encuentraPorNombreActiva", Actividad.class);
        query.setParameter("nombre", nombre);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return ENTRADA_NULL;
        }
    }
    
    // Método de ActividadClienteServicios
    public Actividad[] buscaActividadPorPalabraClave(String palabraClave) {
        TypedQuery<Actividad> query = em.createNamedQuery("Actividad.encuentraPorPalabraClave", Actividad.class);
        query.setParameter("palabraClave", "%" + palabraClave + "%");
        List<Actividad> listaActividades = query.getResultList();
        Actividad[] actividades = new Actividad[listaActividades.size()];
        listaActividades.toArray(actividades);
        return actividades;
    }
    
    // Método de ActividadClienteServicios
    public Actividad[] buscaActividadPorCategoria(String categoria) {
        TypedQuery<Actividad> query = em.createNamedQuery("Actividad.encuentraPorCategoria", Actividad.class);
        query.setParameter("categoria", categoria);
        List<Actividad> listaActividades = query.getResultList();
        Actividad[] actividades = new Actividad[listaActividades.size()];
        listaActividades.toArray(actividades);
        return actividades;
    }

    public Actividad[] listaTodasActividadesActivas() {
        TypedQuery<Actividad> query = em.createNamedQuery("Actividad.encuentraTodasActivas", Actividad.class);
        List<Actividad> listaActividades = query.getResultList();
        Actividad[] actividades = new Actividad[listaActividades.size()];
        listaActividades.toArray(actividades);
        return actividades;
    }
    
    public Actividad[] listaTodasActividadesArchivadas() {
        TypedQuery<Actividad> query = em.createNamedQuery("Actividad.encuentraTodasArchivadas", Actividad.class);
        List<Actividad> listaActividades = query.getResultList();
        Actividad[] actividades = new Actividad[listaActividades.size()];
        listaActividades.toArray(actividades);
        return actividades;
    }

    public boolean creaNuevaEntrada(Actividad actividad) {
    	actividad.setEstado("T"); //Siempre esta dada de alta cuando se añade
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
            actividadBBDD.setCategoria(actividad.getCategoria());
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public boolean cambiaEstado(String nombre) {
        TypedQuery<Actividad> query = em.createNamedQuery("Actividad.encuentraPorNombre", Actividad.class);
        query.setParameter("nombre", nombre);
        try {
        Actividad actividadBBDD = query.getSingleResult();
        if(actividadBBDD.getEstado().equals("T")){
        	actividadBBDD.setEstado("F");
        }
        else{
        	actividadBBDD.setEstado("T");
        }
        return true;
        } catch (NoResultException e) {
            return false;
        }
//        try {
//            int deletedRows = query.executeUpdate();
//            if(deletedRows == 1) return true;
//            else return false;
//        } catch (NoResultException e) {
//            return false;
//        }
    }

	
}
