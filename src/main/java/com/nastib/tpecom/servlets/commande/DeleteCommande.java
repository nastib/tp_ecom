
package com.nastib.tpecom.servlets.commande;

import com.nastib.tpecom.forms.CommandeForm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCommande extends HttpServlet {

    public static final String VUE = "/WEB-INF/listeCommande.jsp";
    public static final String ATT_COMMANDE = "commande";
    public static final String ATT_FORM = "form";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Préparation de l'objet formulaire */
        CommandeForm form = new CommandeForm();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        form.deleteCommande(request);
        
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

}
