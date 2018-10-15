
package com.nastib.tpecom.forms;

import java.io.InputStream;

public interface ValidateForm {
    /**
     * Méthode générique de validation d'un champ string du formulaire
     * @param name
     * @param champ
     * @param caract
     * @throws ValidationException 
     */
    public  void validateStringControle(String name, String champ, int caract) throws ValidationException;
    
    /**
     * Méthode générique de validation d'un champ numérique du formulaire
     * @param name
     * @param champ
     * @param caract
     * @throws ValidationException 
     */
    public void validateNombreControle(String name, String champ, int caract) throws ValidationException;
    
    /**
     * 
     * Méthode générique de validation d'un champ email du formulaire
     * @param name
     * @param champ
     * @throws ValidationException 
     */
    public void validateEmailControle(String name, String champ) throws ValidationException;
    
    /**
     * Méthode générique de validation d'un champ double du formulaire
     * @param name
     * @param champ
     * @return
     * @throws ValidationException 
     */
    public void validateDoubleControle(String name, String champ) throws ValidationException;

    /**
     * Méthode générique de validation d'un champ double du formulaire
     * @param name
     * @param champ
     * @return
     * @throws ValidationException 
     */
    public void validateDateControle(String name, String champ) throws ValidationException;    
    
    public void validationFichier( String nomFichier, InputStream contenuFichier ) throws Exception; 
}
