
package com.nastib.tpecom.servlets.client;

import com.nastib.tpecom.beans.Client;
import com.nastib.tpecom.forms.ClientForm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( urlPatterns = "/editionclient", initParams = @WebInitParam( name = "chemin", value = "/NetBeansProjects/JavaEE/tp_ecom/src/main/webapp/resources/fichiers/" ) )
@MultipartConfig( location = "c:/NetBeansProjects/JavaEE/tp_ecom/src/main/webapp/tmp", maxFileSize = 1024 * 1024, maxRequestSize = 5 * 1 * 1024 * 1024, fileSizeThreshold = 512 * 1024 )
public class EditionClient extends HttpServlet {
    public static final String VUE_AFFICHE = "/listeclient";
    public static final String VUE_EDITION = "/WEB-INF/editerClient.jsp";
    public static final String CHEMIN      = "chemin";
    public static final String ATT_CLIENT = "client";
    public static final String ATT_FORM = "form";
    public static final String ATT_NUMERO = "numero";
    private static final String ATT_ACTION = "Edition";
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Préparation de l'objet formulaire */
        ClientForm form = new ClientForm();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Client client = form.rechercherClient(request);
		
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_CLIENT, client );  
        request.setAttribute( "formAction", ATT_ACTION ); 
        
        this.getServletContext().getRequestDispatcher(VUE_EDITION).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         /*
         * Lecture du paramètre 'chemin' passé à la servlet via la déclaration
         * dans le web.xml
         */
        String chemin = this.getServletConfig().getInitParameter( CHEMIN );
        
        /* Préparation de l'objet formulaire */
        ClientForm form = new ClientForm();
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Client client = form.saveClient(request, chemin);
		
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_CLIENT, client );
        
        if(form.getErreurs().isEmpty()){
            response.sendRedirect(request.getContextPath() + VUE_AFFICHE);
            //this.getServletContext().getRequestDispatcher(VUE_AFFICHE).forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher(VUE_EDITION).forward(request, response);
        }
    }
}
