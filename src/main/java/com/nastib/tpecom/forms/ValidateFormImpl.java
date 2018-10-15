
package com.nastib.tpecom.forms;

import java.io.InputStream;


public class ValidateFormImpl implements ValidateForm {
     /**
     * Méthode générique de validation d'un champ string du formulaire
     * @param name
     * @param champ
     * @param caract
     * @throws ValidationException 
     */
    @Override
    public void validateStringControle(String name, String champ, int caract) throws ValidationException{
        if(name == null){
            throw new ValidationException("Erreur ! le "+champ+" est obligatoire ! ");
        } else if (name.trim().length() < caract){
            throw new ValidationException("Erreur ! le "+champ+" doit contenir au moins "+caract+" caractères. ");
        }
    }

    /**
     * Méthode générique de validation d'un champ numérique du formulaire
     * @param name
     * @param champ
     * @param caract
     * @throws ValidationException 
     */
    public void validateNombreControle(String name, String champ, int caract) throws ValidationException{
        if(name != null){
             if ( !name.matches( "^\\d+$" ) ) {
                 throw new ValidationException("Erreur ! le "+champ+" est numérique ! ");
             } else if ( name.length() < caract ) {
                throw new ValidationException("Erreur ! le "+champ+" doit contenir au moins "+caract+" caractères.");
             }
            
        } else {
            throw new ValidationException("Erreur ! le "+champ+" est obligatoire. ");
        }      
    }  
    
    /**
     * 
     * @param name
     * @param champ
     * @return
     * @throws ValidationException 
     */
    @Override
    public void validateDoubleControle(String name, String champ) throws ValidationException{
        Double temp;
        if ( name != null ) {            
            try {
                 temp = Double.parseDouble(name);
                 if ( temp < 0 ) {
                    throw new ValidationException( "Le "+champ+" doit être un nombre positif." );
                }
            } catch ( NumberFormatException e ) {
                throw new ValidationException( "Le "+champ+" doit être un nombre." );
            }
        } else {
            throw new ValidationException( "Merci d'entrer un "+champ );
        }
        
        //return temp;

    }
    
    /**
     * Méthode générique de validation d'un champ email du formulaire
     * @param name
     * @param champ
     * @throws ValidationException 
     */
    @Override
    public void validateEmailControle(String name, String champ) throws ValidationException{
        String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        //if ( name != null && !name.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
        if ( name != null && !name.matches( pattern ) ) {
            throw new ValidationException( "Erreur ! l\' "+champ+ " n\' est pas un mail valide." );
        }      
    }    
    /**
     * Méthode générique de validation d'un champ email du formulaire
     * @param name
     * @param champ
     * @throws ValidationException 
     */
    @Override
    public void validateDateControle(String name, String champ) throws ValidationException{
        String pattern = "[0-9]{1,2}(/|-)[0-9]{1,2}(/|-)[0-9]{4}";
        if ( name != null && !name.matches( pattern ) ) {
            throw new ValidationException( "Erreur ! l\' "+champ+ " n\' est pas une date valide." );
        }      
    } 
    /*
     * Valide le fichier envoyé.
     */
    public void validationFichier( String nomFichier, InputStream contenuFichier ) throws Exception {
        if ( nomFichier != null && !nomFichier.trim().isEmpty() ){
            if( nomFichier.trim().length() < 6 ){
                throw new Exception( "Attention ! le nom du fichier à envoyer doit avoir au moins 6 caractères." );
            } else if(contenuFichier == null){
                throw new Exception( "Attention ! Le fichier est vide. " );
            }
        }
    } 
}
