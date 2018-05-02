<%-- 
    Document   : header
    Created on : 27/09/2017, 00:06:28
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
<html lang="en">
    <head>
        <title></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../css/style.css">
        <script type="text/javascript" src="../js/jquery.min.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>

        <%-- Script Ajax para pegar hora do servidor --%>
        <script>
            function myF() {
                $.ajax({
                    url: 'get_time',
                    success: function (data) {
                        $("#tempo").html(data);
                    }
                });
            }
            
            $(function () {
                setInterval(myF, 1000);
            });

        </script>

    </head>
    <body>
    <header class="header">
        <div>
            <nav class="navbar navbar-inverse n_radio">
                <button type="button" class="navbar-toggle collapsed white" data-toggle="collapse" data-target="#menu">
                    &#9776;
                </button>            
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand display-3" href="<c:url value="/index" />"><strong>AOJ</strong></a>
                    </div>
                    <div class="collapse navbar-collapse" id="menu">

                        <ul class="nav navbar-nav" id="menu">
                            <li class="active"><a href="<c:url value="/index" />" id="menu">Home</a></li>  
                            <li><a href="<c:url value="/list_contest" />">Contest</a></li> 
                            <li><a href="<c:url value="/problems" />">Problems</a></li> 
                            <li><a href="<c:url value="/ranking" />">Ranking</a></li> 
                            <li><a href="<c:url value="/status" />">Status</a></li>
                            <li></a><a href="<c:url value="/credits" />">Credits</a></li>       
                            <li><a href="https://icpcangola.wordpress.com" target="_blank">ACM-ICPC Angola</a></li>
                        </ul>

                        <div class="navbar-form navbar-right">
                            <span><a href="?lang=en">en</a> | <a href="?lang=pt_BR">pt</a></span>
                        </div>

                        <c:if test="${online eq false}">
                            <form action="<c:url value="/login"/>" method="post" class="navbar-form navbar-right">
                                <div class="form-group">
                                    <input type="text" name="login" class="form-control" placeholder="Username">
                                    <input type="password" name="senha" class="form-control" placeholder="Password">
                                </div>
                                <button type="submit" class="btn btn-primary">Login</button>
                            </form>
                        </c:if>

                        <c:if test="${online eq true}">
                            <div class="dropdown navbar-form navbar-right">
                                <button class="btn btn-primary dropdown-toggle" type="button" 
                                        data-toggle="dropdown">${usuario.login}<span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu dropdown-menu-right">
                                    <li><a href="perfil?uid=${usuario.id}">View profile</a></li>
                                    <li><a href="edit_user?uid=${usuario.id}">Edit profile</a></li>
                                    <li><a href="<c:url value="/logout"/>">Logout</a></li>
                                </ul>
                            </div>
                        </c:if>

                    </div>
                </div>
            </nav>

            <div class="container">
                <div class="col-md-12">
                    <div class="well">
                        <h2>Angola Online Judge</h2>
                        <c:if test="${online eq false}">
                            <p>Make your <a href="<c:url value="/registrar"/>">Register</a></p>
                        </c:if>
                            <p>Server Time: <span id="tempo"></span></p>
                    </div>
                    <c:import url="/templates/alertas.jsp"/>
                </div>
            </div>

        </div>
    </header>



</body>

</html>
