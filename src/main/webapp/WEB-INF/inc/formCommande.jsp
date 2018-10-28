<h1 class="display-4 text-center">Saisie des Commandes</h1>
<p class="lead text-center">Formulaire de saisie des commandes.</p>
<fieldset >

    <legend >Information client</legend>
    <div class="form-group">
    <label for="choixNouveauClient">Nouveau client ? <span class="requis">*</span></label>
    <input type="radio" id="choixNouveauClient" name="choixNouveauClient" value="nouveauClient"   /> Oui
    <input type="radio" id="choixAncierClient" name="choixNouveauClient" value="ancienClient"  checked /> Non
    </div>

    <div class="form-group">
        <div  id="nouveauClient">
            <div class="form-group">
                <span style=" display: inline-block; width: 555px"><b>Cliquez sur le bouton (+) pour ajouter un client  </b></span>
                <a href="<c:url value="/creationclient"/>" class="btn btn-outline-primary " style="padding-top:12px; width: 50px; height: 50px; border-radius: 25px; "><i class="fas fa-plus" ></i></a>
            </div>
        </div>        
        <div id="ancienClient">    

            <label for="client">Client  </label>
            <select name="client" id="client" class="form-control"> 
                <option value="0L"> Choisissez un client ... </option> 
                <c:forEach  var="client" items="${sessionScope.clients}" varStatus="status" >
                    <option value="${client.value.id}" <c:if test="${client.value.id eq commande.client.id}" var="result"> selected="selected" </c:if>> ${client.value.id} - ${client.value.nom} ${client.value.prenom} </option> 
                </c:forEach>
            </select>
            <small id="dateHelp" class="erreur">${form.erreurs['client']}</small>
        </div>
    </div>        

</fieldset>

<fieldset >
    <legend>Information commande</legend>
    <div class="form-group">
        <label for="date">Date <span class="requis">*</span></label>
        <input type="text" id="date" name="date" value="<joda:format value='${commande.date }' pattern='dd/MM/yyyy HH:mm:ss'/>" size="20" maxlength="20" disabled class="form-control"/>
        <small id="dateHelp" class="erreur">${form.erreurs['date']}</small>
    </div>

    <div class="form-group">
        <label for="montant">Montant <span class="requis" >*</span></label>
        <input type="text" id="montant" name="montant" value="<c:out value="${commande.montant}"/>" size="20" maxlength="20" class="form-control"  />
        <small id="montantHelp" class="erreur">${form.erreurs["montant"]}</small>
    </div>

    <div class="form-group">
        <label for="modePaiement">Mode de paiement <span class="requis">*</span></label>
        <input type="text" id="modePaiement" name="modePaiement" value="<c:out value="${commande.modePaiement}"/>" size="20" maxlength="20" class="form-control"/>
        <small id="paiementHelp" class="erreur">${form.erreurs['modePaiement']}</small>
    </div>

    <div class="form-group">
        <label for="statutPaiement">Statut du paiement</label>
        <input type="text" id="statutPaiement" name="statutPaiement" value="<c:out value="${commande.statutPaiement}"/>" size="20" maxlength="20" class="form-control"/>
        <small id="livraisonHelp" class="erreur">${form.erreurs['statutPaiement']}</small>
    </div>

    <div class="form-group">
        <label for="modeLivraison">Mode de livraison <span class="requis">*</span></label>
        <input type="text" id="modeLivraison" name="modeLivraison" value="<c:out value="${commande.modeLivraison}"/>" size="20" maxlength="20" class="form-control"/>
        <small id="livraisonHelp" class="erreur">${form.erreurs['modeLivraison']}</small>
    </div>

    <div class="form-group">
        <label for="statutLivraison">Statut de la livraison</label>
        <input type="text" id="statutLivraison" name="statutLivraison" value="<c:out value="${commande.statutLivraison}"/>" size="20" maxlength="20" class="form-control"/>
        <small id="livraisonHelp" class="erreur">${form.erreurs["statutLivraison"]}</small>
    </div>
    
</fieldset>
