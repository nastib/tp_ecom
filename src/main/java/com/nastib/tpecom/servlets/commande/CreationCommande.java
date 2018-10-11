
package com.nastib.tpecom.servlets.commande;

import com.nastib.tpecom.beans.Commande;
import com.nastib.tpecom.forms.CommandeForm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreationCommande extends HttpServlet {

    public static final String VUE_AFFICHE = "/listecommande";
    public static final String VUE_CREATION = "/WEB-INF/creerCommande.jsp";
    
    public static final String ATT_COMMANDE = "commande";
    public static final String ATT_CLIENT = "client";
    public static final String ATT_FORM = "form";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE_CREATION).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
          CommandeForm form = new CommandeForm();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        form.saveCommande(request);
        Commande commande = form.getCommande();
        
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_COMMANDE, commande );
        request.setAttribute( ATT_CLIENT, commande.getClient() );
        
        if(form.getErreurs().isEmpty()){	
            //this.getServletContext().getRequestDispatcher(VUE_AFFICHE).forward(request, response);
            response.sendRedirect(request.getContextPath()+ VUE_AFFICHE);
        } else {
            this.getServletContext().getRequestDispatcher(VUE_CREATION).forward(request, response);
       }
    }
}
