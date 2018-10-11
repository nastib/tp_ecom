
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'une commande</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
        <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"/>
    </head>
    <body class="container col-md-10">
        <div>
            <div><%@ include file ="inc/menu.jsp" %></div>
            <div class="col-md-6 offset-md-3">
            <form method="post" action="<c:url value="/creationcommande"/>" >

                <%@ include file ="inc/formCommande.jsp" %>    
                <input type="submit" value="Valider" class="btn btn-primary btn-lg " style="margin-right: 10px" />
                <input type="reset" value="Remettre à zéro" class="btn btn-primary btn-lg "/> 
                
            </form>
            </div>
        </div>
    </body>
</html>