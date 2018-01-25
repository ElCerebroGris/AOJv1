<%-- 
    Document   : preview_contest
    Created on : 07/11/2017, 22:49:38
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contest</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link href="css/style.css" rel="stylesheet">
        <script type="text/javascript" href="js/bootstrap.min.js"></script>

    </head>

    <body>
        <c:import url="../templates/header.jsp"/>

        <div class="row alert">
            <div class="col-md-4">
                <c:import url="../templates/menu_esquerdo.jsp"/>
            </div>
            <div class="col-md-8">
                <h2>Contests</h2>
                <c:if test="${admin eq true}">
                    <a href="add_contest" class="btn btn-primary">Create contest</a>
                </c:if>

                <table class="table table-striped table-bordered text-center">
                    <thead>
                        <tr class="bg-primary">
                            <th class="text-center">ID</th>
                            <th class="text-center">Name</th>
                            <th class="text-center">Start at</th>
                            <th class="text-center">End at</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${contests}" var="co">
                            <tr>
                                <td>#</td>
                                <td><a href="preview?cid=${co.id}">${co.nome}</a></td>
                                <td>${co.inicio}</td>
                                <td>${co.fim}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
    <c:import url="../templates/footer.jsp"/>
</html>
