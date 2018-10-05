
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'un client</title>
        <!--<link type="text/css" rel="stylesheet" href="inc/style.css" />-->
        <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <style>
            body {margin-top: 20px}
            .requis, .help { color: #c00 }
        </style>
    </head>
    <body class="container">
     <div><%@ include file ="inc/menu2.jsp" %></div>
    <form method="post" action="creationclient" >
        <%@ include file ="inc/formClient.jsp" %>     
        <input type="submit" value="Valider"  class="btn btn-primary btn-lg " style="margin-right: 10px" />
        <input type="reset" value="Remettre à zéro" class="btn btn-primary btn-lg"/> 
        
    </form>
    </body>
</html>