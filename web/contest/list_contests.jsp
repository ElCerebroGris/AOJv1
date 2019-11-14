<%-- 
    Document   : preview_contest
    Created on : 07/11/2017, 22:49:38
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contests</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link href="css/style.css" rel="stylesheet">
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>

    </head>

    <body>

        <div class="container-fluid">
            <div class="container">
                <div class="row alert">
                    <c:import url="../templates/header.jsp"/>

                    <div class="col-md-4">
                        <c:import url="../templates/menu_esquerdo.jsp"/>
                    </div>
                    <div class="col-md-8">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <p class="panel-title">Contests</p>
                            </div>
                            <div class="panel-body">
                                <table id="tabela" class="table table-striped table-bordered text-center table-responsive">
                                    <thead>
                                        <tr class="bg-primary">
                                            <th class="text-center">ID</th>
                                            <th class="text-center">Nome</th>
                                            <th class="text-center">Inicio</th>
                                            <th class="text-center">Fim</th>
                                            <th class="text-center">Estado</th>
                                                <c:if test="${admin eq true}">
                                                <th class="text-center">Opções</th>
                                                </c:if>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${contests}" var="co">
                                            <c:if test="${co.visible || admin}">
                                                <tr>
                                                    <td>${co.id}</td>
                                                    <td><a href="preview?cid=${co.id}">${co.nome}</a></td>
                                                    <td>
                                                        <fmt:formatDate value="${co.inicio}" pattern="dd/MM/yyyy hh:mm:ss am" />
                                                    </td>
                                                    <td>
                                                        <fmt:formatDate value="${co.fim}" pattern="dd/MM/yyyy hh:mm:ss am" />
                                                    </td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${co.coming}"><span>Coming</span></c:when>
                                                            <c:when test="${co.past}"><span>Past</span></c:when>
                                                            <c:when test="${co.running}"><span>Running</span></c:when>
                                                        </c:choose>
                                                    </td>
                                                    <c:if test="${admin eq true}">
                                                        <td>
                                                            <a href="edit_contest?cid=${co.id}" class="btn btn-info">Editar</a>
                                                            <c:choose>
                                                                <c:when test="${co.visible}">
                                                                    <a class="btn btn-danger" href="desactive_contest?cid=${co.id}">
                                                                        Desactivar
                                                                    </a>
                                                                </c:when>
                                                                <c:when test="${!co.visible}">
                                                                    <a class="btn btn-info" href="active_contest?cid=${co.id}">
                                                                        Activar
                                                                    </a>
                                                                </c:when>
                                                            </c:choose>
                                                        </td>
                                                    </c:if>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                            <c:if test="${admin eq true}">
                                <div class="panel-footer">
                                    <a href="add_contest" class="btn btn-primary">Criar Concurso</a>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
                <c:import url="../templates/footer.jsp"/>
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
