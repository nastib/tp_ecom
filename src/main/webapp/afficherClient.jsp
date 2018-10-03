<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Fiche d'un client</title>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
        <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <style>
            .requis { color: #c00 }
            body {margin-top: 20px}
        </style>
    </head>
    <body>
        <div class="container">
            <form method="get"  action="creerClient.jsp">
                <div class="col-md-6 jumbotron">
                   
                    <h1 class="display-4 text-center">Fiche du client</h1>
                    <p class="lead text-center">Fiche d'informations sur le client.</p>
                    <small id="submitHelp" class="badge badge-success">${message}</small>
                    <table class="table">

                      <tbody>
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
                      </tbody>
                    </table>                              


                </div>

                <input type="submit" value="Retour"  class="btn btn-primary btn-lg"/> <br />
            </form>
        </div>
    </body>
</html>