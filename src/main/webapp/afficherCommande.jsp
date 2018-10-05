
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Fiche d'une commande</title>
        <!--<link type="text/css" rel="stylesheet" href="inc/style.css" />-->
        <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <style>
            .requis { color: #c00 }
            body {margin-top: 20px}
        </style>
    </head>
    <body>
        <div class="container">
            <div><%@ include file ="inc/menu1.jsp" %></div>
            <div class="col-md-6">                 
                <h1 class="display-4 text-center">Fiche commande</h1>
                <small id="submitHelp" class="badge badge-success">${message}</small>
                <table class="table">

                    <tbody>
                        <tr>
                            <td colspan="2"><b>** Client **</b></td>
                        </tr>
                        <tr>
                            <td>Nom :</td>
                            <td class="text-left"><c:out value="${requestScope.client.nomClient}"/></td>
                        </tr>
                        <tr>
                            <td>Prenom :</td>
                            <td class="text-left"><c:out value="${requestScope.client.prenomClient}"/></td>
                        </tr>
                        <tr>
                            <td>Adresse : </td>
                            <td class="text-left"><c:out value="${requestScope.client.adresseClient}"/></td>
                        </tr>
                        <tr>
                            <td>Télephone : </td>
                            <td class="text-left"><c:out value="${requestScope.client.telephoneClient}"/></td>
                        </tr>
                        <tr>
                            <td>Email : </td>
                            <td class="text-left"><c:out value="${requestScope.client.emailClient}"/></td>
                        </tr>   
                        <tr>
                            <td colspan="2"><b>** Commande **</b></td>
                        </tr>
                        <tr>
                            <td>Date :</td>
                            <td class="text-left">${requestScope.commande.dateCommande}</td>
                        </tr>
                        <tr>
                            <td>Montant :</td>
                            <td class="text-left">${requestScope.commande.montantCommande}</td>
                        </tr>   
                        <tr>
                            <td>Mode paiement :</td>
                            <td class="text-left"><c:out value="${requestScope.commande.modePaiementCommande}"/></td>
                        </tr>
                        <tr>
                            <td>Statut de paiement : </td>
                            <td class="text-left"><c:out value="${requestScope.commande.statutPaiementCommande}"/></td>
                        </tr>
                        <tr>
                            <td>Mode livraison : </td>
                            <td class="text-left"><c:out value="${requestScope.commande.modeLivraisonCommande}"/></td>
                        </tr>
                        <tr>
                            <td>Statut livraison : </td>
                            <td class="text-left"><c:out value="${requestScope.commande.statutLivraisonCommande}"/></td>
                        </tr>                         
                    </tbody>
                </table>                              
                        

            </div>

        </div>
    </body>
</html>
