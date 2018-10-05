
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

    public static final String VUE_AFFICHE = "/afficherCommande.jsp";
    public static final String VUE_CREATION = "/creerCommande.jsp";
    public static final String MSG_NOM = "Erreur - le <b>nom</b> est obligatoire. ";
    public static final String MSG_ADRESSE = "Erreur - l\'<b>adresse</b> est obligatoire. ";
    public static final String MSG_TEL = "Erreur - le <b>Téléphone</b> est obligatoire. ";
    public static final String MSG_SUCCES = "Commande créée avec succès !";
    public static final String MSG_MONTANT = "Erreur - le <b>Montant</b> est obligatoire. ";
    public static final String MSG_PAIEMENT = "Erreur - le <b>Mode de paiement</b> est obligatoire. ";
    public static final String MSG_LIVRAISON = "Erreur - le <b>Mode de livraison</b> est obligatoire. ";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        this.getServletContext().getRequestDispatcher(VUE_CREATION).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        String nomClient = request.getParameter("nomClient");
        String prenomClient = request.getParameter("prenomClient");
        String adresseClient = request.getParameter("adresseClient");
        String telephoneClient = request.getParameter("telephoneClient");
        String emailClient = request.getParameter("emailClient");
        
        String modePaiementCommande = request.getParameter("modePaiementCommande");
        Double montantCommande = Double.parseDouble(request.getParameter("montantCommande"));
        String statutPaiementCommande = request.getParameter("statutPaiementCommande");
        String modeLivraisonCommande = request.getParameter("modeLivraisonCommande");
        String statutLivraisonCommande = request.getParameter("statutLivraisonCommande");
        
        Client client = new Client();
        Commande commande = new Commande();
        commande.setDateCommande("");
        
        if ( (nomClient != null && !nomClient.isEmpty()) && 
             (adresseClient != null && !adresseClient.isEmpty()) &&
             (telephoneClient != null && !telephoneClient.isEmpty()) &&
             ( montantCommande != null && !montantCommande.equals(0.0) && !montantCommande.equals(0)) && 
             (modePaiementCommande != null && !modePaiementCommande.isEmpty()) &&
             (modeLivraisonCommande != null && !modeLivraisonCommande.isEmpty())) {
                
            try {
                
                client.setNomClient(nomClient);
                client.setPrenomClient(prenomClient);
                client.setAdresseClient(adresseClient);
                client.setTelephoneClient(telephoneClient);
                client.setEmailClient(emailClient);

                commande.setMontantCommande(montantCommande);
                commande.setModePaiementCommande(modePaiementCommande);
                commande.setStatutPaiementCommande(statutPaiementCommande);
                commande.setModeLivraisonCommande(modeLivraisonCommande);
                commande.setStatutLivraisonCommande(statutLivraisonCommande);

                request.setAttribute("client", client);
                request.setAttribute("commande", commande);
                request.setAttribute("message", MSG_SUCCES);
                
                this.getServletContext().getRequestDispatcher(VUE_AFFICHE).forward(request, response);  
            } catch (BeanException ex) {
                request.setAttribute("nomHelp", ex.getMessage());
                this.getServletContext().getRequestDispatcher(VUE_CREATION).forward(request, response);      
            }            
    

        } else {
             if ( nomClient == null || nomClient.isEmpty()){
                    request.setAttribute("nomHelp", MSG_NOM);
             } else {
                    request.setAttribute("nomHelp", null);
             }

             if ( adresseClient == null ||  adresseClient.isEmpty()){
                    request.setAttribute("adresseHelp", MSG_ADRESSE);
             } else {
                    request.setAttribute("adresseHelp", null);
             }
             
             if ( telephoneClient == null ||  telephoneClient.isEmpty()){
                    request.setAttribute("telephoneHelp", MSG_TEL);
             }  else {
                    request.setAttribute("telephoneHelp", null);
             }           
             
             if ( montantCommande == null || montantCommande.equals(0.0) || montantCommande.equals(0)) {
                 request.setAttribute("montantHelp", MSG_MONTANT);
             } else {
                  request.setAttribute("montantHelp", null);
             }

             if ( modePaiementCommande == null ||  modePaiementCommande.isEmpty()) {
                 request.setAttribute("paiementHelp", MSG_PAIEMENT);
             } else {
                  request.setAttribute("paiementHelp", null);
             } 
             
             if ( modeLivraisonCommande == null ||  modeLivraisonCommande.isEmpty()) {
                 request.setAttribute("livraisonHelp", MSG_LIVRAISON);
             } else {
                  request.setAttribute("livraisonHelp", null);
             }              
             this.getServletContext().getRequestDispatcher(VUE_CREATION).forward(request, response);      

        }
    }

}
