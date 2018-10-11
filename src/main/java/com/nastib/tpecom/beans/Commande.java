
package com.nastib.tpecom.beans;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Commande {

    private Client client;
    private String dateCommande;
    private String modePaiementCommande;
    private String montantCommande;
    private String statutPaiementCommande;
    private String modeLivraisonCommande;
    private String statutLivraisonCommande;

    public Commande() {}

    public String getDateCommande() {
        return dateCommande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    } 
    
    public void setDateCommande(String dateCommande) {

        if(dateCommande == null || dateCommande.isEmpty()){
           /* Récupération de la date courante */
           DateTime dt = new DateTime();
           /* Conversion de la date en String selon le format défini */
           DateTimeFormatter formatter = DateTimeFormat.forPattern( "dd/MM/yyyy HH:mm:ss" );
           String date = dt.toString( formatter );
           this.dateCommande = date;
        }else{
            this.dateCommande = dateCommande;
        }
    }

    public String getModePaiementCommande() {
        return modePaiementCommande;
    }

    public void setModePaiementCommande(String modePaiementCommande) {
        this.modePaiementCommande = modePaiementCommande;
    }

    public String getMontantCommande() {
        return montantCommande;
    }

    public void setMontantCommande(String montantCommande) {
        this.montantCommande = montantCommande;
    }

    public String getStatutPaiementCommande() {
        return statutPaiementCommande;
    }

    public void setStatutPaiementCommande(String statutPaiementCommande) {
        this.statutPaiementCommande = statutPaiementCommande;
    }

    public String getModeLivraisonCommande() {
        return modeLivraisonCommande;
    }

    public void setModeLivraisonCommande(String modeLivraisonCommande) {
        this.modeLivraisonCommande = modeLivraisonCommande;
    }

    public String getStatutLivraisonCommande() {
        return statutLivraisonCommande;
    }

    public void setStatutLivraisonCommande(String statutLivraisonCommande) {
        this.statutLivraisonCommande = statutLivraisonCommande;
    }

    
    
}

