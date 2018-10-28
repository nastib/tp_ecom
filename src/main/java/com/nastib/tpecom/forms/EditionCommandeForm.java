
package com.nastib.tpecom.forms;

import com.nastib.tpecom.beans.Client;
import com.nastib.tpecom.beans.Commande;
import com.nastib.tpecom.dao.CommandeDao;
import com.nastib.tpecom.dao.DAOException;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;

public class EditionCommandeForm  {

    private static final String CHAMP_CHOIX_CLIENT     = "choixNouveauClient";
    private static final String PARAM_IDCOMMANDE       = "idCommande";
    private static final String CHAMP_COMBO_CLIENT     = "client";
    private static final String CHAMP_MONTANT          = "montant";
    private static final String CHAMP_MODE_PAIEMENT    = "modePaiement";
    private static final String CHAMP_STATUT_PAIEMENT  = "statutPaiement";
    private static final String CHAMP_MODE_LIVRAISON   = "modeLivraison";
    private static final String CHAMP_STATUT_LIVRAISON = "statutLivraison";

    private static final String ANCIEN_CLIENT          = "ancienClient";
    private static final String SESSION_CLIENTS        = "clients";

    private String              resultat;
    private Map<String, String> erreurs                = new HashMap<String, String>();
    private CommandeDao         commandeDao;
    private Client              client;

    public EditionCommandeForm( CommandeDao commandeDao ) {
        this.commandeDao = commandeDao;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Commande editerCommande( HttpServletRequest request ) {
        /*
         * Si l'utilisateur choisit un client déjà existant, pas de validation à
         * effectuer
         */
        String choixNouveauClient = getValeurChamp( request, CHAMP_CHOIX_CLIENT );
        
        if ( ANCIEN_CLIENT.equals( choixNouveauClient ) ) {
            /* Récupération de l'id du client choisi */
            String idAncienClient = getValeurChamp( request, CHAMP_COMBO_CLIENT );
            Long id = null;
            try {
                id = Long.parseLong( idAncienClient );
            } catch ( NumberFormatException e ) {
                setErreur( CHAMP_CHOIX_CLIENT, "Client inconnu, merci d'utiliser le formulaire prévu à cet effet." );
                id = 0L;
            }
            /* Récupération de l'objet client correspondant dans la session */
            HttpSession session = request.getSession();
            client = ( (Map<Long, Client>) session.getAttribute( SESSION_CLIENTS ) ).get( id );
        } 

        /*
         * Ensuite, il suffit de procéder normalement avec le reste des champs
         * spécifiques à une commande.
         */

        /* Récupération de la date dans un DateTime Joda. */
        DateTime dt = new DateTime();
        Long idCommande = Long.parseLong(getValeurChamp( request, PARAM_IDCOMMANDE ));
        String montant = getValeurChamp( request, CHAMP_MONTANT );
        String modePaiement = getValeurChamp( request, CHAMP_MODE_PAIEMENT );
        String statutPaiement = getValeurChamp( request, CHAMP_STATUT_PAIEMENT );
        String modeLivraison = getValeurChamp( request, CHAMP_MODE_LIVRAISON );
        String statutLivraison = getValeurChamp( request, CHAMP_STATUT_LIVRAISON );

        Commande commande = new Commande();

        try {
            
            traiterId(idCommande, commande);
            
            traiterClient( client, commande );

            commande.setDate(dt );

            traiterMontant( montant, commande );
            traiterModePaiement( modePaiement, commande );
            traiterStatutPaiement( statutPaiement, commande );
            traiterModeLivraison( modeLivraison, commande );
            traiterStatutLivraison( statutLivraison, commande );

            if ( erreurs.isEmpty() ) {
                commandeDao.modifier( commande );
                resultat = "Succès de la modification de la commande.";
            } else {
                resultat = "Échec de la modification de la commande.";
            }
        } catch ( DAOException e ) {
            setErreur( "imprévu", "Erreur imprévue lors de la modification." );
            resultat = "Échec de la modification de la commande : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }

        return commande;
    }

    private void traiterId( Long id, Commande commande ) {
        if ( id == null || id < 1) {
            setErreur( PARAM_IDCOMMANDE, "iD Commande incorrecte, merci d'utiliser le formulaire prévu à cet effet." );
        }
        commande.setId( id );
    }    
    
    private void traiterClient( Client client, Commande commande ) {
        if ( client == null ) {
            setErreur( CHAMP_CHOIX_CLIENT, "Client inconnu, merci d'utiliser le formulaire prévu à cet effet." );
        }
        commande.setClient( client );
    }

    private void traiterMontant( String montant, Commande commande ) {
        double valeurMontant = -1;
        try {
            valeurMontant = validationMontant( montant );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_MONTANT, e.getMessage() );
        }
        commande.setMontant(valeurMontant );
    }

    private void traiterModePaiement( String modePaiement, Commande commande ) {
        try {
            validationModePaiement( modePaiement );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_MODE_PAIEMENT, e.getMessage() );
        }
        commande.setModePaiement(modePaiement );
    }

    private void traiterStatutPaiement( String statutPaiement, Commande commande ) {
        try {
            validationStatutPaiement( statutPaiement );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_STATUT_PAIEMENT, e.getMessage() );
        }
        commande.setStatutPaiement(statutPaiement );
    }

    private void traiterModeLivraison( String modeLivraison, Commande commande ) {
        try {
            validationModeLivraison( modeLivraison );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_MODE_LIVRAISON, e.getMessage() );
        }
        commande.setModeLivraison(modeLivraison );
    }

    private void traiterStatutLivraison( String statutLivraison, Commande commande ) {
        try {
            validationStatutLivraison( statutLivraison );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_STATUT_LIVRAISON, e.getMessage() );
        }
        commande.setStatutLivraison(statutLivraison );
    }

    private double validationMontant( String montant ) throws FormValidationException {
        double temp;
        if ( montant != null ) {
            try {
                temp = Double.parseDouble( montant );
                if ( temp < 0 ) {
                    throw new FormValidationException( "Le montant doit être un nombre positif." );
                }
            } catch ( NumberFormatException e ) {
                temp = -1;
                throw new FormValidationException( "Le montant doit être un nombre." );
            }
        } else {
            temp = -1;
            throw new FormValidationException( "Merci d'entrer un montant." );
        }
        return temp;
    }

    private void validationModePaiement( String modePaiement ) throws FormValidationException {
        if ( modePaiement != null ) {
            if ( modePaiement.length() < 2 ) {
                throw new FormValidationException( "Le mode de paiement doit contenir au moins 2 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un mode de paiement." );
        }
    }

    private void validationStatutPaiement( String statutPaiement ) throws FormValidationException {
        if ( statutPaiement != null && statutPaiement.length() < 2 ) {
            throw new FormValidationException( "Le statut de paiement doit contenir au moins 2 caractères." );
        }
    }

    private void validationModeLivraison( String modeLivraison ) throws FormValidationException {
        if ( modeLivraison != null ) {
            if ( modeLivraison.length() < 2 ) {
                throw new FormValidationException( "Le mode de livraison doit contenir au moins 2 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un mode de livraison." );
        }
    }

    private void validationStatutLivraison( String statutLivraison ) throws FormValidationException {
        if ( statutLivraison != null && statutLivraison.length() < 2 ) {
            throw new FormValidationException( "Le statut de livraison doit contenir au moins 2 caractères." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}