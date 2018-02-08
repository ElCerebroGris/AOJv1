<%-- 
    Document   : header
    Created on : 27/09/2017, 00:06:28
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <li><a href="<c:url value="/problems" />">Problems</a></li> 
                            <li><a href="<c:url value="/ranking" />">Ranking</a></li> 
                            <li><a href="<c:url value="/status" />">Status</a></li>
                            <li></a><a href="<c:url value="/credits" />">Credits</a></li>       
                            <li><a href="https://icpcangola.wordpress.com" target="_blank">ACM-ICPC Angola</a></li>
                        </ul>

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
                            <form action="<c:url value="/logout"/>" method="get" class="navbar-form navbar-right">
                                <button type="submit" class="btn btn-primary">Logout</button>
                            </form>
                        </c:if>

                    </div>
                </div>
            </nav>

            <div class="jumbotron lado bg-dark white">
                <div class="container">
                    <div class="col-md-4">
                        <h2>Angola Online Judge</h2>
                        <c:if test="${online eq false}">
                            <p>Make your <a href="<c:url value="/registrar"/>">Register</a></p>
                        </c:if>
                    </div>

                </div>

            </div>
        </div>
    </header>



</body>

</html>
