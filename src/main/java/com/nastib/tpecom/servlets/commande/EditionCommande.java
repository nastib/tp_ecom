
package com.nastib.tpecom.servlets.commande;

import com.nastib.tpecom.beans.Commande;
import com.nastib.tpecom.forms.CommandeForm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditionCommande extends HttpServlet {
    public static final String VUE_AFFICHE = "/listecommande";
    public static final String VUE_EDITION = "/WEB-INF/editerCommande.jsp";
    
    public static final String ATT_COMMANDE = "commande";
    public static final String ATT_CLIENT = "client";
    public static final String ATT_FORM = "form";
    public static final String ATT_NUMERO = "numero";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Préparation de l'objet formulaire */
        CommandeForm form = new CommandeForm();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Commande commande = form.rechercherCommande(request);
		
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_COMMANDE, commande );  
        
        this.getServletContext().getRequestDispatcher(VUE_EDITION).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        CommandeForm form = new CommandeForm();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Commande commande = form.saveCommande(request);
		
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_COMMANDE, commande );
        request.setAttribute( ATT_CLIENT, commande.getClient() );
        
        if(form.getErreurs().isEmpty()){
            response.sendRedirect(request.getContextPath() + VUE_AFFICHE);
            //this.getServletContext().getRequestDispatcher(VUE_AFFICHE).forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher(VUE_EDITION).forward(request, response);
        }
    }
}
