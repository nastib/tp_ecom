
package com.nastib.tpecom.servlets.client;

import com.nastib.tpecom.beans.Client;
import com.nastib.tpecom.dao.ClientDao;
import com.nastib.tpecom.dao.DAOFactory;
import com.nastib.tpecom.forms.CreationClientForm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/afficherclient")
public class AfficherClient extends HttpServlet {
    
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUE = "/WEB-INF/afficherClient.jsp";
    public static final String ATT_CLIENT = "client";
    public static final String PARAM_ID_CLIENT = "idClient";
    
    private ClientDao clientDao;
    
    @Override
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        clientDao = ( (DAOFactory) this.getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getClientDao();
    } 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/* Récupération du paramètre */
        String idClient = request.getParameter(PARAM_ID_CLIENT);
        if (idClient != null && idClient.trim().length() > 0){
            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
            Client client = clientDao.trouver(Long.parseLong(idClient)) ;

            /* Stockage du formulaire et du bean dans l'objet request */
            request.setAttribute( ATT_CLIENT, client );  

            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }

}
