<%-- 
    Document   : teams
    Created on : 1/mai/2018, 16:47:08
    Author     : Zamba
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>AOJ - Teams</title>
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
                    <c:import url="../templates/menu_esquerdo.jsp"/>
                </div>

                <div class="col-md-8">

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <p class="panel-title">Teams</p>
                        </div>
                        <div class="panel-body">
                            <table id="tabela" class="table table-bordered text-center">
                                <thead>
                                    <tr class="bg-primary">
                                        <th class="text-center">ID</th>
                                        <th class="text-center">Team</th>
                                        <th class="text-center">Category</th>
                                        <th class="text-center hidden-xs">Pontuation</th>
                                        <th class="text-center">Option</th>

                                    </tr>
                                </thead>
                                <tbody>
                                        <tr>
                                            
                                        </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="panel-footer">
                            <a href="<c:url value="add_problem_form" />" class="btn btn-primary">
                                New team</a><br><br>
                        </div>
                    </div>
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
<c:import url="../templates/footer.jsp"/>
</html>
