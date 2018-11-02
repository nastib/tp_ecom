
package com.nastib.tpecom.servlets.commande;

import com.nastib.tpecom.entities.Commande;
import com.nastib.tpecom.dao.CommandeDao;
import com.nastib.tpecom.dao.DAOFactory;
import static com.nastib.tpecom.servlets.client.AfficherClient.CONF_DAO_FACTORY;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/affichercommande")
public class AfficherCommande extends HttpServlet {
    public static final String VUE = "/WEB-INF/afficherCommande.jsp";
    public static final String ATT_COMMANDE = "commande";
    public static final String ATT_FORM = "form";
    public static final String PARAM_ID_COMMANDE = "idCommande";

    private CommandeDao commandeDao;
    
    @Override
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        commandeDao = ( (DAOFactory) this.getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCommandeDao();
    } 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idCommande = request.getParameter(PARAM_ID_COMMANDE);
        if (idCommande != null && idCommande.trim().length() > 0){
            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
            Commande commande = commandeDao.trouver(Long.parseLong(idCommande)) ;

            /* Stockage du formulaire et du bean dans l'objet request */
            request.setAttribute( ATT_COMMANDE, commande );  
        }
        
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
