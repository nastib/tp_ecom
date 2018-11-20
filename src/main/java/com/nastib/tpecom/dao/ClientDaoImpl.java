
package com.nastib.tpecom.dao;

import com.nastib.tpecom.entities.Client;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(mappedName="ClientDao")
public class ClientDaoImpl implements ClientDao {
    
    // Injection du manager, qui s'occupe de la connexion avec la BDD
    @PersistenceContext( unitName = "bdd_tpecom_PU" )
    private EntityManager       em;
    
     /* Implémentation de la méthode définie dans l'interface ClientDaoImpl */
    @Override
    public Client trouver( long id ) throws DaoException {
                
        try {
            return em.find( Client.class, id );
        } catch ( Exception e ) {
            throw new DaoException( e.getMessage() );
        }

    }

    /* Implémentation de la méthode définie dans l'interface ClientDaoImpl */
    @Override
    public void creer( Client client ) throws DaoException {
        try {
            em.persist( client );
        } catch ( Exception e ) {
            throw new DaoException( e.getMessage() );
        }
    }

    /* Implémentation de la méthode définie dans l'interface ClientDaoImpl */
    @Override
    public void modifier( Client client ) throws DaoException {
        try {
            Query query = em.createNamedQuery("Client.update")                                               
                .setParameter("adresse", client.getAdresse())
                .setParameter("email", client.getEmail())
                .setParameter("nom", client.getNom())
                .setParameter("prenom", client.getPrenom())
                .setParameter("telephone", client.getTelephone())
                .setParameter("image", client.getImage())
                .setParameter("id", client.getId());
            query.executeUpdate();
      
        } catch ( Exception e ) {
            throw new DaoException( e.getMessage() );
        }
    }
  
    @Override
    public List<Client> lister() throws DaoException {
        try {
             Query query = em.createNamedQuery( "Client.selectAll" );           
             return  (List<Client>)  query.getResultList();
        } catch ( Exception e ) {
            throw new DaoException( e.getMessage() );
        }
    }

    @Override
    public void supprimer( Client client ) throws DaoException {
        try {
            em.remove( em.merge( client ) );
        } catch ( Exception e ) {
            throw new DaoException( e.getMessage() );
        }
    }

}