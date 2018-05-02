<%-- 
    Document   : ranking
    Created on : 27/09/2017, 19:45:05
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>AOJ - Users</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link href="css/style.css" rel="stylesheet">
        <script type="text/javascript" href="js/bootstrap.min.js"></script>

    </head>
    <body>
        <c:import url="../templates/header.jsp"/>
        <div class="container">
            <div class="row alert">

                <div class="col-md-4">
                    <c:import url="../templates/menu_esquerdo.jsp"/>
                </div>
                
                <div class="col-md-8">
                    <h3><span class="glyphicon glyphicon-user"></span> Users</h3>
                    <div class="row alert">
                        <table class="table table-bordered">
                            <tr class="bg-primary">
                                <th>Id</th>
                                <th>Country</th>
                                <th>User</th>
                                <th>Full name</th>
                                <th>Gender</th>
                                <th>State</th>
                                <th>Option</th>
                            </tr>
                            <c:forEach items="${users}" var="u">
                                <tr>
                                    <td>${u.id}</td>
                                    <td>${u.country}</td>
                                    <td><a href="perfil?uid=${u.id}">${u.login}</a></td>
                                    <td>${u.first_name} ${u.last_name}</td>
                                    <td>${u.gender}</td>
                                    <td>${u.state}</td>
                                    <td>
                                        <c:if test="${u.state eq true}">
                                            <a href="disable_user?uid=${u.id}" class="btn btn-danger">Disable</a>
                                        </c:if>
                                        <c:if test="${u.state eq false}">
                                            <a href="active_user?uid=${u.id}" class="btn btn-primary">Active</a>
                                        </c:if>

                                        <c:if test="${u.role != 0}">
                                            <a href="make_admin?uid=${u.id}" class="btn btn-info">Make Admin</a>
                                        </c:if>
                                            
                                        <c:if test="${u.role == 0}">
                                            <a href="unmake_admin?uid=${u.id}" class="btn btn-info">Unmake Admin</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>   
        </div>
    </body>
    <c:import url="../templates/footer.jsp"/>
</html>