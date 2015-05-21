package modelo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.datos.Noticia;

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
    
    public boolean actualizaNoticia(Noticia noticia) {
        TypedQuery<Noticia> query = em.createNamedQuery("Noticia.encuentraPorId", Noticia.class);
        query.setParameter("id", noticia.getId());
        try {
            Noticia noticiaBBDD = query.getSingleResult();
            noticiaBBDD.setFecha(noticia.getFecha());
            noticiaBBDD.setTitulo(noticia.getTitulo());
            noticiaBBDD.setSubtitulo((noticia.getSubtitulo()));
            noticiaBBDD.setDescripcion(noticia.getDescripcion());
            noticiaBBDD.setId(noticia.getId());
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
    
    public boolean borraNoticia(Long id) {
        TypedQuery<Noticia> query = em.createNamedQuery("Noticia.borraPorId", Noticia.class);
        query.setParameter("id", id);
        try {
            int deletedRows = query.executeUpdate();
            if(deletedRows == 1) return true;
            else return false;
        } catch (NoResultException e) {
            return false;
        }
    }

}
