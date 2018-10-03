<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Fiche d'une commande</title>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
        <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <style>
            .requis { color: #c00 }
            body {margin-top: 20px}
        </style>
    </head>
    <body>
        <div class="container">
            <form method="get"  action="creerCommande.jsp">
                <div class="col-md-6 jumbotron">
                   
                    <h1 class="display-4 text-center">Fiche commande</h1>
                    <small id="submitHelp" class="badge badge-success">${message}</small>
                    <table class="table">

                      <tbody>
                        <tr>
                            <td class="text-center" colspan="2"><b>Client</b></td>
                        </tr>
                        <tr>
                          <td>Nom :</td>
                          <td class="text-left">${requestScope.nomClient}</td>
                        </tr>
                        <tr>
                           <td>Prenom :</td>
                          <td class="text-left">${requestScope.prenomClient}</td>
                        </tr>
                        <tr>
                          <td>Adresse : </td>
                          <td class="text-left">${requestScope.adresseClient}</td>
                        </tr>
                        <tr>
                          <td>Télephone : </td>
                          <td class="text-left">${requestScope.telephoneClient}</td>
                        </tr>
                        <tr>
                          <td>Email : </td>
                          <td class="text-left">${requestScope.emailClient}</td>
                        </tr>   
                        <tr>
                            <td class="text-center" colspan="2"><b>Commande</b></td>
                        </tr>
                        <tr>
                          <td>Date :</td>
                          <td class="text-left">${requestScope.dateCommande}</td>
                        </tr>
                        <tr>
                           <td>Montant :</td>
                          <td class="text-left">${requestScope.montantCommande}</td>
                        </tr>   
                        <tr>
                           <td>Mode paiement :</td>
                          <td class="text-left">${requestScope.modePaiementCommande}</td>
                        </tr>
                        <tr>
                          <td>Statut de paiement : </td>
                          <td class="text-left">${requestScope.statutPaiementCommande}</td>
                        </tr>
                        <tr>
                          <td>Mode livraison : </td>
                          <td class="text-left">${requestScope.modeLivraisonCommande}</td>
                        </tr>
                        <tr>
                          <td>Statut livraison : </td>
                          <td class="text-left">${requestScope.statutLivraisonCommande}</td>
                        </tr>                         
                      </tbody>
                    </table>                              


                </div>

                <input type="submit" value="Retour"  class="btn btn-primary btn-lg"/> <br />
            </form>
        </div>
    </body>
</html>
