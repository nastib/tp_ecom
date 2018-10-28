<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Inscription</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" />
        <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <style>
            body {margin-top: 20px}
        </style>
    </head>
    <body class="container col-md-10">
         <div><%@ include file ="inc/menu.jsp" %></div>
         <div class="col-md-4 offset-md-4" style="border:solid; border-radius: 5px; margin-top: 10px">
        <form method="post" action="inscription" >
            <fieldset >
                <legend  class="display-4 text-center">Inscription</legend>

                <p class="lead text-center">Vous pouvez vous inscrire via ce formulaire.</p>
                
                <div class="form-group">
                <label for="email">Adresse email <span class="requis">*</span></label>
                <input type="email" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" class="form-control"/>
                <small class="erreur">${form.erreurs['email']}</small>
                </div>

                <div class="form-group">
                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" class="form-control"/>
                <small class="erreur">${form.erreurs['motdepasse']}</small>
                </div>

                <div class="form-group">
                <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" class="form-control"/>
                <small class="erreur">${form.erreurs['confirmation']}</small>
                </div>

                <div class="form-group">
                <label for="nom">Nom d'utilisateur</label>
                <input type="text" id="nom" name="nom" value="<c:out value="${utilisateur.nom}"/>" size="20" maxlength="20" class="form-control"/>
                <small class="erreur">${form.erreurs['nom']}</small>
                </div>

                <input type="submit" value="Envoyer" class="btn btn-primary btn-lg" />
                <br /> <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
            </fieldset>
        </form>
         </div>
    </body>
</html>
