package com.nastib.tpecom.dao;

import com.nastib.tpecom.entities.Client;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface ClientDao {
    public Client trouver( long id ) throws DaoException;
    public void creer( Client client ) throws DaoException;
    public void modifier( Client client ) throws DaoException;
    public List<Client> lister() throws DaoException;
    public void supprimer( Client client ) throws DaoException;
    
}
