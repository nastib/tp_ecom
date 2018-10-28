
package com.nastib.tpecom.dao;

import com.nastib.tpecom.beans.Client;
import java.util.List;

public interface ClientDao {

    void creer( Client client ) throws DAOException;
    
    void modifier( Client client ) throws DAOException;

    Client trouver( long id ) throws DAOException;
    
    List<Client> lister() throws DAOException;
    
    void supprimer (Client client) throws DAOException;

}