<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Fiche d'une commande</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" />
        <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"/>
        <style>
            .btn { width: 50px; height: 50px; border-radius: 25px; }
            .colon { font-weight: bold}
        </style>
    </head>
    <body>
        <div class="container col-md-10" style="margin-top:10px">
            <div><%@ include file ="inc/menu.jsp" %></div>
               
            <h1 class="display-4 text-center">Fiche commande</h1>
            <p class="lead text-center">Fiche d'informations sur la commande.</p>
            <small id="submitHelp" class="badge badge-success">${message}</small>
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
                        <td colspan="2" style="color:blue"><b>** CLIENT **</b></td>
                    </tr>
                    <tr>
                        <td class="colon">Id :</td>
                        <td class="text-left"><c:out value="${requestScope.commande.client.id}"/></td>
                    </tr>
                    <tr>
                        <td class="colon">Nom :</td>
                        <td class="text-left"><c:out value="${requestScope.commande.client.nom}"/></td>
                    </tr>
                    <tr>
                        <td class="colon">Prenom :</td>
                        <td class="text-left"><c:out value="${requestScope.commande.client.prenom}"/></td>
                    </tr>
                    <tr>
                        <td class="colon">Adresse : </td>
                        <td class="text-left"><c:out value="${requestScope.commande.client.adresse}"/></td>
                    </tr>
                    <tr>
                        <td class="colon">Télephone : </td>
                        <td class="text-left"><c:out value="${requestScope.commande.client.telephone}"/></td>
                    </tr>
                    <tr>
                        <td class="colon">Email : </td>
                        <td class="text-left"><c:out value="${requestScope.commande.client.email}"/></td>
                    </tr>   
                    <tr>
                        <td colspan="2" style="color:blue"><b>** COMMANDE **</b></td>
                    </tr>
                    <tr>
                        <td class="colon">Id :</td>
                        <td class="text-left">${requestScope.commande.id}</td>
                    </tr>
                    <tr>
                        <td class="colon">Date :</td>
                        <td class="text-left"><joda:format value="${commande.date }" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                    </tr>
                    <tr>
                        <td class="colon">Montant :</td>
                        <td class="text-left">${requestScope.commande.montant}</td>
                    </tr>   
                    <tr>
                        <td class="colon">Mode paiement :</td>
                        <td class="text-left"><c:out value="${requestScope.commande.modePaiement}"/></td>
                    </tr>
                    <tr>
                        <td class="colon">Statut de paiement : </td>
                        <td class="text-left"><c:out value="${requestScope.commande.statutPaiement}"/></td>
                    </tr>
                    <tr>
                        <td class="colon">Mode livraison : </td>
                        <td class="text-left"><c:out value="${requestScope.commande.modeLivraison}"/></td>
                    </tr>
                    <tr>
                        <td class="colon">Statut livraison : </td>
                        <td class="text-left"><c:out value="${requestScope.commande.statutLivraison}"/></td>
                    </tr>                         
                </tbody>
            </table>                              
        </div>
        </div>
    </body>
</html>
