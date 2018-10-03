
package com.nastib.tpecom.servlets;

import com.nastib.tpecom.beans.BeanException;
import com.nastib.tpecom.beans.Client;
import com.nastib.tpecom.beans.Commande;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreationCommande extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        this.getServletContext().getRequestDispatcher("/creerCommande.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        String nomClient = request.getParameter("nomClient");
        String prenomClient = request.getParameter("prenomClient");
        String adresseClient = request.getParameter("adresseClient");
        String telephoneClient = request.getParameter("telephoneClient");
        String emailClient = request.getParameter("emailClient");
        
        Commande commande = new Commande();
        commande.setDateCommande("");
        String dateCommande = commande.getDateCommande();
        
        String modePaiementCommande = request.getParameter("modePaiementCommande");
        Double montantCommande = Double.parseDouble(request.getParameter("montantCommande"));
        String statutPaiementCommande = request.getParameter("statutPaiementCommande");
        String modeLivraisonCommande = request.getParameter("modeLivraisonCommande");
        String statutLivraisonCommande = request.getParameter("statutLivraisonCommande");
        
        request.setAttribute("nomClient", nomClient);
        request.setAttribute("prenomClient", prenomClient);
        request.setAttribute("adresseClient", adresseClient);
        request.setAttribute("telephoneClient", telephoneClient);
        request.setAttribute("emailClient", emailClient);
        
        request.setAttribute("dateCommande",dateCommande);
        request.setAttribute("modePaiementCommande",modePaiementCommande);
        request.setAttribute("montantCommande",montantCommande);
        request.setAttribute("statutPaiementCommande",statutPaiementCommande);
        request.setAttribute("modeLivraisonCommande",modeLivraisonCommande);
        request.setAttribute("statutLivraisonCommande",statutLivraisonCommande);
        
        System.out.println(nomClient);
        String message="";
   
        if ( (nomClient != null && !nomClient.isEmpty()) && 
             (adresseClient != null && !adresseClient.isEmpty() &&
             (telephoneClient != null && !telephoneClient.isEmpty())) ) {
            Client client = new Client();
            message = "Commande créée avec succès !";
            request.setAttribute("message", message);     
            try {
                client.setPrenomClient(prenomClient);
                client.setNomClient(nomClient);
                client.setAdresseClient(adresseClient);
                client.setTelephoneClient(telephoneClient);
                client.setEmailClient(emailClient);

                this.getServletContext().getRequestDispatcher("/afficherCommande.jsp").forward(request, response);  
            } catch (BeanException ex) {
                request.setAttribute("nomHelp", ex.getMessage());
                this.getServletContext().getRequestDispatcher("/creerCommande.jsp").forward(request, response);      
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

            this.getServletContext().getRequestDispatcher("/creerCommande.jsp").forward(request, response);      

        }
    }

}
