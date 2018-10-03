<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'un client</title>
        <!--<link type="text/css" rel="stylesheet" href="inc/style.css" />-->
        <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <style>
            body {margin-top: 20px}
            .requis, .help { color: #c00 }
        </style>
    </head>
    <body>
        <div class="container">
            <form method="post" action="creationclient" class="form-row col-md-6">
                <fieldset class=" jumbotron">
                    <h1 class="display-4 text-center">Création d'un client</h1>
                    <p class="lead text-center">Formulaire de saisie des informations sur le client.</p>
                    <small id="submitHelp" class="form-text text-muted">${message}</small>
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
                    <div />
    
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
                <input type="submit" value="Valider"  class="btn btn-primary btn-lg " style="margin-right: 10px" />
                <input type="reset" value="Remettre à zéro" class="btn btn-primary btn-lg"/> <br />
            </form>
        </div>
    </body>
</html>