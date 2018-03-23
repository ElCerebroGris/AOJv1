<%-- 
    Document   : cproblems
    Created on : 22/fev/2018, 13:27:02
    Author     : Zamba
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>AOJ - Problems</title>
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
                            <p class="panel-title">Problems</p>
                        </div>
                        <div class="panel-body">
                            <table id="tabela" class="table table-bordered text-center table-responsive">
                                <thead>
                                    <tr class="bg-primary">
                                        <th class="text-center"></th>
                                        <th class="text-center">Name</th>
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
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <script src="js/jquery.min.js"></script>

    </script>
</body>
<c:import url="../templates/footer.jsp"/>
</html>
