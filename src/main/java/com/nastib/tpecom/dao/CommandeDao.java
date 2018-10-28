
package com.nastib.tpecom.dao;

import com.nastib.tpecom.beans.Commande;
import java.util.List;

public interface CommandeDao {

    void creer( Commande commande ) throws DAOException;
    
    void modifier( Commande commande ) throws DAOException;

    Commande trouver( long id ) throws DAOException;
    
    List<Commande> lister() throws DAOException;
    
    void supprimer (Commande commande) throws DAOException;

}