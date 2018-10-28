 <!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" />
        <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"/>
        <style>
            body {margin-top: 20px}
            .btn { width: 50px; height: 50px; border-radius: 25px; }
            td span { display: inline-block; width: 30px; }
        </style>        
        <title>Rapport d'Inventaire</title>
    </head>
    <body class="container col-md-10">
        <div><%@ include file ="inc/menu.jsp" %></div>
        <h1 class="display-4 text-center">Liste des commandes</h1>
        <div class="row">
            <div class="col-md-11">
                <small id="submitHelp" class="badge badge-success">${form.resultat}</small>
            </div>
            <div class="col-md-1 align-rigth">
                <a href="<c:url value="/creationcommande"/>" class="btn btn-outline-primary " style="padding-top:12px"><i class="fas fa-plus" ></i></a>
            </div>
        </div>
         <div class="row">.</div>
        <div class="row">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Date</th>
                    <th>Client</th>
                    <th>Montant</th>
                    <th>Paiement</th>
                    <th>Livraison</th>
                    <th class="text-center">Actions</th>
                </tr>
            </thead>
            <tbody>

                <c:forEach var="commande" items="${sessionScope.commandes}" varStatus="status">
                    <c:set var="date" value="<joda:format value='${commande.value.date }' pattern='dd/MM/yyyy HH:mm:ss'></joda:format>"/>
                    <tr>  
                        <td><strong><c:out value="${commande.value.id}"/> </strong></td>
                        <td><joda:format value='${commande.value.date }' pattern='dd/MM/yyyy HH:mm:ss'/></td> 
                        <td> <c:out value="${commande.value.client.nom} ${commande.value.client.prenom}"></c:out> </td>  
                        <td> <c:out value="${commande.value.montant}"></c:out> </td>     
                        <td> <c:out value="${commande.value.modePaiement}"></c:out> </td>     
                        <td> <c:out value="${commande.value.modeLivraison}"></c:out> </td>     
                        <td class="text-center">
                            <span><a href="<c:url value="/affichercommande?idCommande=${commande.value.id}"/>"><i class="far fa-eye" ></i></a></span>   
                            <span><a href="<c:url value="/editioncommande?idCommande=${commande.value.id}"/>"><i class="fas fa-pencil-alt" ></i></a></span>   
                            <span><a href="<c:url value="/suppressioncommande?idCommande=${commande.value.id}"/>"><i class="fas fa-times" style="color:red"></i></a></span>
                            <span><a href="<c:url value="/sousmenucommande"/>"><i class="fas fa-ellipsis-v"></i></a></span>
                        </td>
                        </tr>              
                </c:forEach>
            </tbody> 

        </table>
        </div>   
    </body>
</html>