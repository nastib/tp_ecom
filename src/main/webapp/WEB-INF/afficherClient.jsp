
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Fiche d'un client</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" />
        <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"/>
        <style>
            .btn { width: 50px; height: 50px; border-radius: 25px; }
            .colon { font-weight: bold}
        </style>
    </head>
    <body>
        <div class="container col-md-10">
            <div><%@ include file ="inc/menu.jsp" %></div>

            <h1 class="display-4 text-center">Fiche du client</h1>
            <p class="lead text-center">Fiche d'informations sur le client.</p>
           
            <div class="col-md-8 offset-md-2">
            <div class="row">
                <div class="col-md-11">
                    <small id="submitHelp" class="badge badge-success">${message}</small>
                </div>
                <div class="col-md-1 align-rigth" >
                    <a href="<c:url value="/print#"/>" class="btn btn-outline-primary " style="padding-top:12px"><i class="fas fa-print" ></i></a>
                </div>
                
            </div>                 
                <table class="table table-striped" style="margin-top:10px">
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