<%-- 
    Document   : add_contest
    Created on : 07/11/2017, 23:29:11
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create contest</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link href="css/style.css" rel="stylesheet">
        <script type="text/javascript" href="js/bootstrap.min.js"></script>
        
        <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>
        <script>
            $(function () {
                $("#cstart").datepicker();
                $("#cend").datepicker();
            });
        </script>
    </head>
    <body>
        <c:import url="../templates/header.jsp"/>
        
        <div class="row alert">
            <div class="col-md-4">
                <c:import url="../templates/menu_esquerdo.jsp"/>
            </div>
            <div class="col-md-8">
                <h4>Server time: </h4>
                <form role="form" action="add_contest_form" method="post">
                    <div class="form-group">
                        <label for="cname">Name</label>
                        <input id="cname" type="text" class="form-control" required />
                    </div>

                    <div class="form-group">
                        <label for="cstart">Start date</label>
                        <input type="text" id="cstart" class="form-control" required />
                    </div>

                    <div class="form-group">
                        <label for="cend">End date</label>
                        <input type="text" id="cend" class="form-control" required />
                    </div>
                    
                    <div class="form-group">
                        <label for="cend">Problems</label>
                    </div>
                    
                    <div class="form-group">
                        <label for="cend">Users</label>
                    </div>

                    Minutes, the standing will stop on this time <br>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary" value="Create" />
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
