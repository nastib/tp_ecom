
package com.nastib.tpecom.servlets.client;

import com.nastib.tpecom.beans.Client;
import com.nastib.tpecom.forms.ClientForm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AfficherClient extends HttpServlet {
    public static final String VUE = "/WEB-INF/afficherClient.jsp";
    public static final String ATT_CLIENT = "client";
    public static final String ATT_FORM = "form";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Préparation de l'objet formulaire */
        ClientForm form = new ClientForm();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Client client = form.rechercherClient(request);
		
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_CLIENT, client );  
        
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }


}
