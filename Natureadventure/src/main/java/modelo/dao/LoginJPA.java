package modelo.dao;

/**
 * @author appujimatica
 * JPA que gestiona el login de la plataforma
 */

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import modelo.datos.Usuario;

@Stateless
public class LoginJPA {
    public static Usuario ENTRADA_NULL = new Usuario();
    @PersistenceContext(unitName = "natureadventureJTA")
    EntityManager em;

    public Usuario buscaUsuario(String username, String password) {
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.login", Usuario.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return ENTRADA_NULL;
        }
    }

}
