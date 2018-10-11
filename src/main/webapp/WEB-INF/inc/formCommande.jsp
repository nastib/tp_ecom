<h1 class="display-4 text-center">Saisie des Commandes</h1>
<p class="lead text-center">Formulaire de saisie des commandes.</p>
<fieldset >

    <legend >Information client</legend>

        <label for="choixNouveauClient">Nouveau client ? <span class="requis">*</span></label>
        <input type="radio" id="choixNouveauClient" name="choixNouveauClient" value="nouveauClient"   /> Oui
        <input type="radio" id="choixAncierClient" name="choixNouveauClient" value="ancienClient"  checked /> Non
        <br/>
 
  
    <div id="nouveauClient">

        <div class="form-check form-check-inline">
            <table>
                <tr>
                    <td style="width:630px"><label>Cliquez le bouton plus pour ajouter un client  </label></td>
                    <td style="width:50px ;text-align:right"><a href="<c:url value="/creationclient"/>" class="btn btn-outline-primary " style="padding-top:12px; width: 50px; height: 50px; border-radius: 25px; "><i class="fas fa-plus" ></i></a></td>
                </tr>
            </table>
        </div>
    </div>        


    <div id="ancienClient">    
        <div class="form-group">
            <label for="client">Id Client  </label>
            <select name="client" id="client" class="form-control"> 
                <option value="o"> Choisissez un client ... </option> 
                <c:forEach  var="clt" items="${sessionScope.listeClients}" varStatus="status" >
                    <option value="${status.count}" <c:if test="${status.count eq param.numero}" var="result"> selected="selected" </c:if>> ${status.count} - ${clt.nomClient} ${clt.prenomClient} </option> 
                </c:forEach>
            </select>
            <small id="dateHelp" class="erreur">${form.erreurs['client']}</small>
        </div>
    </div>        

</fieldset>

<fieldset >
    <legend>Information commande</legend>
    <div class="form-group">
        <label for="dateCommande">Date <span class="requis">*</span></label>
        <input type="text" id="dateCommande" name="dateCommande" value="<c:out value="${commande.dateCommande}"/>" size="20" maxlength="20" disabled class="form-control"/>
        <small id="dateHelp" class="erreur">${form.erreurs['dateCommande']}</small>
    </div>

    <div class="form-group">
        <label for="montantCommande">Montant <span class="requis" >*</span></label>
        <input type="text" id="montantCommande" name="montantCommande" value="<c:out value="${commande.montantCommande}"/>" size="20" maxlength="20" class="form-control"  />
        <small id="montantHelp" class="erreur">${form.erreurs["montantCommande"]}</small>
    </div>

    <div class="form-group">
        <label for="modePaiementCommande">Mode de paiement <span class="requis">*</span></label>
        <input type="text" id="modePaiementCommande" name="modePaiementCommande" value="<c:out value="${commande.modePaiementCommande}"/>" size="20" maxlength="20" class="form-control"/>
        <small id="paiementHelp" class="erreur">${form.erreurs['modePaiementCommande']}</small>
    </div>

    <div class="form-group">
        <label for="statutPaiementCommande">Statut du paiement</label>
        <input type="text" id="statutPaiementCommande" name="statutPaiementCommande" value="<c:out value="${commande.statutPaiementCommande}"/>" size="20" maxlength="20" class="form-control"/>
        <small id="livraisonHelp" class="erreur">${form.erreurs['statutPaiementCommande']}</small>
    </div>

    <div class="form-group">
        <label for="modeLivraisonCommande">Mode de livraison <span class="requis">*</span></label>
        <input type="text" id="modeLivraisonCommande" name="modeLivraisonCommande" value="<c:out value="${commande.modeLivraisonCommande}"/>" size="20" maxlength="20" class="form-control"/>
        <small id="livraisonHelp" class="erreur">${form.erreurs['modeLivraisonCommande']}</small>
    </div>

    <div class="form-group">
        <label for="statutLivraisonCommande">Statut de la livraison</label>
        <input type="text" id="statutLivraisonCommande" name="statutLivraisonCommande" value="<c:out value="${commande.statutLivraisonCommande}"/>" size="20" maxlength="20" class="form-control"/>
        <small id="livraisonHelp" class="erreur">${form.erreurs["statutLivraisonCommande"]}</small>
    </div>
</fieldset>
