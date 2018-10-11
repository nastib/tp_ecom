
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Edition d'une commande</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
        <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
    </head>
    <body class="container col-md-10">
        <div><%@ include file ="inc/menu.jsp" %></div>
        <div class="col-md-6 offset-md-3">
        <form method="post" action="<c:url value='/editioncommande?numero=${param.numero}'/>">
            <%@ include file ="inc/formCommande.jsp" %>     
            <input type="submit" value="Valider"  class="btn btn-primary btn-lg " style="margin-right: 10px" />
            <input type="reset" value="Remettre � z�ro" class="btn btn-primary btn-lg"/>        
        </form>
        </div>
    </body>
</html>