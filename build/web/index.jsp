<%-- 
    Document   : index
    Created on : 27/09/2017, 00:12:04
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>AOJ</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link href="css/style.css" rel="stylesheet">
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
    </head>

    <body class="">

        <div class="container-fluid">
            <div class="container">

                <div class="row alert">
                    <c:import url="templates/header.jsp"/>

                    <div class="col-sm-4">
                        <c:import url="templates/menu_esquerdo.jsp"/>
                    </div>

                    <div class="col-md-8">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <p class="panel-title">Forum</p>
                            </div>
                            <div class="panel-body">
                                <c:if test="${online eq true}">
                                    <div class="form-group ss">
                                        <form action="<c:url value="/comentar"/>" method="post">
                                            <textarea name="texto" class="form-control" id="txtarea" rows="5" id="comment" 
                                                      style="max-width:100%;max-height:95px;" placeholder="Pergunte Aqui..."></textarea>
                                            <br>
                                            <input type="submit" class="btn btn-primary" id="btt" value="Partilhar" />
                                        </form>
                                    </div>
                                </c:if>
                                <br><br>

                                <div class="table-responsive">
                                    <table id="tabela" class="table">
                                        <thead>
                                            <tr class="bg-primary">
                                                <th class="text-center"></th>
                                                <th class="text-center"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${mural}" var="m">
                                                <tr>
                                                    <td>${m.usuario}</td>
                                                    <td><p>${m.texto}</p>
                                                        <span class="text-muted small small small">posted on ${m.data}</span>
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
                <c:import url="templates/footer.jsp"/>
            </div>
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
