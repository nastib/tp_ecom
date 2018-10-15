
package com.nastib.tpecom.beans;

public class Client {
    private String nomClient; 
    private String prenomClient; 
    private String adresseClient; 
    private String telephoneClient; 
    private String emailClient; 
    private String image;
    
    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) throws BeanException {
        if ( nomClient != null && nomClient.length() <= 15) {
            this.nomClient = nomClient;
        } else {
            throw new BeanException("Attention ! \n Le nom est trop grand ! (15 caractères maximum).");
        }
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public String getTelephoneClient() {
        return telephoneClient;
    }

    public void setTelephoneClient(String telephoneClient) {
        this.telephoneClient = telephoneClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Client{" + "nomClient=" + nomClient + ", prenomClient=" + prenomClient + ", adresseClient=" + adresseClient + ", telephoneClient=" + telephoneClient + ", emailClient=" + emailClient + ", image=" + image + '}';
    }
    
    
}
