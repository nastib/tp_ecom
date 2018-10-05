<fieldset class="form-row col-md-6">
    <h3 class="display-4 text-center">Info. commande</h3>
    <div class="form-group">
        <label for="dateCommande">Date <span class="requis">*</span></label>
        <input type="text" id="dateCommande" name="dateCommande" value="${requestScope.commande.dateCommande}" size="20" maxlength="20" disabled class="form-control"/>
        <small id="dateHelp" class="form-text text-muted">${message}</small>
    </div>

    <div class="form-group">
        <label for="montantCommande">Montant <span class="requis" >*</span></label>
        <input type="number" id="montantCommande" name="montantCommande" value="${param.montantCommande}" size="20" maxlength="20" class="form-control" required />
        <small id="montantHelp" class="help">${montantHelp}</small>
    </div>

    <div class="form-group">
        <label for="modePaiementCommande">Mode de paiement <span class="requis">*</span></label>
        <input type="text" id="modePaiementCommande" name="modePaiementCommande" value="${param.modePaiementCommande}" size="20" maxlength="20" class="form-control"/>
        <small id="paiementHelp" class="help">${paiementHelp}</small>
    </div>

    <div class="form-group">
        <label for="statutPaiementCommande">Statut du paiement</label>
        <input type="text" id="statutPaiementCommande" name="statutPaiementCommande" value="${param.statutPaiementCommande}" size="20" maxlength="20" class="form-control"/>
    </div>

    <div class="form-group">
        <label for="modeLivraisonCommande">Mode de livraison <span class="requis">*</span></label>
        <input type="text" id="modeLivraisonCommande" name="modeLivraisonCommande" value="${param.modeLivraisonCommande}" size="20" maxlength="20" class="form-control"/>
        <small id="livraisonHelp" class="help">${livraisonHelp}</small>
    </div>

    <div class="form-group">
        <label for="statutLivraisonCommande">Statut de la livraison</label>
        <input type="text" id="statutLivraisonCommande" name="statutLivraisonCommande" value="${param.statutLivraisonCommande}" size="20" maxlength="20" class="form-control"/>
    </div>
</fieldset>
