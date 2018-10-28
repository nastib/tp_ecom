
<fieldset>

    <h1 class="display-4 text-center">${formAction} d'un client</h1>

    <p class="lead text-center">Formulaire de saisie des informations sur le client.</p>
    <small id="resultat" class="erreur">${form.resultat}</small>
    <div class="col-md-12" style="display : flex">
        <div class="col-md-9">
            <div class="form-group">
                <label for="nom">Nom <span class="requis">*</span></label>
                <input type="text" id="nom" name="nom" value="<c:out value="${client.nom}"/>"  size="20" maxlength="20" class="form-control"/>
                <small id="nomHelp" class="erreur">${form.erreurs['nom']}</small>
            </div>

            <div class="form-group">
                <label for="prenom">Prénom <span class="requis">*</span></label>
                <input type="text" id="prenom" name="prenom" value="<c:out value="${client.prenom}"/>" size="20" maxlength="20" class="form-control"/>
                <small id="prenomHelp" class="erreur">${form.erreurs['prenom']}</small>
            </div>

            <div class="form-group">
                <label for="adresse">Adresse de livraison <span class="requis">*</span></label>
                <input type="text" id="adresse" name="adresse" value="<c:out value="${client.adresse}"/>" size="20" maxlength="20" class="form-control"/>
                <small id="adresseHelp" class="erreur">${form.erreurs['adresse']}</small>
            </div>
            <div class="form-group">
                <label for="telephone">Numéro de téléphone <span class="requis">*</span></label>
                <input type="text" id="telephone" name="telephone" value="<c:out value="${requestScope.client.telephone}"/>" size="20" maxlength="20" class="form-control"/>
                <small id="telephoneHelp" class="erreur">${form.erreurs['telephone']}</small>
            </div>

            <div class="form-group">
                <label for="email">Adresse email</label>
                <input type="text" id="email" name="email" value="<c:out value="${client.email}"/>" size="20" maxlength="60" class="form-control"/>
                <small id="emailHelp" class="erreur">${form.erreurs['email']}</small>
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
                <a href="<c:out value="resources/fichiers/images/${client.image}"/>"><img src="resources/fichiers/images/${client.image}" alt="photo" class="float-right" style="width: 120px; height: 120px; border-radius: 10px"/></a>
                </c:if>
        </div>  
    </div>
</fieldset>

