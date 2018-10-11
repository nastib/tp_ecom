<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Connexion</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" />
        <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <style> 
            body { margin-top : 20px}
        </style>
    </head>
    <body class="container col-md-10">

        <div><%@ include file ="inc/menu.jsp" %></div>
        <div class="col-md-4 offset-md-4" style="border:solid; border-radius: 5px; margin-top: 10px">
            <form method="post" action="<c:url value="/connexion"/>" >
                <fieldset >

                    <legend class="display-4 text-center">Connexion</legend>
                    <p class="lead text-center">Connectez-vous via ce formulaire.</p>

                    <c:if test="${empty sessionScope.sessionUtilisateur && !empty requestScope.intervalleConnexions}">
                        <p class="info">(Vous ne vous êtes pas connecté(e) depuis ce navigateur depuis ${requestScope.intervalleConnexions})</p>
                    </c:if>

                    <div class="form-group">
                        <label for="nom">Adresse email <span class="requis">*</span></label>
                        <input type="email" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" class="form-control"/>
                        <small class="erreur">${form.erreurs['email']}</small>
                    </div>

                    <div class="form-group">
                        <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                        <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" class="form-control"/>
                        <small class="erreur">${form.erreurs['motdepasse']}</small>
                    </div>

                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" id="memoire" name="memoire" value="${param.memoire}" class="custom-control-input" />
                        <label for="memoire" class="custom-control-label">Se souvenir de moi</label>
                    </div>
                    <br />
                    <input type="submit" value="Connexion" class="btn btn-primary btn-lg" />
                    <a href="<c:url value="/inscription"/>" class="btn btn-primary btn-lg"> Inscription</a>
                    <br /><br />

                    <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>

                    <%-- Vérification de la présence d'un objet utilisateur en session --%>
                    <c:if test="${!empty sessionScope.sessionUtilisateur}">
                        <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
                        <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.email}</p>
                    </c:if>                

                </fieldset>
            </form>
        </div>
    </body>
</html>

