
package com.nastib.tpecom.forms;

import com.nastib.tpecom.beans.Client;
import com.nastib.tpecom.beans.Commande;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandeForm extends ValidateFormImpl implements ValidateForm {

    public static final String CHAMP_CLIENT = "client";
    public static final String CHAMP_DATE = "dateCommande";
    public static final String CHAMP_MONTANT = "montantCommande";
    public static final String CHAMP_PAIEMENT = "modePaiementCommande";
    public static final String CHAMP_STATUTPAIE = "statutPaiementCommande";
    public static final String CHAMP_LIVRAISON = "modeLivraisonCommande";
    public static final String CHAMP_STATUTLIVR = "statutLivraisonCommande";
    public static final String ATT_SESSION_LISTECOMMANDES = "listeCommandes";
    public static final String ATT_SESSION_LISTECLIENTS = "listeClients";
    public static final String CHAMP_NUMERO = "numero";
    
    private String resultat;
    private final Map<String, String> erreurs = new HashMap<>();
    private Commande commande;
    private Client client;
    
    private String num, idClient, date, paiement,statutPaiement, livraison, statutLivraison, montant;
   
    // Getters and setters ///////////////
    public String getResultat() {
        return resultat;
    }

    public Commande getCommande() {
        return commande;
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
     * Méthode utilitaire qui retourne null si un champ est vide sinon son contenu
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
    public Commande saveCommande(HttpServletRequest request) {
                
        date = getValeurChamp(request, CHAMP_DATE);
        montant = getValeurChamp(request, CHAMP_MONTANT);
        paiement = getValeurChamp(request, CHAMP_PAIEMENT);
        statutPaiement = getValeurChamp(request, CHAMP_STATUTPAIE);
        livraison = getValeurChamp(request, CHAMP_LIVRAISON);
        statutLivraison = getValeurChamp(request, CHAMP_STATUTLIVR);
        idClient = getValeurChamp(request, CHAMP_CLIENT);
        num = getValeurChamp(request, CHAMP_NUMERO);
        
        //Validation du formilaire
        valideForm() ;
        
        client = rechercherClient(request);
        
        //Persistence dans le bean Commande
        commande = new Commande();
        commande.setClient(client);
        commande.setDateCommande(date);
        commande.setMontantCommande(montant);
        commande.setModePaiementCommande(paiement);
        commande.setModeLivraisonCommande(livraison);
        commande.setStatutLivraisonCommande(statutLivraison);
        commande.setStatutPaiementCommande(statutPaiement);
        if (getErreurs().isEmpty()){
            if(num == null){
                //Persiste le client dans la collection de clients en session
                request.setAttribute(ATT_SESSION_LISTECOMMANDES,addCommandeToList(request, commande)) ; 
            } else {
                //Persiste le client dans la collection de clients en session
                request.setAttribute(ATT_SESSION_LISTECOMMANDES,editCommandeToList(request, commande, Integer.parseInt(num))) ;       
            }   
        }
        return commande;
    }

    /**
     * Validation du formulaire ClientForm
     */
    private boolean valideForm() {

        try {
            this.validateNombreControle(idClient, CHAMP_CLIENT,1);
        } catch (ValidationException ex) {
            setErreur(idClient, ex.getMessage());
            Logger.getLogger(CommandeForm.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        
        try {
            this.validateDateControle(date, CHAMP_DATE);
        } catch (ValidationException ex) {
            setErreur(CHAMP_DATE, ex.getMessage());
            Logger.getLogger(CommandeForm.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
    
        try {
            this.validateDoubleControle(montant, CHAMP_MONTANT);
        } catch (ValidationException ex) {
            setErreur(CHAMP_MONTANT, ex.getMessage());
            Logger.getLogger(CommandeForm.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }

        try {
            this.validateStringControle(paiement, CHAMP_PAIEMENT, 2);
        } catch (ValidationException ex) {
            setErreur(CHAMP_PAIEMENT, ex.getMessage());
            Logger.getLogger(CommandeForm.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }

        try {
            this.validateStringControle(statutPaiement, CHAMP_STATUTPAIE, 2);
        } catch (ValidationException ex) {
            setErreur(CHAMP_STATUTPAIE, ex.getMessage());
            Logger.getLogger(CommandeForm.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }

        try {
            this.validateStringControle(livraison, CHAMP_LIVRAISON, 2);
        } catch (ValidationException ex) {
            setErreur(CHAMP_LIVRAISON, ex.getMessage());
            Logger.getLogger(CommandeForm.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }

        try {
            this.validateStringControle(statutLivraison, CHAMP_STATUTLIVR,2);
        } catch (ValidationException ex) {
            setErreur(CHAMP_STATUTLIVR, ex.getMessage());
            Logger.getLogger(CommandeForm.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }           
        return getErreurs().size() > 0;
    }

    public Client rechercherClient(HttpServletRequest request) {
        Client client = new Client();
        String num = getValeurChamp(request, CHAMP_CLIENT);
        if(num != null){
            try {
                int numero = Integer.parseInt(num);
                List<Client> listeClients = (List<Client>) request.getSession().getAttribute(ATT_SESSION_LISTECLIENTS);
                if (listeClients != null) {
                    int cpt = 1;
                    for (Client item : listeClients) {
                        if (numero == cpt) {
                            client = item;
                        }
                        cpt++;
                    }
                }
            } catch (Exception ex){
                setErreur(CHAMP_CLIENT, "Le champ client est obligatoire !");
                Logger.getLogger(CommandeForm.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            }
        }
        return client;
    }

    public Commande rechercherCommande(HttpServletRequest request) {
        Commande commande = new Commande();
        String num = getValeurChamp(request, CHAMP_NUMERO);
        if (num != null) {
            int numero = Integer.parseInt(num);
            List<Commande> listeCommandes = (List<Commande>) request.getSession().getAttribute(ATT_SESSION_LISTECOMMANDES);
            if (listeCommandes != null) {
                int cpt = 1;
                for (Commande item : listeCommandes) {
                    if (numero == cpt) {
                        commande = item;
                    }
                    cpt++;
                }
            }
        }
        return commande;
    } 
    
    private List<Commande> addCommandeToList(HttpServletRequest request, Commande commande){
        //Récupération de la collection de client en session scope
        HttpSession session = request.getSession();      
        if(session.getAttribute(ATT_SESSION_LISTECOMMANDES) == null){
            session.setAttribute( ATT_SESSION_LISTECOMMANDES, (new ArrayList<>()) );
        }              
       List<Commande> listeCommandes =  (List<Commande>) session.getAttribute(ATT_SESSION_LISTECOMMANDES); 
       listeCommandes.add(commande);
       return listeCommandes;
    }
    
    private List<Commande> editCommandeToList(HttpServletRequest request, Commande commande, int numero){        
        HttpSession session = request.getSession();
        List<Commande> listeCommandes = (List<Commande>) session.getAttribute(ATT_SESSION_LISTECOMMANDES);
        if (listeCommandes != null) {
            for (int i = 0; i < listeCommandes.size(); i++) {
                if ((numero-1) == i) {
                    listeCommandes.set(i, commande);
                }
            }
        }
        return listeCommandes;

    } 
    
    public void deleteCommande(HttpServletRequest request) {
        int numero = Integer.parseInt(getValeurChamp(request, CHAMP_NUMERO));
        HttpSession session = request.getSession();
        List<Commande> listeCommandes = (List<Commande>) session.getAttribute(ATT_SESSION_LISTECOMMANDES);
        if (listeCommandes != null) {
            for (int i = 0; i < listeCommandes.size(); i++) {
                if ((numero-1) == i) {
                    listeCommandes.remove(i);
                    session.setAttribute(ATT_SESSION_LISTECOMMANDES, listeCommandes);
                }
            }
        }
    }     
}
