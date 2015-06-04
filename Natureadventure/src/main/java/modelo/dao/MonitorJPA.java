package modelo.dao;

/**
 * @author appujimatica
 * JPA que gestiona los monitores de la plataforma
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import modelo.datos.Reserva;

@Stateless
public class MonitorJPA {

	public static Reserva ENTRADA_NULL = new Reserva();
	@PersistenceContext(unitName = "natureadventureJTA")
	EntityManager em;

	public Reserva[] listaTodasReservasSupervisar(String username) {
		TypedQuery<Reserva> query = em.createNamedQuery("Reserva.encuentraTodas", Reserva.class);
		query.setParameter("username", username);
		List<Reserva> listaReservas = query.getResultList();
		listaReservas = reservasPasadas(listaReservas, "supervisar");
		Reserva[] reservas = new Reserva[listaReservas.size()];
		listaReservas.toArray(reservas);
		return reservas;
	}

	public Reserva[] listaTodasReservasPasadas(String username) {
		TypedQuery<Reserva> query = em.createNamedQuery("Reserva.encuentraTodas", Reserva.class);
		query.setParameter("username", username);
		List<Reserva> listaReservas = query.getResultList();
		listaReservas = reservasPasadas(listaReservas, "pasadas");
		Reserva[] reservas = new Reserva[listaReservas.size()];
		listaReservas.toArray(reservas);
		return reservas;
	}

	private List<Reserva> reservasPasadas(List<Reserva> reservas, String tipo){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH)+1);
		String annio = Integer.toString(c.get(Calendar.YEAR));
		String fecha = dia+"-"+mes+"-"+annio;
		Date actual = null;
		ArrayList<Reserva> aux = new ArrayList<Reserva>();
		try {
			actual = formatter.parse(fecha);
			System.out.println(actual);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		for (Reserva r : reservas){
			String dateInString = r.getFechaActividad();
			try {
				Date date = formatter.parse(dateInString);
				System.out.println("**********************************************");
				System.out.println(date);
				System.out.println(formatter.format(date));
				switch(tipo){
				case "pasadas":
					if(date.after(actual) || date.equals(actual)){
						aux.add(r);
					}
					break;
				case "supervisar":
					if(date.before(actual)){
						aux.add(r);
					}
					break;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
	}
		reservas.removeAll(aux);
		return reservas;
	}


}
