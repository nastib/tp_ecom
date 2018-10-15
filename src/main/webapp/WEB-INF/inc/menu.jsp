<div>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="border-radius: 10px">
  <a class="navbar-brand" href="<c:url value="/accueil"/>">
  <img src="resources/img/bootstrap-solid.svg" width="30" height="30" class="d-inline-block align-top" alt="">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"/>
  TP-eCom
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
          <a class="nav-link" href="<c:url value="/accueil"/>">Accueil <span class="sr-only">(current)</span></a>
      </li>        
      <c:if test="${ !empty sessionScope.sessionUtilisateur}">
      <li class="nav-item">
          <a class="nav-link" href="<c:url value="/listeclient"/>">Client <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
          <a class="nav-link" href="<c:url value="/listecommande"/>">Commande</a>
      </li> 
      </c:if>
    </ul>
   <form class="form-inline my-2 my-lg-0">
      <c:if test="${ empty sessionScope.sessionUtilisateur}">
          <ul>
          <li class="nav-item">
          <a class="nav-link" href="<c:url value="/connexion"/>"><i class="fas fa-user-check" style="padding-right: 5px"></i>Login</a>         
          </li>  
          </ul>
      </c:if>
      <c:if test="${ !empty sessionScope.sessionUtilisateur}">
          <ul>
          <li class="nav-item">
          <a class="nav-link" href="<c:url value="/deconnexion"/>"><i class="fas fa-sign-out-alt" style="padding-right: 5px"></i>Logout</a>
          </li>  
          </ul>
      </c:if>
   </form>      
  </div>
</nav>
</div>