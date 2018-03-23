<%-- 
    Document   : add_contest
    Created on : 07/11/2017, 23:29:11
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

                var dados = ["1122", "2121", "0001"];
                $("#buscaU").autocomplete({
                    source: dados
                });

                /*
                 $("#addP").click(function () {
                 $("#listaP").append("<li class=\"list-group-item\">" + $("#busca").val() + "</li>");
                 });
                 */
            });

        </script>
    </head>
    <body>
        <c:import url="../templates/header.jsp"/>
        <div class="container-fluid">
            <div class="container">
                <div class="row alert">
                    <div class="col-md-4">
                        <c:import url="../templates/menu_contest.jsp"/>
                    </div>
                    <div class="col-md-8">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <p class="panel-title">Edit contest</p>
                            </div>
                            <div class="panel-body">
                                <h4>Server time: </h4>
                                <div> 
                                    <ul class="list-group">
                                        <li class="list-group-item">ID: ${contest.id}</li>
                                        <li class="list-group-item">Name: ${contest.nome}</li>
                                        <li class="list-group-item">Start at: 
                                            <fmt:formatDate value="${contest.inicio}" pattern="dd/MM/yyyy hh:mm:ss am" />
                                        </li>
                                        <li class="list-group-item">End at: 
                                            <fmt:formatDate value="${contest.fim}" pattern="dd/MM/yyyy hh:mm:ss am" />
                                        </li>
                                    </ul>
                                </div>
                                <sf:form role="form" action="add_problem_contest?cid=${contest.id}">

                                    <div class="form-group">
                                        <label>Problems</label>
                                        <div class="form-group">
                                            <div class="form-group">
                                                <input name="busca" type="text" id="busca" placeholder="problem id"/>
                                                <input type="submit" id="addP" class="btn btn-primary" value="Add" />
                                            </div>

                                            <ul name="listaP" class="list-group" id="listaP">
                                                <c:forEach items="${contest.problems}" var="cp">
                                                    <li class="list-group-item">${cp}</li>
                                                    </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </sf:form>

                                <sf:form role="form" action="add_user_contest?cid=${contest.id}">
                                    <div class="form-group">
                                        <label for="cend">Users</label>
                                        <div class="form-group">
                                            <label for="busca"></label>
                                            <input type="text" name="buscaU" id="buscaU" placeholder="user id"/>
                                            <input type="submit" id="addU" class="btn btn-primary" value="Add" />

                                            <ul name="listaU" class="list-group" id="listaU">
                                                <c:forEach items="${contest.users}" var="cu">
                                                    <li class="list-group-item">${cu}</li>
                                                    </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </sf:form>


                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
