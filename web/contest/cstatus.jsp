<%-- 
    Document   : status
    Created on : 27/09/2017, 19:55:32
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>AOJ-Status</title>
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
            <div class="row alert">

            <div class="col-md-4">
                    <c:import url="../templates/menu_contest.jsp"/>
                </div>
                
            <div class="col-md-8">
                <div class="panel panel-default">
                        <div class="panel-heading">
                            <p class="panel-title">Status</p>
                        </div>
                    <div class="panel-body">

                <table class="table table-bordered text-center">
                    <tr class="bg-primary">
                        <th class="text-center">ID</th>
                        <th class="text-center">User</th>
                        <th class="text-center">Problem</th>
                        <th class="text-center">Status</th>
                        <th class="text-center">Language</th>
                        <th class="text-center">Memory</th>
                        <th class="text-center">Date</th>
                    </tr>
                    <c:forEach items="${submisso}" var="s">
                        <tr>
                            <td>${s.id_submissao}</td>
                            <td><a href="cuser_profile?uid=${s.id_usuario}">${s.login_user}</a>
                            </td>
                            <td><a href="cproblem?cid=${contest.id}&pid=${s.id_problema}">${s.id_problema}</a>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${s.status=='Ok'}"><p style="color: green"></c:when>
                                    <c:when test="${s.status=='Evaluating'}"><p style="color: black"></c:when>
                                    <c:otherwise><p style="color: red"></c:otherwise>
                                </c:choose>
                                ${s.status}
                                </p>
                            </td>
                            <td>${s.linguagem}</td>
                            <td>0KB</td>
                            <td>${s.data}</td>
                        </tr>
                    </c:forEach>
                </table>
                        </div>
                </div>
            </div>
        </div>
                </div>
    </body>
    <c:import url="../templates/footer.jsp"/>
</html>
