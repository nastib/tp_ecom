
<fieldset>

    <h1 class="display-4 text-center">${formAction} d'un client</h1>

    <p class="lead text-center">Formulaire de saisie des informations sur le client.</p>
    <small id="resultat" class="erreur">${form.resultat}</small>
    <div class="col-md-12" style="display : flex">
        <div class="col-md-9">
            <div class="form-group">
                <label for="nomClient">Nom <span class="requis">*</span></label>
                <input type="text" id="nomClient" name="nomClient" value="<c:out value="${client.nomClient}"/>"  size="20" maxlength="20" class="form-control"/>
                <small id="nomHelp" class="erreur">${form.erreurs['nomClient']}</small>
            </div>

            <div class="form-group">
                <label for="prenomClient">Prénom <span class="requis">*</span></label>
                <input type="text" id="prenomClient" name="prenomClient" value="<c:out value="${client.prenomClient}"/>" size="20" maxlength="20" class="form-control"/>
                <small id="prenomHelp" class="erreur">${form.erreurs['prenomClient']}</small>
            </div>

            <div class="form-group">
                <label for="adresseClient">Adresse de livraison <span class="requis">*</span></label>
                <input type="text" id="adresseClient" name="adresseClient" value="<c:out value="${client.adresseClient}"/>" size="20" maxlength="20" class="form-control"/>
                <small id="adresseHelp" class="erreur">${form.erreurs['adresseClient']}</small>
            </div>

            <div class="form-group">
                <label for="telephoneClient">Numéro de téléphone <span class="requis">*</span></label>
                <input type="text" id="telephoneClient" name="telephoneClient" value="<c:out value="${client.telephoneClient}"/>" size="20" maxlength="20" class="form-control"/>
                <small id="telephoneHelp" class="erreur">${form.erreurs['telephoneClient']}</small>
            </div>

            <div class="form-group">
                <label for="emailClient">Adresse email</label>
                <input type="text" id="emailClient" name="emailClient" value="<c:out value="${client.emailClient}"/>" size="20" maxlength="60" class="form-control"/>
                <small id="emailHelp" class="erreur">${form.erreurs['emailClient']}</small>
            </div>

            <div class="form-group">
                <label for="image" class="custom-control-label">Emplacement du fichier <span class="requis">*</span></label>
                <input type="file" id="image" name="image" class="form-control" />
                <!-- Champ caché pour gérer la modification sans changer l'image -->
                <input hidden type="text" id="nomImage" name="nomImage" value="<c:out value="${client.image}"/>"/>
                <small class="erreur">${form.erreurs['image']}</small>
            </div>  
        </div>
        <div class="col-md-3">
            <c:if test="${!empty client.image}">
                <a href="<c:out value="/fichiers/${client.image}"/>"><img src="resources/fichiers/${client.image}" alt="photo" class="float-right" style="width: 120px; height: 120px; border-radius: 10px"/></a>
                </c:if>
        </div>  
    </div>
</fieldset>

