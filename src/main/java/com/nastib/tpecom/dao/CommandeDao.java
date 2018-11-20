
package com.nastib.tpecom.dao;

import com.nastib.tpecom.entities.Commande;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface CommandeDao {
    
    public Commande trouver( long id ) throws DaoException;
    public void creer( Commande commande ) throws DaoException;
    public void modifier( Commande commande ) throws DaoException;
    public List<Commande> lister() throws DaoException;
    public void supprimer( Commande commande ) throws DaoException;
}
