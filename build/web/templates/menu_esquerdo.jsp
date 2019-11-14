<%-- 
    Document   : menu_esquerdo
    Created on : 27/09/2017, 00:13:00
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<div class="hidden-sm hidden-xs">

    <c:if test="${admin eq true}">
        <div class="panel panel-default"> 
            <div class="panel-heading"> 
                <h3 class="panel-title">Administração</h3> 
            </div> 
            <div class="panel-body"> 
                <ul style="list-style-type: none">
                    <li><a href="<c:url value="/admin_server" />">Server</a></li>
                    <li><a href="<c:url value="/problems_admin" />">Problemas</a></li>
                    <li><a href="<c:url value="/users_admin" />">Usuarios</a></li>
                    <li><a href="<c:url value="/teams" />">Equipas</a></li>
                    <li><a href="<c:url value="/teams" />">Instituição</a></li>
                </ul>
            </div> 
        </div>
    </c:if>

    <div class="panel panel-default"> 
        <div class="panel-heading"> 
            <h3 class="panel-title">24h</h3> 
        </div> 
        <div class="panel-body"> 
            <ul style="list-style-type: none">
                <li><a href="<c:url value="/problems" />">Problemas</a></li>
                <li><a href="<c:url value="/status" />">Submissões</a></li>
                <li><a href="<c:url value="/ranking" />">Rank</a></li>
                <li><a href="">Concursos</a>
                    <ul>
                        <li><a href="<c:url value="/next_contest" />">Proximo</a></li>
                        <li><a href="<c:url value="/running_contest" />">Em execução</a></li>
                        <li><a href="<c:url value="/past_contest" />">Passados</a></li>
                        <li><a href="<c:url value="/global_contest_statistics" />">Statisticas</a></li>
                    </ul>
                </li>
                <li><a href="<c:url value="/statistics_submit" />">Statisicas</a></li>
            </ul>
        </div>

    </div>
</div>