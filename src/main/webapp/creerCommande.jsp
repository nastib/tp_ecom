<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'une commande</title>
        <!--<link type="text/css" rel="stylesheet" href="inc/style.css" />-->
        <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <style>
            body {margin-top: 20px}
            .requis, .help { color: #c00 }
        </style>
    </head>
    <body class="container">
        <div>
            <form method="post" action="creationcommande" >
                <fieldset class=" jumbotron form-row col-md-6">
                    <h2 class="display-4 text-center">Informations client</h2>
                    
                    <div class="form-group">
                        <label for="nomClient">Nom <span class="requis">*</span></label>
                        <input type="text" id="nomClient" name="nomClient" value="${nomClient}" size="20" maxlength="20" class="form-control"/>
                        <small id="nomHelp" class="help">${nomHelp}</small>
                    <div />
                    
                     <div class="form-group">
                        <label for="prenomClient">Prénom </label>
                        <input type="text" id="prenomClient" name="prenomClient" value="${prenomClient}" size="20" maxlength="20" class="form-control"/>
                        <small id="prenomHelp" class="help">${prenomHelp}</small>
                    <div />
                    
                    <div class="form-group">
                    <label for="adresseClient">Adresse de livraison <span class="requis">*</span></label>
                    <input type="text" id="adresseClient" name="adresseClient" value="${adresseClient}" size="20" maxlength="20" class="form-control"/>
                    <small id="adresseHelp" class="help">${adresseHelp}</small>
                    <div/>
    
                    <div class="form-group">
                    <label for="telephoneClient">Numéro de téléphone <span class="requis">*</span></label>
                    <input type="text" id="telephoneClient" name="telephoneClient" value="${telephoneClient}" size="20" maxlength="20" class="form-control"/>
                    <small id="telephoneHelp" class="help">${telephoneHelp}</small>
                    <div />
                    
                    <div class="form-group">
                    <label for="emailClient">Adresse email</label>
                    <input type="email" id="emailClient" name="emailClient" value="${emailClient}" size="20" maxlength="60" class="form-control"/>
                    <small id="emailHelp" class="help"></small>
                    <div />
                </fieldset>
                <fieldset class=" jumbotron form-row col-md-6">
                    <h3 class="display-4 text-center">Info. commande</h3>
                    <div class="form-group">
                    <label for="dateCommande">Date <span class="requis">*</span></label>
                    <input type="text" id="dateCommande" name="dateCommande" value="${requestScope.dateCommande}" size="20" maxlength="20" disabled class="form-control"/>
                    <small id="dateHelp" class="form-text text-muted">${message}</small>
                    </div>
                    
                    <div class="form-group">
                    <label for="montantCommande">Montant <span class="requis" >*</span></label>
                    <input type="number" id="montantCommande" name="montantCommande" value="${requestScope.montantCommande}" size="20" maxlength="20" class="form-control"/>
                    <small id="montantHelp" class="form-text text-muted">${message}</small>
                    </div>
                    
                    <div class="form-group">
                    <label for="modePaiementCommande">Mode de paiement <span class="requis">*</span></label>
                    <input type="text" id="modePaiementCommande" name="modePaiementCommande" value="${requestScope.modePaiementCommande}" size="20" maxlength="20" class="form-control"/>
                    <small id="modeHelp" class="form-text text-muted">${message}</small>
                    </div>
                    
                    <div class="form-group">
                    <label for="statutPaiementCommande">Statut du paiement</label>
                    <input type="text" id="statutPaiementCommande" name="statutPaiementCommande" value="${requestScope.statutPaiementCommande}" size="20" maxlength="20" class="form-control"/>
                    </div>
                    
                    <div class="form-group">
                    <label for="modeLivraisonCommande">Mode de livraison <span class="requis">*</span></label>
                    <input type="text" id="modeLivraisonCommande" name="modeLivraisonCommande" value="${requestScope.modeLivraisonCommande}" size="20" maxlength="20" class="form-control"/>
                    <small id="modeHelp" class="form-text text-muted">${message}</small>
                    </div>
                    
                    <div class="form-group">
                    <label for="statutLivraisonCommande">Statut de la livraison</label>
                    <input type="text" id="statutLivraisonCommande" name="statutLivraisonCommande" value="${requestScope.statutLivraisonCommande}" size="20" maxlength="20" class="form-control"/>
                    </div>
                </fieldset>
                <input type="submit" value="Valider" class="btn btn-primary btn-lg " style="margin-right: 10px" />
                <input type="reset" value="Remettre à zéro" class="btn btn-primary btn-lg "/> <br />
            </form>
        </div>
    </body>
</html>