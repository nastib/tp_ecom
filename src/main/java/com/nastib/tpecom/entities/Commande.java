
package com.nastib.tpecom.entities;

import java.io.Serializable;
import org.joda.time.DateTime;

public class Commande implements Serializable {

    private long id;
    private Client client;
    private DateTime date;
    private Double montant;    
    private String modePaiement;
    private String statutPaiement;
    private String modeLivraison;
    private String statutLivraison;
    private final static long serialVersionUID = 100211;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getDate() {
        return date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    } 
    
    public void setDate(DateTime dateCommande) {
        this.date = dateCommande;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiementCommande) {
        this.modePaiement = modePaiementCommande;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getStatutPaiement() {
        return statutPaiement;
    }

    public void setStatutPaiement(String statutPaiementCommande) {
        this.statutPaiement = statutPaiementCommande;
    }

    public String getModeLivraison() {
        return modeLivraison;
    }

    public void setModeLivraison(String modeLivraisonCommande) {
        this.modeLivraison = modeLivraisonCommande;
    }

    public String getStatutLivraison() {
        return statutLivraison;
    }

    public void setStatutLivraison(String statutLivraisonCommande) {
        this.statutLivraison = statutLivraisonCommande;
    }

    
    
}

