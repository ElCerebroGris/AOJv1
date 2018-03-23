<%-- 
    Document   : contest_rank
    Created on : 26/fev/2018, 17:51:07
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>AOJ-Contest-Rank</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link href="css/style.css" rel="stylesheet">
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>

    </head>
    <body>
        <c:import url="../templates/header.jsp"/>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <c:import url="../templates/menu_contest.jsp"/>
                </div>

                <div class="col-md-8">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <p class="panel-title">Rank</p>
                        </div>
                        <div class="panel-body">
                            <table class="table table-bordered table-responsive">
                                <thead>
                                    <tr class="bg-primary">
                                        <th></th>
                                        <th>Contestant</th>
                                        <th>AC</th>
                                        <th>Time</th>
                                            <c:forEach items="${contest.problems}" var="cp">
                                            <th><a style="color:white" href="cproblem?pid=${cp.id}">${cp.letra}</a></th>
                                            </c:forEach>
                                    </tr>
                                </thead>

                                <tbody>
                                    <% int i = 1; %>
                                    <c:forEach items="${users}" var="u">
                                        <tr>
                                            <td><% out.println(i++);%></td>
                                            <td><a href="cuser_profile?uid=${u.id}">${u.login}</a></td>
                                            <td></td>
                                            <td></td>

                                            <%-- Para cada Usuario listar os problemas --%>
                                            <c:forEach items="${u.problems}" var="cp">
                                                
                                                <c:if test="${cp > 0}">
                                                    <td class="success">${cp}</td>
                                                </c:if>

                                                <c:if test="${cp < 0}">
                                                    <td class="danger">(${cp})</td>
                                                </c:if>

                                                <c:if test="${cp == 0}">
                                                    <td></td>
                                                </c:if>

                                            </c:forEach>
                                            <%-- FIM Para cada Usuario listar os problemas --%>

                                        </tr>
                                    </c:forEach>
                                </tbody>

                            </table>
                        </div>
                    </div>

                </div>
            </div>   
        </div>
    </body>
    <c:import url="../templates/footer.jsp"/>
</html>
