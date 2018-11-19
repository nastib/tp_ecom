
package com.nastib.tpecom.entities;

import com.nastib.tpecom.tools.JodaDateTimeConverter;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;

@Entity
@Table(name = "commande")
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;
            
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    
    @ManyToOne
    @JoinColumn( name = "id_client" )
    private Client client;
    
    @Column( name="date_commande", columnDefinition = "TIMESTAMP" )
    @Converter( name = "dateTimeConverter", converterClass = JodaDateTimeConverter.class )
    @Convert( "dateTimeConverter" )
    private DateTime dateCommande;
    
    @Column(name="montant")
    private Double montant; 
    
    @Column(name="mode_paiement", length=20)
    private String modePaiement;
    
    @Column(name="statut_paiement", length=20)
    private String statutPaiement;
    
    @Column(name="mode_livraison", length=20)
    private String modeLivraison;
    
    @Column(name="statut_livraison", length=20)
    private String statutLivraison;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getDate() {
        return dateCommande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    } 
    
    public void setDate(DateTime dateCommande) {
        this.dateCommande = dateCommande;
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

