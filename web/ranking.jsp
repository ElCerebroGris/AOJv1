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
        <title>AOJ - Rank</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link href="css/style.css" rel="stylesheet">
        <script type="text/javascript" href="js/bootstrap.min.js"></script>

    </head>
    <body>
        <c:import url="templates/header.jsp"/>
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <div class="thumbnail m-t-1">
                        <img src="img/img_avatar.png"  class="img-thumbnail img-circle img-fluid" width="100%" />
                        <div class="caption">
                            <h4 class="text-center h1 bg-primary">1st</h4>
                            <p>User: ${users[0].login} </p>
                            <p>Nick Name:.....</p>
                            <p>Institution:....</p>
                            <p>Points: ${users[0].pontos}</p>
                            <p>Exercises solved:....</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-9">
                    <h3><span class="glyphicon glyphicon-list"></span> Ranking</h3>
                    <div class="row alert">
                        <table class="table table-bordered">
                            <tr class="bg-primary">
                                <th>Rank</th>
                                <th>Country</th>
                                <th>User</th>
                                <th>Rating</th>
                                <th>Solved</th>
                                <th>Last Submitions</th>
                            </tr>
                            <% int i = 1; %>
                            <c:forEach items="${users}" var="u">
                                <tr>
                                    <td><% out.println(i);
                                            ++i;%></td>
                                    <td>Angola</td>
                                    <td><a href="perfil?uid=${u.id}">${u.login}</a></td>
                                    <td>${u.pontos}</td>
                                    <td>${u.solved}</td>
                                    <td>${u.last_submission}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>   
        </div>
    </body>
    <c:import url="templates/footer.jsp"/>
</html>