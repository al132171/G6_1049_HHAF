package modelo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import modelo.datos.Actividad;
import modelo.datos.Reserva;
import modelo.datos.Usuario;

@Stateless
public class ReservaJPA {
	public static Reserva ENTRADA_NULL = new Reserva();
	@PersistenceContext(unitName = "natureadventureJTA")
	EntityManager em;
	
	public void nuevaReserva(Reserva reserva) {
    	reserva.setEstado("P"); 
        em.persist(reserva);
    }

	public Reserva[] listaTodasReservasPendientes() {
		TypedQuery<Reserva> query = em.createNamedQuery("Reserva.encuentraTodasPendientes", Reserva.class);
		List<Reserva> listaReservas = query.getResultList();
		Reserva[] reservas = new Reserva[listaReservas.size()];
		listaReservas.toArray(reservas);
		return reservas;
	}

	public Reserva[] listaTodasReservasAceptadas() {
		TypedQuery<Reserva> query = em.createNamedQuery("Reserva.encuentraTodasAceptadas", Reserva.class);
		List<Reserva> listaReservas = query.getResultList();
		Reserva[] reservas = new Reserva[listaReservas.size()];
		listaReservas.toArray(reservas);
		return reservas;
	}

	public Reserva buscaReserva(String dni, String fechaReserva) {
		TypedQuery<Reserva> query = em.createNamedQuery("Reserva.encuentraReserva", Reserva.class);
		query.setParameter("dni", dni);
		query.setParameter("fechaReserva", fechaReserva);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return ENTRADA_NULL;
		}
	}

	 public boolean actualizaReserva(Reserva reserva) {
	        TypedQuery<Reserva> query = em.createNamedQuery("Reserva.encuentraReserva", Reserva.class);
	        query.setParameter("fechaReserva", reserva.getFechaReserva());
	        query.setParameter("dni", reserva.getDni());
	        try {
	            Reserva reservaBBDD = query.getSingleResult();
	            reservaBBDD.setEstado("A");
	            reservaBBDD.setUsuario(reserva.getUsuario());
	            reservaBBDD.setContrato(reserva.getContrato());
	            return true;
	        } catch (NoResultException e) {
	            return false;
	        }
	    }
	
	public boolean cambiaEstado(String dni, String fechaReserva) {
        TypedQuery<Reserva> query = em.createNamedQuery("Reserva.borrarReserva", Reserva.class);
        query.setParameter("dni", dni);
        query.setParameter("fechaReserva", fechaReserva);
        try {
            int deletedRows = query.executeUpdate();
           
            if(deletedRows == 1) return true;
            else return false;
        } catch (NoResultException e) {
            return false;
        }
	}

	
}
