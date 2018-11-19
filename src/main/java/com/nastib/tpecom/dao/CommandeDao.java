
package com.nastib.tpecom.dao;

import com.nastib.tpecom.entities.Commande;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CommandeDao {
    
    public Commande trouver( long id ) throws DAOException;
    public void creer( Commande commande ) throws DAOException;
    public void modifier( Commande commande ) throws DAOException;
    public List<Commande> lister() throws DAOException;
    public void supprimer( Commande commande ) throws DAOException;
}
