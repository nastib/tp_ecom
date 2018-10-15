package com.nastib.tpecom.forms;

import com.nastib.tpecom.beans.BeanException;
import com.nastib.tpecom.beans.Client;
import eu.medsea.mimeutil.MimeUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


public class ClientForm extends ValidateFormImpl implements ValidateForm {

    public static final String CHAMP_NOM = "nomClient";
    public static final String CHAMP_PRENOM = "prenomClient";
    public static final String CHAMP_ADRESSE = "adresseClient";
    public static final String CHAMP_TELEPHONE = "telephoneClient";
    public static final String CHAMP_EMAIL = "emailClient";
    public static final String ATT_SESSION_LISTECLIENTS = "listeClients";
    public static final String CHAMP_NUMERO = "numero";
    private static final String CHAMP_IMAGE = "image";
    private static final String CHAMP_NOMIMAGE = "nomImage";
    private static final int TAILLE_TAMPON = 10240;

    
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private Client client;
    private String nomImage;
    private String nomFichier = null;

    private InputStream contenuFichier = null;

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
    public Client saveClient(HttpServletRequest request, String chemin) {

        // Params Form-data
        nom = getValeurChamp(request, CHAMP_NOM);
        prenom = getValeurChamp(request, CHAMP_PRENOM);
        adresse = getValeurChamp(request, CHAMP_ADRESSE);
        telephone = getValeurChamp(request, CHAMP_TELEPHONE);
        email = getValeurChamp(request, CHAMP_EMAIL);
        nomImage = getValeurChamp(request, CHAMP_NOMIMAGE);
        //Récupération du contenu du champ fichier du formulaire. Il faut ici
        this.getValeurChampFichier(request);
 
        //Params Query String
        String num = getValeurChamp(request, CHAMP_NUMERO);

        //Validation du formilaire
        if (erreurs.isEmpty()) {
            this.valideForm();
        }
       
        /* Écriture du fichier sur le disque */
        if (erreurs.isEmpty()) {
            
            try {              
                if(!nomFichier.isEmpty()){this.ecrireFichier(contenuFichier, nomFichier, chemin);}
            } catch (Exception e) {
                
                this.setErreur(CHAMP_IMAGE, "Erreur lors de l'écriture du fichier sur le disque.");
            }
        }

        //Enregistrement du formulaire
        if (erreurs.isEmpty()) {
            client = updateBeanClient(new Client());
        }

        if (erreurs.isEmpty()) {

            resultat = "Client " + client.getNomClient() + " créé avec succès.";
            if (num == null) {
                //Persiste le client dans la collection de clients en session
                request.setAttribute(ATT_SESSION_LISTECLIENTS, addClientToList(request, client));
            } else {
                //Persiste le client dans la collection de clients en session;
                request.setAttribute(ATT_SESSION_LISTECLIENTS, editClientToList(request, client, Integer.parseInt(num)));
            }
        } else {           
             if(nom == null){
                 resultat = "Échec de la création du client" ;
             } else {    
                 resultat = "Échec de la création du client " +nom+ " "+prenom;
             }
        }

        return client;
    }

    /**
     * Sauvegarde du formualaire dans le bean Client
     */
    private Client updateBeanClient(Client client) {

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
        //Gestion de la modification du formulaire sans le champ image
        if (nomFichier != null && !nomFichier.trim().isEmpty()) {
            client.setImage(nomFichier);
        } else {
            if(nomImage != null ){client.setImage(nomImage);}
        }

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
                if ((numero - 1) == i) {
                    listeClients.remove(i);
                    session.setAttribute(ATT_SESSION_LISTECLIENTS, listeClients);
                }
            }
        }
    }

    /**
     * Validation du formulaire ClientForm
     */
    private boolean valideForm() {

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

        /* Validation du champ fichier image. */
        try {
            this.validationFichier(nomFichier, contenuFichier);
        } catch (Exception e) {
            setErreur(CHAMP_IMAGE, e.getMessage());
        }

        return getErreurs().size() > 0;

    }

    private List<Client> addClientToList(HttpServletRequest request, Client client) {
        //Récupération de la collection de client en session scope
        HttpSession session = request.getSession();
        if (session.getAttribute(ATT_SESSION_LISTECLIENTS) == null) {
            session.setAttribute(ATT_SESSION_LISTECLIENTS, (new ArrayList<>()));
        }
        List<Client> listeClients = (List<Client>) session.getAttribute(ATT_SESSION_LISTECLIENTS);
        listeClients.add(client);
        return listeClients;
    }

    private List<Client> editClientToList(HttpServletRequest request, Client client, int numero) {
        HttpSession session = request.getSession();
        List<Client> listeClients = (List<Client>) session.getAttribute(ATT_SESSION_LISTECLIENTS);
        if (listeClients != null) {
            for (int i = 0; i < listeClients.size(); i++) {
                if ((numero - 1) == i) {
                    listeClients.set(i, client);
                }
            }
        }
        return listeClients;

    }

    // Utilitaire de file upload
    /*
     * Méthode utilitaire qui a pour unique but d'analyser l'en-tête
     * "content-disposition", et de vérifier si le paramètre "filename" y est
     * présent. Si oui, alors le champ traité est de type File et la méthode
     * retourne son nom, sinon il s'agit d'un champ de formulaire classique et
     * la méthode retourne null.
     */
    private static String getNomFichier(Part part) {
        /* Boucle sur chacun des paramètres de l'en-tête "content-disposition". */
        for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
            /* Recherche de l'éventuelle présence du paramètre "filename". */
            if (contentDisposition.trim().startsWith("filename")) {
                /*
                 * Si "filename" est présent, alors renvoi de sa valeur,
                 * c'est-à-dire du nom de fichier sans guillemets.
                 */
                return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        /* Et pour terminer, si rien n'a été trouvé... */
        return null;
    }

    /*
     * Méthode utilitaire qui a pour but d'écrire le fichier passé en paramètre
     * sur le disque, dans le répertoire donné et avec le nom donné.
     */
    private void ecrireFichier(InputStream contenu, String nomFichier, String chemin) throws Exception {
        /* Prépare les flux. */
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            /* Ouvre les flux. */
            entree = new BufferedInputStream(contenu, TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)),
                    TAILLE_TAMPON);

            /*
             * Lit le fichier reçu et écrit son contenu dans un fichier sur le
             * disque.
             */
            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur = 0;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }

    private void getValeurChampFichier(HttpServletRequest request) {
        /*
         * Récupération du contenu du champ fichier du formulaire. Il faut ici
         * utiliser la méthode getPart(), comme nous l'avions fait dans notre
         * servlet auparavant.
         */

        try {
            Part part = request.getPart(CHAMP_IMAGE);
            /*
             * Il faut déterminer s'il s'agit bien d'un champ de type fichier :
             * on délègue cette opération à la méthode utilitaire
             * getNomFichier().
             */
            nomFichier = getNomFichier(part);
            
            /*
             * Si la méthode a renvoyé quelque chose, il s'agit donc d'un
             * champ de type fichier (input type="file").
             */
            if (nomFichier != null && !nomFichier.isEmpty()) {
                /*
                 * Antibug pour Internet Explorer, qui transmet pour une
                 * raison mystique le chemin du fichier local à la machine
                 * du client...
                 * 
                 * Ex : C:/dossier/sous-dossier/fichier.ext
                 * 
                 * On doit donc faire en sorte de ne sélectionner que le nom
                 * et l'extension du fichier, et de se débarrasser du
                 * superflu.
                 */
                nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                        .substring(nomFichier.lastIndexOf('\\') + 1);

                /* Récupération du contenu du fichier */
                contenuFichier = part.getInputStream();
                
                /* Extraction du type MIME du fichier depuis l'InputStream nommé "contenu" */
                MimeUtil.registerMimeDetector( "eu.medsea.mimeutil.detector.MagicMimeMimeDetector" );
                Collection<?> mimeTypes = MimeUtil.getMimeTypes( contenuFichier );

                /*
                 * Si le fichier est bien une image, alors son en-tête MIME
                 * commence par la chaîne "image"
                 */
                if ( !mimeTypes.toString().startsWith( "image" ) ) {
                    setErreur(CHAMP_IMAGE, "Le fichier envoyé n'est pas une image.");
                    /* Appeler ici la méthode d'écriture du fichier sur le disque... */
                }

            }
        } catch (IllegalStateException e) {
            /*
             * Exception retournée si la taille des données dépasse les limites
             * définies dans la section <multipart-config> de la déclaration de
             * notre servlet d'upload dans le fichier web.xml
             */
            e.printStackTrace();
            setErreur(CHAMP_IMAGE, "Les données envoyées sont trop volumineuses.");
        } catch (IOException e) {
            /*
             * Exception retournée si une erreur au niveau des répertoires de
             * stockage survient (répertoire inexistant, droits d'accès
             * insuffisants, etc.)
             */
            e.printStackTrace();
            setErreur(CHAMP_IMAGE, "Erreur de configuration du serveur.");
        } catch (ServletException e) {
            /*
             * Exception retournée si la requête n'est pas de type
             * multipart/form-data. Cela ne peut arriver que si l'utilisateur
             * essaie de contacter la servlet d'upload par un formulaire
             * différent de celui qu'on lui propose... pirate ! :|
             */
            e.printStackTrace();
            setErreur(CHAMP_IMAGE,
                    "Ce type de requête n'est pas supporté, merci d'utiliser le formulaire prévu pour envoyer votre fichier.");
        }

    }

}
