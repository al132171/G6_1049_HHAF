package modelo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import modelo.datos.Actividad;

/**
 * Created by oscar on 30/01/15.
 */
@Stateless
public class PersonaJPA {
    public static Actividad ENTRADA_NULL = new Actividad();
    @PersistenceContext(unitName = "natureadventureJTA")
//    @PersistenceContext(unitName = "personasJTADerby")
    EntityManager em;

    public void nuevaPersona(Actividad persona) {
        em.persist(persona);
    }

    public Actividad buscaPersonaPorNombre(String nombre) {
        TypedQuery<Actividad> query = em.createNamedQuery("Actividad.encuentraPorNombre", Actividad.class);
        query.setParameter("nombre", nombre);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return ENTRADA_NULL;
        }
    }

    public Actividad[] listaTodasPersonas() {
        TypedQuery<Actividad> query = em.createNamedQuery("Actividad.encuentraTodas", Actividad.class);
        List<Actividad> listaPersonas = query.getResultList();
        Actividad[] personas = new Actividad[listaPersonas.size()];
        listaPersonas.toArray(personas);
        //System.out.println("********************************************************"+personas.length);
        return personas;
    }

    public boolean creaNuevaEntrada(Actividad persona) {
        em.persist(persona);
        return true;
    }

    public boolean actualizaPersona(Actividad persona) {
        TypedQuery<Actividad> query = em.createNamedQuery("Actividad.encuentraPorNombre", Actividad.class);
        query.setParameter("nombre", persona.getNombre());
        try {
            Actividad personaBBDD = query.getSingleResult();
            personaBBDD.setNombre(persona.getNombre());
            personaBBDD.setDuracion(persona.getDuracion());
            personaBBDD.setDescripcion(persona.getDescripcion());
            personaBBDD.setFechaFin(persona.getFechaFin());
            personaBBDD.setFechaInicio(persona.getFechaInicio());
            personaBBDD.setHoraInicio(persona.getHoraInicio());
            personaBBDD.setNivel(persona.getNivel());
            personaBBDD.setPrecio(persona.getPrecio());
            personaBBDD.setParticipantesMax(persona.getParticipantesMax());
            personaBBDD.setParticipantesMin(persona.getParticipantesMin());
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public boolean borraPersona(String nombre) {
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
