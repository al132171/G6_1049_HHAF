package modelo.dao;

/**
 * @author appujimatica
 * JPA que gestiona las suscripciones de noticias de la plataforma
 */

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import modelo.datos.UsuarioSuscritoNoticias;

@Stateless
public class UsuarioSuscritoNoticiasJPA {
	public static UsuarioSuscritoNoticias ENTRADA_NULL = new UsuarioSuscritoNoticias();
	@PersistenceContext(unitName = "natureadventureJTA")
	EntityManager em;
	
	public void nuevaSuscripcion( UsuarioSuscritoNoticias usn) {
		em.persist(usn);
	}
	
	public UsuarioSuscritoNoticias buscaSuscripcion(String email) {
		TypedQuery<UsuarioSuscritoNoticias> query = em.createNamedQuery("UsuarioSuscritoNoticias.encuentraSuscripcion", UsuarioSuscritoNoticias.class);
		query.setParameter("email", email);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return ENTRADA_NULL;
		}
	}
	
	public UsuarioSuscritoNoticias[] listaTodosSuscritos() {
		TypedQuery<UsuarioSuscritoNoticias> query = em.createNamedQuery("UsuarioSuscritoNoticias.encuentraTodos", UsuarioSuscritoNoticias.class);
		List<UsuarioSuscritoNoticias> listaSuscritos = query.getResultList();
		UsuarioSuscritoNoticias[] usuariosSuscritos = new UsuarioSuscritoNoticias[listaSuscritos.size()];
		listaSuscritos.toArray(usuariosSuscritos);
		return usuariosSuscritos;
	}
	
}
