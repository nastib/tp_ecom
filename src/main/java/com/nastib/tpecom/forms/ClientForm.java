package com.nastib.tpecom.forms;

import com.nastib.tpecom.beans.BeanException;
import com.nastib.tpecom.beans.Client;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ClientForm extends ValidateFormImpl implements ValidateForm {

    public static final String CHAMP_NOM = "nomClient";
    public static final String CHAMP_PRENOM = "prenomClient";
    public static final String CHAMP_ADRESSE = "adresseClient";
    public static final String CHAMP_TELEPHONE = "telephoneClient";
    public static final String CHAMP_EMAIL = "emailClient";
    public static final String ATT_SESSION_LISTECLIENTS = "listeClients";
    public static final String CHAMP_NUMERO = "numero";

    
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private Client client;
    
    // Getters and setters ///////////////
    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    /**
     * Ajoute un message à la map des erreurs.
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }   
    
    /**
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     */
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }
    }

    /**
     * Methode qui traite de la logique metier du formulaire
     */
    public Client updateClient(HttpServletRequest request) {
        
        nom = getValeurChamp(request, CHAMP_NOM);
        prenom = getValeurChamp(request, CHAMP_PRENOM);
        adresse = getValeurChamp(request, CHAMP_ADRESSE);
        telephone = getValeurChamp(request, CHAMP_TELEPHONE);
        email = getValeurChamp(request, CHAMP_EMAIL);   
        String num = getValeurChamp(request, CHAMP_NUMERO);
        
        //Validation du formilaire
        valideForm(request) ;
        
        //Enregistrement du formulaire
        client =  saveClient(new Client()); 
        
        if (erreurs.isEmpty()) {
            resultat = "Client "+client.getNomClient()+" créé avec succès.";
            if(num == null){
                //Persiste le client dans la collection de clients en session
                request.setAttribute(ATT_SESSION_LISTECLIENTS,addClientToList(request, client)) ; 
            } else {
                //Persiste le client dans la collection de clients en session
                request.setAttribute(ATT_SESSION_LISTECLIENTS,editClientToList(request, client, Integer.parseInt(num))) ;       
            }
        } else {
            resultat = "Échec de la création du client "+client.getNomClient();
        }       
        
        return client;
    }

    /**
     * Sauvegarde du formualaire dans le bean Client
     */
    private Client saveClient(Client client) {

        try {
            client.setNomClient(nom);
        } catch (BeanException ex) {
            setErreur(CHAMP_NOM, ex.getMessage());
            Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        client.setPrenomClient(prenom);
        client.setAdresseClient(adresse);
        client.setTelephoneClient(telephone);
        client.setEmailClient(email);
               
        return client;
    }

     public Client rechercherClient(HttpServletRequest request) {
        Client client = new Client();
        int numero = Integer.parseInt(getValeurChamp(request, CHAMP_NUMERO));
        HttpSession session = request.getSession();
        List<Client> listeClients = (List<Client>) session.getAttribute(ATT_SESSION_LISTECLIENTS);
        if (listeClients != null) {
            int cpt = 1;
            for (Client item : listeClients) {
                if (numero == cpt) {
                    client = item;
                }
                cpt++;
            }
        }
        return client;
    }

    public void deleteClient(HttpServletRequest request) {
        int numero = Integer.parseInt(getValeurChamp(request, CHAMP_NUMERO));
        HttpSession session = request.getSession();
        List<Client> listeClients = (List<Client>) session.getAttribute(ATT_SESSION_LISTECLIENTS);
        if (listeClients != null) {
            for (int i = 0; i < listeClients.size(); i++) {
                if ((numero-1) == i) {
                    listeClients.remove(i);
                    session.setAttribute(ATT_SESSION_LISTECLIENTS, listeClients);
                }
            }
        }
    }   
    
    /**
     * Validation du formulaire ClientForm
     */
    private boolean valideForm(HttpServletRequest request) {

        try {
            this.validateStringControle(nom, CHAMP_NOM, 2);
        } catch (ValidationException ex) {
            setErreur(CHAMP_NOM, ex.getMessage());
            Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }

        try {
            this.validateStringControle(prenom, CHAMP_PRENOM, 2);
        } catch (ValidationException ex) {
            setErreur(CHAMP_PRENOM, ex.getMessage());
            Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }

        try {
            this.validateStringControle(adresse, CHAMP_ADRESSE, 10);
        } catch (ValidationException ex) {
            setErreur(CHAMP_ADRESSE, ex.getMessage());
            Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }

        try {
            this.validateNombreControle(telephone, CHAMP_TELEPHONE, 4);
        } catch (ValidationException ex) {
            setErreur(CHAMP_TELEPHONE, ex.getMessage());
            Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }

        try {
            this.validateEmailControle(email, CHAMP_EMAIL);
        } catch (ValidationException ex) {
            setErreur(CHAMP_EMAIL, ex.getMessage());
            Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        
        return getErreurs().size() > 0;

    }

    private List<Client> addClientToList(HttpServletRequest request, Client client){
        //Récupération de la collection de client en session scope
        HttpSession session = request.getSession();      
        if(session.getAttribute(ATT_SESSION_LISTECLIENTS) == null){
            session.setAttribute( ATT_SESSION_LISTECLIENTS, (new ArrayList<>()) );
        }              
       List<Client> listeClients =  (List<Client>) session.getAttribute(ATT_SESSION_LISTECLIENTS); 
       listeClients.add(client);
       return listeClients;
    }
    
    private List<Client> editClientToList(HttpServletRequest request, Client client, int numero){        
        HttpSession session = request.getSession();
        List<Client> listeClients = (List<Client>) session.getAttribute(ATT_SESSION_LISTECLIENTS);
        if (listeClients != null) {
            for (int i = 0; i < listeClients.size(); i++) {
                if ((numero-1) == i) {
                    listeClients.set(i, client);
                }
            }
        }
        return listeClients;

    }
}
