<%-- 
    Document   : add_contest
    Created on : 07/11/2017, 23:29:11
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create contest</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link href="css/style.css" rel="stylesheet">

        <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script>
            $(function () {
                $("#cstart").datepicker({
                    dateFormat: 'yy/mm/dd'
                });
                $("#cend").datepicker({
                    dateFormat: 'yy/mm/dd'
                });

                var dados = ["1122", "2121", "0001"];
                $("#busca").autocomplete({
                    source: dados
                });

                $("#addP").click(function () {
                    $("#listaP").append("<li class=\"list-group-item\">" +
                            $("#busca").val() + "</li>");
                });
            });

        </script>
    </head>
    <body>

        <div class="container-fluid">
            <div class="container">

                <div class="row alert">
                    <c:import url="../templates/header.jsp"/>

                    <div class="col-md-4">
                        <c:import url="../templates/menu_esquerdo.jsp"/>
                    </div>
                    <div class="col-md-8">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <p class="panel-title">Add contest</p>
                            </div>
                            <div class="panel-body">
                                <sf:form modelAttribute="contest" role="form" action="add_contest_form">
                                    <div class="form-group">
                                        <label>CID</label>
                                        <div>
                                            <sf:input id="pid" path="id"  class="form-control" readonly="true" value="0" type="text" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="cname">Contest name</label>
                                        <sf:input path="nome" id="cname" type="text" class="form-control" />
                                    </div>

                                    <div class="form-group form-inline">
                                        <label for="cstart">Start date</label>
                                        <input name="inicio" type="text" id="cstart" class="form-control" />

                                        <label for="cstart">Start time</label>
                                        <input name="hora_inicio" type="time" id="cstart" class="form-control" />
                                    </div>

                                    <div class="form-group form-inline">
                                        <label for="cend">End date</label>
                                        <input name="fim" type="text" id="cend" class="form-control" />

                                        <label for="cend">End time</label>
                                        <input name="hora_fim" type="time" id="cend" class="form-control" />
                                    </div>

                                    <div class="form-group">
                                        <input type="submit" class="btn btn-primary" value="Create" />
                                    </div>
                                </sf:form>
                            </div>
                        </div>

                    </div>
                </div>
                <c:import url="../templates/footer.jsp"/>
            </div>
        </div>

    </body>

</html>
