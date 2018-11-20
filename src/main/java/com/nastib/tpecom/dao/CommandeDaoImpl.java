
package com.nastib.tpecom.dao;

import com.nastib.tpecom.entities.Commande;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(mappedName="CommandeDao")
public class CommandeDaoImpl implements CommandeDao {
    
    // Injection du manager, qui s'occupe de la connexion avec la BDD
    @PersistenceContext( unitName = "bdd_tpecom_PU" )
    private EntityManager       em;
    
    @Override
    public Commande trouver( long id ) throws DaoException {
        try {
            return em.find( Commande.class, id );
        } catch ( Exception e ) {
            throw new DaoException( e.getMessage() );
        }
    }

    @Override
    public void creer( Commande commande ) throws DaoException {
        try {
            em.persist( commande );
        } catch ( Exception e ) {
            throw new DaoException( e.getMessage() );
        }
    }

    /* Implémentation de la méthode définie dans l'interface ClientDao */
    @Override
    public void modifier( Commande commande ) throws DaoException {
        try {
            Query query = em.createNamedQuery("Commande.update")                                              
                .setParameter("date", commande.getDate())
                .setParameter("montant", commande.getMontant())
                .setParameter("client", commande.getClient())
                .setParameter("modeliv", commande.getModeLivraison())
                .setParameter("statutliv", commande.getStatutLivraison())
                .setParameter("modepaie", commande.getModePaiement())
                .setParameter("statutpaie", commande.getStatutPaiement())
                .setParameter("id", commande.getId());
            query.executeUpdate();
      
        } catch ( Exception e ) {
            throw new DaoException( e.getMessage() );
        }
    }  
      
    @Override
    public List<Commande> lister() throws DaoException {
        try {
            Query query = em.createNamedQuery( "Commande.selectAll" );
            return (List<Commande>) query.getResultList();
        } catch ( Exception e ) {
            throw new DaoException( e.getMessage() );
        }
    }

    @Override
    public void supprimer( Commande commande ) throws DaoException {
        try {
            em.remove( em.merge( commande ) );
        } catch ( Exception e ) {
            throw new DaoException( e );
        }
    }

}