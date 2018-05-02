<%-- 
    Document   : credits
    Created on : 27/09/2017, 21:08:13
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>AOJ-Credits</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link href="css/style.css" rel="stylesheet">
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>

    </head>
    <body>
        <c:import url="templates/header.jsp"/>
        <div class="container">
            <div class="row alert">
                <div class="col-md-12">
                    <h2 class="text-center alert">AOJ exists thanks to</h2>
                    <h3 class="text-center">Institution</h3>
                    <table class="table table-bordered ">
                        <tr class="bg-primary">
                            <th>Name</th>
                        </tr>
                        <tr>
                            <td><a href="#" target="_blank">ISUTIC (Instituto Superior de Tecnologia de Informação e Comunicação)</a></td>
                        </tr>

                    </table>
                    <h3 class="text-center">Team</h3>
                    <table class="table">
                        <tr class="bg-primary">
                            <th>Name</th>
                            <th>Intitution</th>
                            <th>Country</th>
                        </tr>
                        <tr>
                            <td>Osvaldo José Zamba Calombe</td>
                            <td>ISUTIC</td>
                            <td>Angola</td>
                        </tr>
                        <tr>
                            <td>Elisa Vanussa de Silveira Capololo</td>
                            <td>ISUTIC</td>
                            <td>Angola</td>
                        </tr>
                        <tr>
                            <td>Ariclene Narciso</td>
                            <td>ISUTIC</td>
                            <td>Angola</td>
                        </tr>
                    </table>
                    <h3 class="text-center">Special credits</h3>
                    <table class="table">
                        <tr class="bg-primary">
                            <th>Name</th>
                            <th>Institution</th>
                            <th>Country</th>
                        </tr>
                        <tr>
                            <td>Tomas Orlando Junco</td>
                            <td>UCI</td>
                            <td>Cuba</td>
                        </tr>
                        <tr>
                            <td>Angel Vasques</td>
                            <td>UCI</td>
                            <td>Cuba</td>
                        </tr>
                        <tr>
                            <td>Yoenes Pantoja</td>
                            <td>UCI</td>
                            <td>Cuba</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <c:import url="templates/footer.jsp"/>
    </body>
</html>

