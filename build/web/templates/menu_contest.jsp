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
                <h3 class="panel-title">Administration</h3> 
            </div> 
            <div class="panel-body"> 
                <ul style="list-style-type: none">
                    <li><a href="<c:url value="/admin_server" />">Server</a></li>
                    <li><a href="<c:url value="/problems" />">Problems</a></li>
                    <li><a href="<c:url value="/ranking" />">Users</a></li>
                </ul>
            </div> 
        </div>
    </c:if>

    <div class="panel panel-default"> 
        <div class="panel-heading"> 
            <h3 class="panel-title">Real Contest</h3> 
        </div> 
        <div class="panel-body"> 
            <ul style="list-style-type: none">
                <li><a href="<c:url value="/preview?cid=${contest.id}" />">Overview</a></li>
                    <c:if test="${contest.running eq true or contest.past eq true}">
                    <li><a href="<c:url value="/cproblems?cid=${contest.id}" />">Problems</a></li>
                    <li><a href="<c:url value="/crank?cid=${contest.id}" />">Rank</a></li>
                    </c:if>
            </ul>
        </div>

    </div>
</div>