
package com.nastib.tpecom.dao;

import com.nastib.tpecom.entities.Utilisateur;

public interface UtilisateurDao {

    void creer( Utilisateur utilisateur ) throws DAOException;

    Utilisateur trouver( String email ) throws DAOException;

}