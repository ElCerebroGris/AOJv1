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
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>

    </head>
    <body>

        <div class="container">
            <div class="row alert">
                <c:import url="templates/header.jsp"/>

                <div class="col-md-4">
                    <c:import url="templates/menu_esquerdo.jsp"/>
                </div>

                <div class="col-md-8">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <p class="panel-title">Rank</p>
                        </div>
                        <div class="panel-body">
                            <table id="tabela" class="table table-bordered table-responsive">
                                <thead>
                                    <tr class="bg-primary">
                                        <th>Rank</th>
                                        <th>Country</th>
                                        <th>User</th>
                                        <th>Rating</th>
                                        <th>Solved</th>
                                        <th>Last Submitions</th>
                                    </tr>
                                </thead>
                                <tbody>
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
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
            <c:import url="templates/footer.jsp"/>
        </div>
        <script src="js/jquery.min.js"></script>
        <link rel="stylesheet" href="css/datatables.min.css" />
        <script src="js/datatables.min.js"></script>

        <script>
            $(document).ready(function () {
                $('#tabela').DataTable({
                    "language": {
                        "lengthMenu": "Display _MENU_ records per page",
                        "zeroRecords": "Nothing found - sorry",
                        "info": "Showing page _PAGE_ of _PAGES_",
                        "infoEmpty": "No records available",
                        "infoFiltered": "(filtered from _MAX_ total records)"
                    }
                });
            });
        </script>
    </body>

</html>