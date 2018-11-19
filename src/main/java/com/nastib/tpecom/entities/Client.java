
package com.nastib.tpecom.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "client")
public class Client implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(name="nom")
    private String nom; 
    
    @Column(name="prenom")
    private String prenom; 
    
    @Column(name="adresse")
    private String adresse; 
    
    @Column(name="telephone")
    private String telephone; 
    
    @Column(name="email")
    private String email; 
    
    @Column(name="image")
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
 
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nomClient) throws BeanException {
        if ( nomClient != null && nomClient.length() <= 15) {
            this.nom = nomClient;
        } else {
            throw new BeanException("Attention ! \n Le nom est trop grand ! (15 caractères maximum).");
        }
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenomClient) {
        this.prenom = prenomClient;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresseClient) {
        this.adresse = adresseClient;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephoneClient) {
        this.telephone = telephoneClient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emailClient) {
        this.email = emailClient;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Client{" + "nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", telephone=" + telephone + ", email=" + email + ", image=" + image + '}';
    }
    
    
}
