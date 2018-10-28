
package com.nastib.tpecom.dao;

import com.nastib.tpecom.beans.Utilisateur;

public interface UtilisateurDao {

    void creer( Utilisateur utilisateur ) throws DAOException;

    Utilisateur trouver( String email ) throws DAOException;

}