
package com.nastib.tpecom.servlets.client;

import com.nastib.tpecom.entities.Client;
import com.nastib.tpecom.dao.ClientDao;
import com.nastib.tpecom.dao.DAOFactory;
import com.nastib.tpecom.forms.EditionClientForm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet( urlPatterns = "/editionclient", initParams = @WebInitParam( name = "chemin", value = "/NetBeansProjects/JavaEE/tp_ecom/src/main/webapp/resources/fichiers/images/" ) )
@MultipartConfig( location = "c:/NetBeansProjects/JavaEE/tp_ecom/src/main/webapp/tmp", maxFileSize = 1024 * 1024, maxRequestSize = 5 * 1 * 1024 * 1024, fileSizeThreshold = 512 * 1024 )
public class EditionClient extends HttpServlet {
    
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUE_AFFICHE = "/listeclients";
    public static final String VUE_EDITION = "/WEB-INF/editerClient.jsp";
    public static final String CHEMIN      = "chemin";
    public static final String ATT_CLIENT = "client";
    public static final String ATT_FORM = "form";
    public static final String SESSION_CLIENTS ="clients";
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

            this.getServletContext().getRequestDispatcher(VUE_EDITION).forward(request, response);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         /*
         * Lecture du paramètre 'chemin' passé à la servlet via la déclaration
         * dans le web.xml
         */
        String chemin = this.getServletConfig().getInitParameter( CHEMIN );
        
        /* Préparation de l'objet formulaire */
        EditionClientForm form = new EditionClientForm(clientDao);
		
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Client client = form.modifierClient(request, chemin);
		
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_CLIENT, client );
        
        if(form.getErreurs().isEmpty()){
            
            /* Alors récupération de la map des clients dans la session */
            HttpSession session = request.getSession();
            Map<Long, Client> clients = (HashMap<Long, Client>) session.getAttribute( SESSION_CLIENTS );
            /* Si aucune map n'existe, alors initialisation d'une nouvelle map */
            if ( clients == null ) {
                clients = new HashMap<Long, Client>();
            }
            /* Puis ajout du client courant dans la map */
            clients.replace(client.getId(), client );
            /* Et enfin (ré)enregistrement de la map en session */
            session.setAttribute( SESSION_CLIENTS, clients );            
            response.sendRedirect(request.getContextPath() + VUE_AFFICHE);
            //this.getServletContext().getRequestDispatcher(VUE_AFFICHE).forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher(VUE_EDITION).forward(request, response);
        }
    }
}
