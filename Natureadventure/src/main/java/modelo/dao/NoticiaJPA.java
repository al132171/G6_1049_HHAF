package modelo.dao;

/**
 * @author appujimatica
 * JPA que gestiona las noticias de la plataforma
 */

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.datos.Noticia;
import modelo.datos.Usuario;

@Stateless
public class NoticiaJPA {
	public static Noticia ENTRADA_NULL = new Noticia();
    @PersistenceContext(unitName = "natureadventureJTA")
    EntityManager em;
    
    public void nuevaNoticia(Noticia noticia) {
        em.persist(noticia);
    }
    
    public Noticia buscaNoticiaPorId(Long id) {
        TypedQuery<Noticia> query = em.createNamedQuery("Noticia.encuentraPorId", Noticia.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return ENTRADA_NULL;
        }
    }
    
    public Noticia[] listaTodasNoticias() {
        TypedQuery<Noticia> query = em.createNamedQuery("Noticia.encuentraTodas", Noticia.class);
        List<Noticia> listaNoticias = query.getResultList();
        Noticia[] noticias = new Noticia[listaNoticias.size()];
        listaNoticias.toArray(noticias);
        return noticias;
    }
    
    public Noticia[] listaUltimasNoticias() {
    	int cantidadNoticias = 6;
        TypedQuery<Noticia> query = em.createNamedQuery("Noticia.encuentraRecientes", Noticia.class);
        query.setMaxResults(cantidadNoticias);
        List<Noticia> listaNoticias = query.getResultList();
        Noticia[] noticias = new Noticia[listaNoticias.size()];
        listaNoticias.toArray(noticias);
        return noticias;
    }
    
    public boolean actualizaNoticia(Noticia noticia) {
        TypedQuery<Noticia> query = em.createNamedQuery("Noticia.encuentraPorId", Noticia.class);
        query.setParameter("id", noticia.getId());
        try {
            Noticia noticiaBBDD = query.getSingleResult();
            noticiaBBDD.setUser(noticia.getUser());
            noticiaBBDD.setFecha(noticia.getFecha());
            noticiaBBDD.setTitulo(noticia.getTitulo());
            noticiaBBDD.setSubtitulo((noticia.getSubtitulo()));
            noticiaBBDD.setDescripcion(noticia.getDescripcion());
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
    
    public boolean borraNoticia(String titulo) {
        TypedQuery<Noticia> query = em.createNamedQuery("Noticia.borraPorTitulo", Noticia.class);
        query.setParameter("titulo", titulo);
        try {
            int deletedRows = query.executeUpdate();
            if(deletedRows == 1) return true;
            else return false;
        } catch (NoResultException e) {
            return false;
        }
    }

	public Noticia recuperarNoticia(String titulo) {
		TypedQuery<Noticia> query = em.createNamedQuery("Noticia.encuentraPorTitulo", Noticia.class);
		query.setParameter("titulo", titulo);	        
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return ENTRADA_NULL;
		}
	}

}
