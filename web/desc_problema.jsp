<%-- 
    Document   : desc_problema
    Created on : 27/09/2017, 20:03:22
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <title>AOJ - Problemas</title>
    </head>

    <body>
        <div class="container">

            <div class="row alert">
                <c:import url="templates/header.jsp"/>

                <div class="col-md-4">
                    <div class="panel panel-default"> 
                        <div class="panel-heading"> 
                            <h3 class="panel-title"></h3> 
                        </div> 
                        <div class="panel-body"> 
                            <ul class="list-group list-unstyled">
                                <li class="group-list-iten"><strong>Tempo Total</strong>: ${problema.tempo} seconds</li>
                                <li class="group-list-iten"><strong>Tempo de Teste</strong>: ${problema.tempo} seconds</li>
                                <li class="group-list-iten"><strong>Memoria</strong>: 256 MB</li>
                                <li class="group-list-iten"><strong>Adicionado por</strong>: ${problema.usuario}</li>
                                    <c:if test="${online eq true}">
                                    <li class="group-list-iten">
                                        <div class="text-center">
                                            <a href="submit?id=${problema.id}" class="btn btn-primary">Submeter</a>  
                                        </div>
                                    </li>
                                </c:if>
                            </ul>
                        </div> 
                    </div>
                    <c:import url="templates/menu_esquerdo.jsp"/>

                </div>

                <div class="col-md-8 text-justify">

                    <header>
                        <h2 class="text-center"><strong>#${problema.id} - ${problema.nome} </strong></h2>   
                    </header>

                    <h4><strong>Descrição</strong></h4>

                    <div class="text m-t-2 m-b-2" id="texto">
                        ${problema.texto}
                    </div>

                    <h4><strong>Expecificações de Entrada</strong></h4>

                    <p class="m-t-2 m-b-2">

                        ${problema.expecificacaoEntrada}

                    </p>

                    <h4><strong>Expecificações de Saída</strong></h4>

                    <p class="m-t-2 m-b-2">
                        ${problema.expecificacaoSaida}
                    </p>

                    <h4><strong>Exemplo de Entrada</strong></h4>

                    <p class="m-t-2 m-b-2">

                        ${problema.exemploEntrada}

                    </p>

                    <h4><strong>Exemplo de Saída</strong></h4>

                    <p class="m-t-2 m-b-2">
                        ${problema.exemploSaida}
                    </p>



                    <div class="row">

                        <c:if test="${online eq true}">
                            <div class="row">
                                <div class="text-center">
                                    <a href="submit?id=${problema.id}" class="btn btn-primary btn-lg">Submeter</a>
                                </div>

                            </div>    
                        </c:if>

                    </div>

                </div>

            </div>
            <c:import url="templates/footer.jsp"/>

        </div>
    </body>
</html>