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
        <title>Statistics</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link href="css/style.css" rel="stylesheet">

        <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>

    </head>
    <body>

        <div class="container-fluid">
            <div class="container">

                <div class="row alert">
                    <c:import url="../templates/header.jsp"/>

                    <div class="col-md-4">
                        <c:import url="../templates/menu_contest.jsp"/>
                    </div>
                    <div class="col-md-8">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <p class="panel-title">Contest Statistics</p>
                            </div>
                            <div class="panel-body">
                                <table id="tabela" class="table table-bordered text-center">
                                    <thead>
                                        <tr class="bg-primary">
                                            <th class="text-center">Language</th>
                                            <th class="text-center">AC</th>
                                            <th class="text-center">TLE</th>
                                            <th class="text-center">WA</th>
                                            <th class="text-center">CE</th>
                                            <th class="text-center">RTE</th>
                                            <th class="text-center">Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${languages}" var="pl">
                                            <tr>
                                                <td>${pl.name}</td>
                                                <td>${pl.AC}</td>
                                                <td>${pl.TLE}</td>
                                                <td>${pl.WA}</td>
                                                <td>${pl.CE}</td>
                                                <td>${pl.RTE}</td>
                                                <td>${pl.AC+pl.TLE+pl.WA+pl.CE+pl.RTE}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>
                <c:import url="../templates/footer.jsp"/>
            </div>
        </div>
    </body>
</html>
