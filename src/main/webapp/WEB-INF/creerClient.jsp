
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Cr�ation d'un client</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
        <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <style> body{ margin-top:20px}</style>
    </head>
    <body class="container col-md-10">
        <div><%@ include file ="inc/menu.jsp" %></div>
        <div class="col-md-6 offset-md-3">
        <form method="post" action="<c:url value="/creationclient"/>" enctype="multipart/form-data" />
            <%@ include file ="inc/formClient.jsp" %>     
            <input type="submit" value="Valider"  class="btn btn-primary btn-lg " style="margin-right: 10px" />
            <input type="reset" value="Remettre � z�ro" class="btn btn-primary btn-lg"/>        
        </form>
        </div>
    </body>
</html>