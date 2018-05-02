<%-- 
    Document   : cuser_perfil
    Created on : 6/mar/2018, 18:46:14
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Contest User profile</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>

    <body>

        <c:import url="../templates/header.jsp"/>

        <div class="container">
            <div class="row alert">

                <div class="col-md-4">
                    <c:import url="../templates/menu_esquerdo.jsp"/>
                </div>

                <div class="col-md-8">

                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                        <div class="panel panel-primary">
                            <div class="panel-heading" role="tab" id="headingOne">
                                <h4 class="panel-title">
                                        General Information - ${user.login}
                                </h4>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                <div class="panel-body">

                                    <div class="row">
                                        <div class="col-md-10 col-offset-md-2">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <div class="thumbnail ">
                                                        <img src="img/img_avatar.png" class="img-thumbnail img-circle img-fluid" width="100%" />

                                                    </div>
                                                </div>

                                                <div class="col-md-4 col-sm-6 col-xs-6">
                                                    <div class="caption">
                                                        <p>Name:  </p>
                                                        <p>Point:</p>
                                                        <p>Exercises solved:</p>
                                                        <p>Institution:</p>
                                                    </div>
                                                </div>

                                                <div class="col-md-4 col-sm-6 col-xs-6">
                                                    <p>${user.login}</p>
                                                    <p>${user.pontos}</p>
                                                    <p>${user.solved}</p>
                                                    <p>${user.institution}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>


                        <div class="panel panel-primary">
                            <div class="panel-heading" role="tab" id="headingTwo">
                                <h4 class="panel-title">
                                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                        Exercises Solved
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                                <div class="panel-body">

                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
            <c:import url="../templates/footer.jsp"/>
    </body>

</html>
