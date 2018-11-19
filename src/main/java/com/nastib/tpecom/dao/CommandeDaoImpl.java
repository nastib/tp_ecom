
package com.nastib.tpecom.dao;

import com.nastib.tpecom.entities.Commande;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CommandeDaoImpl implements CommandeDao {
    
    private static final String SQL_SELECT        = "SELECT c FROM Commande c ORDER BY c.id";
    
    // Injection du manager, qui s'occupe de la connexion avec la BDD
    @PersistenceContext( unitName = "bdd_tpecom_PU" )
    private EntityManager       em;
    
    public Commande trouver( long id ) throws DAOException {
        try {
            return em.find( Commande.class, id );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    public void creer( Commande commande ) throws DAOException {
        try {
            em.persist( commande );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /* Implémentation de la méthode définie dans l'interface ClientDao */
    public void modifier( Commande commande ) throws DAOException {
        try {
            em.persist( commande );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }   
      
    public List<Commande> lister() throws DAOException {
        try {
            TypedQuery<Commande> query = em.createQuery( SQL_SELECT, Commande.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    public void supprimer( Commande commande ) throws DAOException {
        try {
            em.remove( em.merge( commande ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

}