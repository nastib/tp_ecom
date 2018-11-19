
package com.nastib.tpecom.servlets.commande;

import com.nastib.tpecom.entities.Commande;
import com.nastib.tpecom.dao.CommandeDao;
import com.nastib.tpecom.forms.EditionCommandeForm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/editioncommande")
public class EditionCommande extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    public static final String CONF_DAO_FACTORY      = "daofactory";
    public static final String ATT_COMMANDE          = "commande";
    public static final String ATT_FORM              = "form";
    public static final String SESSION_CLIENTS       = "clients";
    public static final String APPLICATION_CLIENTS   = "initClients";
    public static final String SESSION_COMMANDES     = "commandes";
    public static final String APPLICATION_COMMANDES = "initCommandes";
    public static final String PARAM_ID_COMMANDE = "idCommande";

    public static final String VUE_SUCCES            = "/listecommandes";
    public static final String VUE_FORM              = "/WEB-INF/editerCommande.jsp";

    // Injection de notre EJB (Session Bean Stateless)
    @EJB
    private CommandeDao commandeDao;

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	/* Récupération du paramètre */
        String idCommande = request.getParameter(PARAM_ID_COMMANDE);
        if (idCommande != null && idCommande.trim().length() > 0){
            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
            Commande commande = commandeDao.trouver(Long.parseLong(idCommande)) ;

            /* Stockage du formulaire et du bean dans l'objet request */
            request.setAttribute( ATT_COMMANDE, commande );  

            this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
        }
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {


        /* Préparation de l'objet formulaire */
        EditionCommandeForm form = new EditionCommandeForm( commandeDao );

        /* Traitement de la requête et récupération du bean en résultant */
        Commande commande = form.editerCommande( request );

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
            response.sendRedirect(request.getContextPath() + VUE_SUCCES);
        } else {
            /* Sinon, ré-affichage du formulaire de création avec les erreurs */
            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
        }
    }
}