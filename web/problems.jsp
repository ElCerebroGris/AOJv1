<%-- 
    Document   : problems
    Created on : 27/09/2017, 00:55:26
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
        <script type="text/javascript" href="js/bootstrap.min.js"></script>
    </head>
    
    <body>
        <c:import url="templates/header.jsp"/>
        <div class="container">

            <div class="row alert">
                <div class="col-md-4">
                    <c:import url="templates/menu_esquerdo.jsp"/>
                </div>
                <div class="col-md-8">

                    <c:if test="${admin eq true}">
                        <a href="<c:url value="/add_problem" />">Add probem</a>
                    </c:if>

                    <table id="tabela" class="table table-bordered text-center">
                        <thead>
                            <tr class="bg-primary">
                                <th class="text-center">ID</th>
                                <th class="text-center">Name</th>
                                <th class="text-center">AC</th>
                                <th class="text-center">AC%</th>
                                <th class="text-center hidden-xs">Score</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${problemas}" var="p">
                                <tr>
                                    <td>${p.id}</td>
                                    <td><a href="verProblema?id=${p.id}">${p.nome}</a></td>
                                    <td>${p.ac}</td>
                                    <td>${p.percentagem}</td>
                                    <td class="hidden-xs">${p.pontos}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>

        </div>

        <script src="js/jquery.min.js"></script>
    <link rel="stylesheet" href="css/datatables.min.css" />
    <script src="js/datatables.min.js"></script>

    <script>
        $(document).ready(function () {
            $('#tabela').DataTable();
        });
    </script>
</body>
<c:import url="templates/footer.jsp"/>
</html>
