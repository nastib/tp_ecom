
package com.nastib.tpecom.servlets;

import com.nastib.tpecom.beans.BeanException;
import com.nastib.tpecom.beans.Client;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class CreationClient extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/creerClient.jsp").forward(request, response);      
    }
    @Override
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String nomClient = request.getParameter("nomClient");
        String prenomClient = request.getParameter("prenomClient");
        String adresseClient = request.getParameter("adresseClient");
        String telephoneClient = request.getParameter("telephoneClient");
        String emailClient = request.getParameter("emailClient");
        request.setAttribute("nomClient", nomClient);
        request.setAttribute("prenomClient", prenomClient);
        request.setAttribute("adresseClient", adresseClient);
        request.setAttribute("telephoneClient", telephoneClient);
        request.setAttribute("emailClient", emailClient);
        System.out.println(nomClient);
        String message="";
   
        if ( (nomClient != null && !nomClient.isEmpty()) && 
             (adresseClient != null && !adresseClient.isEmpty() &&
             (telephoneClient != null && !telephoneClient.isEmpty())) ) {
             Client client = new Client();
            message = "Client créé avec succès !";
            request.setAttribute("message", message);     
            try {
                client.setPrenomClient(prenomClient);
                client.setNomClient(nomClient);
                client.setAdresseClient(adresseClient);
                client.setTelephoneClient(telephoneClient);
                client.setEmailClient(emailClient);
                
                this.getServletContext().getRequestDispatcher("/afficherClient.jsp").forward(request, response);  
            } catch (BeanException ex) {
                request.setAttribute("nomHelp", ex.getMessage());
                this.getServletContext().getRequestDispatcher("/creerClient.jsp").forward(request, response);      
            }            
    

        } else {
             if ( nomClient == null || (nomClient != null && nomClient.isEmpty())){
                    message = "Erreur - le champs <b>nom</b> est obligatoire. ";
                    request.setAttribute("nomHelp", message);
             } else {
                    request.setAttribute("nomHelp", null);
             }

             if ( adresseClient == null || (adresseClient != null && adresseClient.isEmpty())){
                    message = "Erreur - le champs <b>adresse</b> est obligatoire. ";
                    request.setAttribute("adresseHelp", message);
             } else {
                    request.setAttribute("adresseHelp", null);
             }
             
             if ( telephoneClient == null || (telephoneClient != null && telephoneClient.isEmpty())){
                    message = "Erreur - le champs <b>telephone</b> est obligatoire. ";
                    request.setAttribute("telephoneHelp", message);
             }  else {
                    request.setAttribute("telephoneHelp", null);
             }           


            //  + "<br> <a href=\"creerClient.jsp\">Cliquez ici</a> pour accéder au formulaire de création d'un client.";

            this.getServletContext().getRequestDispatcher("/creerClient.jsp").forward(request, response);      

        }        
    }

}
