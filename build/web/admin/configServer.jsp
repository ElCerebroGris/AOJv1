<%-- 
    Document   : configServer
    Created on : 14/11/2017, 17:56:36
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AOJ - Config</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <c:import url="../templates/header.jsp"/>
        <div class="container">
            <div class="row alert">
                <div class="col-md-4">                    
                    <c:import url="../templates/menu_esquerdo.jsp"/>
                </div>

                <div class="col-md-8">

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <p class="panel-title">Configuration</p>
                        </div>
                        <div class="panel-body">
                            <form method="post">
                                <div class="form-group">
                                    <label for="files">File location</label><br>
                                    <input type="text" id="files" size="60" value="${files}"/>
                                </div>
                                <div class="form-group">
                                    <label for="files">Data Base</label><br>
                                    <input type="text" id="files" value="${db}" size="60"/>
                                </div>
                                <div class="form-group">
                                    <input type="submit" class="btn btn-primary" value="Update"/>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <c:import url="../templates/footer.jsp"/>
</html>
