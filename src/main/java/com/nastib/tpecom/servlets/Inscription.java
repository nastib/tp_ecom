package com.nastib.tpecom.servlets;

import com.nastib.tpecom.dao.UtilisateurDao;
import com.nastib.tpecom.entities.Utilisateur;
import com.nastib.tpecom.forms.InscriptionForm;
import java.io.IOException;
import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( "/inscription" )
public class Inscription extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String VUE              = "/WEB-INF/inscription.jsp";
    public static final String URL_REDIRECTION  = "/connexion";

    // Injection de notre EJB (Session Bean Stateless)
    @EJB
    private UtilisateurDao   utilisateurDao;
    
    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
	
    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* Préparation de l'objet formulaire */
        InscriptionForm form = new InscriptionForm( utilisateurDao);

        /* Traitement de la requête et récupération du bean en résultant */
        Utilisateur utilisateur = form.inscrireUtilisateur( request );
        System.out.println(utilisateur.toString());
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );
       
        if(form.getErreurs().size()>0){
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        } else {
            response.sendRedirect(request.getContextPath() + URL_REDIRECTION);
        }

    }
}