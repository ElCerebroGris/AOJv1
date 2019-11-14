<%-- 
    Document   : problems
    Created on : 27/09/2017, 00:55:26
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>AOJ - Problemas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link href="css/style.css" rel="stylesheet">

        <link rel="stylesheet" href="css/dataTables.bootstrap.css" />
        <link rel="stylesheet" href="css/dataTables.min.css" />
        <link rel="stylesheet" href="css/jquery.dataTables.css" />

        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
    </head>

    <body>

        <div class="container">

            <div class="row alert">
                <c:import url="templates/header.jsp"/>

                <div class="col-md-4">
                    <c:import url="templates/menu_esquerdo.jsp"/>
                </div>
                <div class="col-md-8">

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <p class="panel-title">Problemas</p>
                        </div>
                        <div class="panel-body">
                            <table id="tabela" class="table table-bordered text-center table-responsive">
                                <thead>
                                    <tr class="bg-primary">
                                        <th class="text-center">ID</th>
                                        <th class="text-center">Nome</th>
                                        <th class="text-center">AC</th>
                                        <th class="text-center">AC%</th>
                                        <th class="text-center hidden-xs">Pontuação</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${problemas}" var="p">
                                        <tr>
                                            <td>${p.id}</td>
                                            <td><a href="verProblema?id=${p.id}">${p.nome}</a></td>
                                            <td>${p.ac}</td>
                                            <td>${p.percentagem}</td>
                                            <td class="hidden-xs">${p.pontos}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <c:import url="templates/footer.jsp"/>
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.dataTables.js"></script>

        <script src="js/datatables.min.js"></script>
        <script src="js/datatables.bootstrap.min.js"></script>

        <script>
            $(document).ready(function () {
                $('#tabela').DataTable({
                    "language": {
                        "sProcessing": "Processando...",
                        "sLengthMenu": "Mostrar _MENU_ registros",
                        "sZeroRecords": "Não existe resultados",
                        "sEmptyTable": "Nenhum dado disponivel nesta tabela",
                        "sInfo": "Mostrando registros de _START_ à _END_ de um total de _TOTAL_ registros",
                        "sInfoEmpty": "Mostrando registros de 0 à 0 de um total de 0 registros",
                        "sInfoFiltered": "(filtrando de um total de _MAX_ registros)",
                        "sInfoPostFix": "",
                        "sSearch": "Buscar:",
                        "sUrl": "",
                        "sInfoThousands": ",",
                        "sLoadingRecords": "Carregando...",
                        "oPaginate": {
                            "sFirst": "Primeiro",
                            "sLast": "Último",
                            "sNext": "Seguinte",
                            "sPrevious": "Anterior"
                        },
                        "oAria": {
                            "sSortAscending": ": Activar para ordenar a coluna de maneira ascendente",
                            "sSortDescending": ": Activar para ordenar a coluna de maneira descendente"
                        }
                    }
                });
            });
        </script>
    </body>
</html>
