
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"/>
        <style>
            body {margin-top: 20px}
            .btn { width: 50px; height: 50px; border-radius: 25px; }
            td span { display: inline-block; width: 30px; }
        </style>        
        <title>Liste Client</title>
    </head>
    <body class="container col-md-10">
        <div><%@ include file ="inc/menu.jsp" %></div>
        <h1 class="display-4 text-center">Liste des clients</h1>
        <div class="row">
            <div class="col-md-11">
                <small id="submitHelp" class="badge badge-success">${form.resultat}</small>
            </div>
            <div class="col-md-1 align-rigth">
                <a href="<c:url value="/creationclient"/>" class="btn btn-outline-primary " style="padding-top:12px"><i class="fas fa-plus" ></i></a>
            </div>
        </div>
         <div class="row">.</div>
        <div class="row">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Photo</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Adresse</th>
                    <th>Téléphone</th>
                    <th>Email</th>
                    <th class="text-center">Actions</th>
                </tr>
            </thead>
            <tbody>

                <c:forEach var="item" items="${sessionScope.listeClients}" varStatus="status">
                    <tr>  
                        <td><strong><c:out value="${status.count}"/> </strong></td>
                        <c:if test="${ !empty item.image}">
                             <td> <img src="resources/fichiers/${item.image}" alt="photo" style="width: 30px; height: 30px; border-radius: 25px"/></td>  
                        </c:if>
                        <c:if test="${ empty item.image}">
                             <td>  </td>  
                        </c:if>                             
                        <td> <c:out value="${item.nomClient}"></c:out> </td>                                 
                        <td> <c:out value="${item.prenomClient}"></c:out> </td>     
                        <td> <c:out value="${item.adresseClient}"></c:out> </td>     
                        <td> <c:out value="${item.telephoneClient}"></c:out> </td>     
                        <td> <c:out value="${item.emailClient}"></c:out> </td>     
                        <td class="text-center">
                            <span><a href="<c:url value="/afficherclient?numero=${status.count}"/>"><i class="far fa-eye" ></i></a> </span>  
                            <span><a href="<c:url value="/editionclient?numero=${status.count}"/>"><i class="fas fa-pencil-alt" ></i></a> </span>  
                            <span><a href="<c:url value="/deleteclient?numero=${status.count}"/>"><i class="fas fa-times" style="color:red"></i></a></span>
                            <span><a href="<c:url value="/printcommande?numero=${status.count}"/>"><i class="fas fa-print" ></i></a></span>
                        </td>            
                    </tr>                        
                </c:forEach>
            </tbody> 

        </table>
        </div>   
    </body>
</html>