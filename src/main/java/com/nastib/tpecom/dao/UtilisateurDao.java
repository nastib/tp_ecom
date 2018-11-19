
package com.nastib.tpecom.dao;

import com.nastib.tpecom.entities.Utilisateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UtilisateurDao  {
    private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM Utilisateur u WHERE u.email = :email";
    private static final String PARAM_EMAIL           = "email";

    // Injection du manager, qui s'occupe de la connexion avec la BDD
    @PersistenceContext( unitName = "bdd_tpecom_PU" )
    private EntityManager       em;

    // Enregistrement d'un nouvel utilisateur  
    public void creer( Utilisateur utilisateur ) throws DAOException {
        try {
            em.persist( utilisateur );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    // Recherche d'un utilisateur à partir de son adresse email
    public Utilisateur trouver( String email ) throws DAOException {
       
        try {
            TypedQuery<Utilisateur> query = em.createQuery( JPQL_SELECT_PAR_EMAIL ,Utilisateur.class);
            query.setParameter( PARAM_EMAIL, email );
            return query.getSingleResult();  
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
}