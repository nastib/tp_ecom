

    <fieldset class="form-row col-md-6">
        <h1 class="display-4 text-center">Création d'un client</h1>
        <p class="lead text-center">Formulaire de saisie des informations sur le client.</p>
        <small id="submitHelp" class="form-text text-muted">${message}</small>
        <div class="form-group">
            <label for="nomClient">Nom <span class="requis">*</span></label>
            <input type="text" id="nomClient" name="nomClient" value="${param.nomClient}" size="20" maxlength="20" class="form-control"/>
            <small id="nomHelp" class="help">${nomHelp}</small>
        <div />

         <div class="form-group">
            <label for="prenomClient">Prénom </label>
            <input type="text" id="prenomClient" name="prenomClient" value="${param.prenomClient}" size="20" maxlength="20" class="form-control"/>
            <small id="prenomHelp" class="help">${prenomHelp}</small>
        <div />

        <div class="form-group">
        <label for="adresseClient">Adresse de livraison <span class="requis">*</span></label>
        <input type="text" id="adresseClient" name="adresseClient" value="${param.adresseClient}" size="20" maxlength="20" class="form-control"/>
        <small id="adresseHelp" class="help">${adresseHelp}</small>
        <div />

        <div class="form-group">
        <label for="telephoneClient">Numéro de téléphone <span class="requis">*</span></label>
        <input type="text" id="telephoneClient" name="telephoneClient" value="${param.telephoneClient}" size="20" maxlength="20" class="form-control"/>
        <small id="telephoneHelp" class="help">${telephoneHelp}</small>
        <div />

        <div class="form-group">
        <label for="emailClient">Adresse email</label>
        <input type="email" id="emailClient" name="emailClient" value="${param.emailClient}" size="20" maxlength="60" class="form-control"/>
        <small id="emailHelp" class="help"></small>
        <div />
    </fieldset>

