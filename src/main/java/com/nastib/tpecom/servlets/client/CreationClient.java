package com.nastib.tpecom.servlets.client;

import com.nastib.tpecom.beans.Client;
import com.nastib.tpecom.forms.ClientForm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreationClient extends HttpServlet {

    public static final String VUE_AFFICHE = "/listeclient";
    public static final String VUE_CREATION = "/WEB-INF/creerClient.jsp";
    
    public static final String ATT_CLIENT = "client";
    public static final String ATT_FORM = "form";

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE_CREATION).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        ClientForm form = new ClientForm();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Client client = form.updateClient(request);
		
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_CLIENT, client );
        
        if(form.getErreurs().isEmpty()){
            response.sendRedirect(request.getContextPath() + VUE_AFFICHE);
            //this.getServletContext().getRequestDispatcher(VUE_AFFICHE).forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher(VUE_CREATION).forward(request, response);
        }
        
    }
}
