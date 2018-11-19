
package com.nastib.tpecom.dao;

import com.nastib.tpecom.entities.Client;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class ClientDao {
    
    // Injection du manager, qui s'occupe de la connexion avec la BDD
    @PersistenceContext( unitName = "bdd_tpecom_PU" )
    private EntityManager       em;
    
     /* Implémentation de la méthode définie dans l'interface ClientDao */
    public Client trouver( long id ) throws DAOException {
                
        try {
            return em.find( Client.class, id );
        } catch ( Exception e ) {
            throw new DAOException( e.getMessage() );
        }

    }

    /* Implémentation de la méthode définie dans l'interface ClientDao */
    public void creer( Client client ) throws DAOException {
        try {
            em.persist( client );
        } catch ( Exception e ) {
            throw new DAOException( e.getMessage() );
        }
    }

    /* Implémentation de la méthode définie dans l'interface ClientDao */
    public void modifier( Client client ) throws DAOException {
        try {
            em.persist( client );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
    
    public List<Client> lister() throws DAOException {
        try {
            TypedQuery<Client> query = em.createQuery( "SELECT c FROM Client c ORDER BY c.id", Client.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    public void supprimer( Client client ) throws DAOException {
        try {
            em.remove( em.merge( client ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }


}