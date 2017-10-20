<%-- 
    Document   : login
    Created on : 15/10/2017, 22:32:48
    Author     : VashJHK
    --%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>
        <title>Tela de Login - ControlChef</title>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>

    <body>
      <!--Import jQuery before materialize.js-->
      <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
      <script type="text/javascript" src="js/materialize.min.js"></script>


      <div class="row card-panel grey darken-4"> <!-- section grey darken-4 -->

          <div class="container">
              <form action="" method="post" enctype="multipart/form-data">
                  <div class="input-field col l12">
                    <input id="last_name" name="nome" type="text" class="validate">
                    <label for="last_name">Usuário</label>  
                </div>

                <div class="input-field col l12">
                    <input id="password" name="senha" type="password" class="validate">
                    <label for="password">Senha</label>
                    <input type="hidden" name="acao" value="logar" />
                    <input type="submit" value="Logar" class="btn" />
                </div>
            </form>
        </div>
    </div>

</body>

 <footer class="page-footer grey darken-3">
  <div class="container">
    <div class="row">
      <div class="col l6 s12">
      <img src="images/logo-footer.png" style="width:70px; float: left;">
        <h5 class="white-text">ControlChef - Controle de Restaurante</h5>
      </div>
      <div class="col l4 offset-l2 s12">

      </div>
    </div>
  </div>
  <div class="footer-copyright grey darken-4">
    <div class="container">
      © 2017 Copyright| Todos os direitos reservados.
      <a class="grey-text text-lighten-4 right" href="http://www.seaman.com.br/">More Links</a>
    </div>
  </div>
</footer>
</html>
