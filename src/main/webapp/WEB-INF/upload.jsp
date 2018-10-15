
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Envoi de fichier</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" />
        <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <style> 
            body { margin-top : 20px}
        </style>
    </head>

    <body class="container col-md-10">
        <div><%@ include file ="inc/menu.jsp" %></div>
        <div class="col-md-4 offset-md-4" >    
            <form action="<c:url value="/upload" />" method="post" enctype="multipart/form-data">
                <fieldset>
                    <legend class="display-4 text-center">Envoi de fichier</legend>

                    <div class="form-group">
                        <label for="description" class="custom-control-label">Description du fichier <span class="requis">*</span></label>
                        <input type="text" id="description" name="description" value="<c:out value="${fichier.description}"/>" class="form-control"/>
                        <small class="erreur">${form.erreurs['description']}</small>
                    </div>

                    <div class="form-group">
                        <label for="fichier" class="custom-control-label">Emplacement du fichier <span class="requis">*</span></label>
                        <input type="file" id="fichier" name="fichier" class="form-control"/><span>${fichier.nom}</span>
                        <small class="erreur">${form.erreurs['fichier']}</small>
                    </div>

                    <input type="submit" value="Envoyer" class="btn btn-primary btn-lg" />
                    <br/>
                </fieldset>
            </form>
        </div> 
    </body>
</html>
