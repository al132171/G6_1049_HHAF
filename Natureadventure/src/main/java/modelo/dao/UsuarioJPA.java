package modelo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import modelo.datos.Reserva;
import modelo.datos.Usuario;

@Stateless
public class UsuarioJPA {
	public static Usuario ENTRADA_NULL = new Usuario();
	@PersistenceContext(unitName = "natureadventureJTA")
	EntityManager em;

	public Usuario perfilUsuario(String username) {
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.perfil", Usuario.class);
		query.setParameter("username", username);	        
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return ENTRADA_NULL;
		}
	}

	public boolean actualizaUsuario(Usuario usuario) {
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.perfil", Usuario.class);
		query.setParameter("username", usuario.getUsername());
		try {
			Usuario usuarioBBDD = query.getSingleResult();
			usuarioBBDD.setNombre(usuario.getNombre());
			usuarioBBDD.setApellidos(usuario.getApellidos());
			usuarioBBDD.setEmail(usuario.getEmail());
			usuarioBBDD.setEspecialidad(usuario.getEspecialidad());
			usuarioBBDD.setPassword(usuario.getPassword());
			usuarioBBDD.setTelefono(usuario.getTelefono());
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

	public Usuario[] buscaMonitorDisponible(String especialidad, String fechaActividad) {
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.buscaMonitorDisponible", Usuario.class);
		query.setParameter("especialidad", especialidad);
		List<Usuario> listaUsuarios = query.getResultList();
		//Añado los monitores que no tienen una reserva en esa fecha
		ArrayList<Usuario> aux = new ArrayList<Usuario>();
		for (Usuario u : listaUsuarios){	
			TypedQuery<Reserva> query2 = em.createNamedQuery("Reserva.encuentraTodasMonitorFechaActividad", Reserva.class);
			query2.setParameter("username", u.getUsername());
			query2.setParameter("fechaActividad", fechaActividad);
			if(query2.getResultList().isEmpty()){ // Si hay alguna reserva en esa fecha, quitamos al monitor
				System.out.println(" no tiene reserva");
				aux.add(u);
			}	
		}
		
		Usuario[] usuarios = new Usuario[aux.size()];
		aux.toArray(usuarios);
		return usuarios;
	}

	public Usuario buscaMonitorPorDni(String dni) {
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.buscaMonitorPorDni", Usuario.class);
		query.setParameter("dni", dni);	        
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return ENTRADA_NULL;
		}
	}

	//	##### GERENTE MONITORES ####
	public void nuevoMonitor(Usuario monitor) {
		em.persist(monitor);
	}


	public Usuario[] listaTodosMonitoresActivos() {
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.encuentraTodosActivos", Usuario.class);
		List<Usuario> listaMonitores = query.getResultList();
		Usuario[] monitores = new Usuario[listaMonitores.size()];
		listaMonitores.toArray(monitores);
		return monitores;
	}
	
	public Usuario[] listaTodosMonitoresNoDisponibles() {
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.encuentraTodosNoDisponibles", Usuario.class);
		List<Usuario> listaMonitores = query.getResultList();
		Usuario[] monitores = new Usuario[listaMonitores.size()];
		listaMonitores.toArray(monitores);
		return monitores;
	}

	public boolean creaNuevaEntrada(Usuario monitor) {
		em.persist(monitor);
		return true;
	}

	public boolean actualizaMonitor(Usuario monitor) {
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.buscaMonitorPorDni", Usuario.class);
		query.setParameter("dni", monitor.getDni());
		try {
			Usuario monitorBBDD = query.getSingleResult();
			monitorBBDD.setNombre(monitor.getNombre());
			monitorBBDD.setApellidos(monitor.getApellidos());
			monitorBBDD.setDni(monitor.getDni());
			monitorBBDD.setEmail(monitor.getEmail());
			monitorBBDD.setTelefono(monitor.getTelefono());
			monitorBBDD.setEspecialidad(monitor.getEspecialidad());
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

	public boolean cambiaEstado(String dni) {
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.buscaMonitorPorDni", Usuario.class);
		query.setParameter("dni", dni);
		try {
			Usuario monitorBBDD = query.getSingleResult();
			System.out.println(monitorBBDD.getEstado() + "   "+monitorBBDD.getDni());
			if(monitorBBDD.getEstado().equals("A")){
				monitorBBDD.setEstado("B");
			}
			else
				monitorBBDD.setEstado("A");
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
}