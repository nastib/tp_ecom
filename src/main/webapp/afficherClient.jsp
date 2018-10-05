
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Fiche d'un client</title>
        <!--<link type="text/css" rel="stylesheet" href="inc/style.css" />-->
        <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <style>
            .requis { color: #c00 }
            body {margin-top: 20px}
            .colon { font-weight: bold}
        </style>
    </head>
    <body>
        <div class="container">
            <div><%@ include file ="inc/menu1.jsp" %></div>
            <div class="col-md-6">                    
                <h1 class="display-4 text-center">Fiche du client</h1>
                <p class="lead text-center">Fiche d'informations sur le client.</p>
                <small id="submitHelp" class="badge badge-success">${message}</small>
                <table class="table">
                    <tbody>
            
                        <tr>
                            <td class="colon">Nom :</td>
                            <td class="text-left"><c:out value="${requestScope.client.nomClient}"/></td>
                        </tr>
                        <tr>
                            <td class="colon">Prenom :</td>
                            <td class="text-left"><c:out value="${requestScope.client.prenomClient}"/></td>
                        </tr>
                        <tr>
                            <td class="colon">Adresse : </td>
                            <td class="text-left"><c:out value="${requestScope.client.adresseClient}"/></td>
                        </tr>
                        <tr>
                            <td class="colon">Télephone : </td>
                            <td class="text-left"><c:out value="${requestScope.client.telephoneClient}"/></td>
                        </tr>
                        <tr>
                            <td class="colon">Email : </td>
                            <td class="text-left"><c:out value="${requestScope.client.emailClient}"/></td>
                        </tr>                        
                    </tbody>
                </table>                              
            </div>
        </div>
    </body>
</html>