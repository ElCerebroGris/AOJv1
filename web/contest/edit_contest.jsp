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
        <title>Edit contest</title>
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

                                <div class="col-md-6">
                                    <sf:form role="form" action="add_problem_contest?cid=${contest.id}">

                                        <div class="form-group">
                                            <label>Problems</label>
                                            <div class="form-group">
                                                <div class="form-group">
                                                    <input name="busca" type="text" id="busca" placeholder="problem id"/>
                                                    <input type="submit" id="addP" class="btn btn-primary" value="Add" />
                                                </div>

                                                <!--List problems in this contest -->

                                                <table id="tabela" class="table table-bordered text-center table-responsive">
                                                    <thead>
                                                        <tr class="bg-primary">
                                                            <th class="text-center">ID</th>
                                                            <th class="text-center">Name</th>
                                                            <th class="text-center">Option</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${contest.problems}" var="p">
                                                            <tr>
                                                                <td>${p.letra}</td>
                                                                <td><a href="cproblem?cid=${contest.id}&pid=${p.id}">
                                                                        ${p.nome}
                                                                    </a>
                                                                </td>
                                                                <td><a href="rem_problem_contest?cid=${contest.id}&pid=${p.id}" 
                                                                       class="btn btn-danger">Remove</a></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                                <!-- End list problem in this contest -->

                                            </div>
                                        </div>
                                    </sf:form>
                                </div>

                                <div class="col-md-6">
                                    <sf:form role="form" action="add_user_contest?cid=${contest.id}">
                                        <div class="form-group">
                                            <label for="cend">Users</label>
                                            <div class="form-group">
                                                <label for="busca"></label>
                                                <input type="text" name="buscaU" id="buscaU" placeholder="user id"/>
                                                <input type="submit" id="addU" class="btn btn-primary" value="Add" />
                                            </div>

                                            <!--List user in this contest -->

                                            <table id="tabela" class="table table-bordered text-center table-responsive">
                                                <thead>
                                                    <tr class="bg-primary">
                                                        <th class="text-center">ID</th>
                                                        <th class="text-center">Name</th>
                                                        <th class="text-center">Option</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${contest.users}" var="u">
                                                        <tr>
                                                            <td>${u.id}</td>
                                                            <td><a href="cuser_profile?cid=${contest.id}&uid=${u.id}">
                                                                    ${u.login}
                                                                </a>
                                                            </td>
                                                            <td><a href="#" class="btn btn-danger">Remove</a></td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                            <!-- End list users in this contest -->

                                        </div>

                                    </sf:form>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</body>
</html>
