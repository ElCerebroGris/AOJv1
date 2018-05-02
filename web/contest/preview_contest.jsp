<%-- 
    Document   : preview_contest
    Created on : 07/11/2017, 22:49:38
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>

        <link rel="stylesheet" type="text/css" href="css/TimeCircles.css" />
        <script type="text/javascript" src="js/TimeCircles.js"></script>

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
                            <h3 class="panel-title">${contest.nome} - Overview</h3> 
                        </div> 
                        <div class="panel-body"> 
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
                        <c:if test="${contest.coming}">
                            <div class="panel-footer">
                                <div id="exemplo" data-timer="${timeInit}"></div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                $("#exemplo").TimeCircles({circle_bg_color: "white", fg_width: 0.05, text_size: 0.06});
            });
        </script>
    </body>
    <c:import url="../templates/footer.jsp"/>
</html>
