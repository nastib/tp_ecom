
package com.nastib.tpecom.dao;

import com.nastib.tpecom.entities.Utilisateur;
import javax.ejb.Remote;

@Remote
public interface UtilisateurDao {
    
    public void creer( Utilisateur utilisateur ) throws DaoException;
    public Utilisateur trouver( String email ) throws DaoException;
    
}
