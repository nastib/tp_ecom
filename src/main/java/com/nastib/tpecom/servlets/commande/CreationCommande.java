
package com.nastib.tpecom.servlets.commande;

import com.nastib.tpecom.beans.Client;
import com.nastib.tpecom.beans.Commande;
import com.nastib.tpecom.dao.ClientDao;
import com.nastib.tpecom.dao.CommandeDao;
import com.nastib.tpecom.dao.DAOFactory;
import com.nastib.tpecom.forms.CreationCommandeForm;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/creationcommande")
public class CreationCommande extends HttpServlet {

    public static final String CONF_DAO_FACTORY      = "daofactory";
    public static final String ATT_COMMANDE          = "commande";
    public static final String ATT_FORM              = "form";
    public static final String SESSION_CLIENTS       = "clients";
    public static final String APPLICATION_CLIENTS   = "initClients";
    public static final String SESSION_COMMANDES     = "commandes";
    public static final String APPLICATION_COMMANDES = "initCommandes";

    public static final String VUE_SUCCES            = "/WEB-INF/afficherCommande.jsp";
    public static final String VUE_FORM              = "/WEB-INF/creerCommande.jsp";

    private CommandeDao        commandeDao;

    @Override
    public void init() throws ServletException {
        /* Récupération d'une instance de nos DAO Client et Commande */
        this.commandeDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCommandeDao();
    }

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* À la réception d'une requête GET, simple affichage du formulaire */
        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        /* Préparation de l'objet formulaire */
        CreationCommandeForm form = new CreationCommandeForm( commandeDao );

        /* Traitement de la requête et récupération du bean en résultant */
        Commande commande = form.creerCommande( request );

        /* Ajout du bean et de l'objet métier à l'objet requête */
        request.setAttribute( ATT_COMMANDE, commande );
        request.setAttribute( ATT_FORM, form );

        /* Si aucune erreur */
        if ( form.getErreurs().isEmpty() ) {
            /* Alors récupération de la map des clients dans la session */
            HttpSession session = request.getSession();
            /* Ensuite récupération de la map des commandes dans la session */
            Map<Long, Commande> commandes = (HashMap<Long, Commande>) session.getAttribute( SESSION_COMMANDES );
            /* Si aucune map n'existe, alors initialisation d'une nouvelle map */
            if ( commandes == null ) {
                commandes = new HashMap<Long, Commande>();
            }
            /* Puis ajout de la commande courante dans la map */
            commandes.put( commande.getId(), commande );
            /* Et enfin (ré)enregistrement de la map en session */
            session.setAttribute( SESSION_COMMANDES, commandes );

            /* Affichage de la fiche récapitulative */
            this.getServletContext().getRequestDispatcher( VUE_SUCCES ).forward( request, response );
        } else {
            /* Sinon, ré-affichage du formulaire de création avec les erreurs */
            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
        }
    }
}
