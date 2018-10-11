
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Cr�ation d'une commande</title>
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
                <input type="reset" value="Remettre � z�ro" class="btn btn-primary btn-lg "/> 
                
            </form>
            </div>
        </div>
        <%-- Inclusion de la biblioth�que jQuery. Vous trouverez des cours sur JavaScript et jQuery aux adresses suivantes :
               - http://www.siteduzero.com/tutoriel-3-309961-dynamisez-vos-sites-web-avec-javascript.html 
               - http://www.siteduzero.com/tutoriel-3-659477-un-site-web-dynamique-avec-jquery.html 
               
             Si vous ne souhaitez pas t�l�charger et ajouter jQuery � votre projet, vous pouvez utiliser la version fournie directement en ligne par Google :
             <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script> 
        --%>
        <script src="<c:url value="resources/assets/js/jquery-1.12.4.min.js"/>"></script>
        
        <%-- Petite fonction jQuery permettant le remplacement de la premi�re partie du formulaire par la liste d�roulante, au clic sur le bouton radio. --%>
        <script>
        	jQuery(document).ready(function(){
        		/* 1 - Au lancement de la page, on cache le bloc d'�l�ments du formulaire correspondant aux clients existants */
        		$("div#nouveauClient").hide();
        		/* 2 - Au clic sur un des deux boutons radio "choixNouveauClient", on affiche le bloc d'�l�ments correspondant (nouveau ou ancien client) */
                jQuery('input[name=choixNouveauClient]:radio').click(function(){
                	$("div#nouveauClient").hide();
                	$("div#ancienClient").hide();
                    var divId = jQuery(this).val();
                    $("div#"+divId).show();
                });
            });
        </script>                
    </body>
</html>