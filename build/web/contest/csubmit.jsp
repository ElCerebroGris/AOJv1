<%-- 
    Document   : submit
    Created on : 01/10/2017, 18:30:08
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>AOJ - Submit</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link href="css/style.css" rel="stylesheet">
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>

    </head>
    <body>

        <div class="container">
            <div class="row alert">
                <c:import url="../templates/header.jsp"/>

                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading text-center">
                            <h4>${problema.id} - ${problema.nome}</h4>
                            <p>time limit per test: ${problema.tempo} ms</p>
                            <p>memory limit per test: 256 megabytes</p>
                            <p>input: standard input</p>
                            <p>output: standard output</p>
                        </div>
                        <div class="panel-body">
                            <i class="text-center h4">Submit your soluction for the probem</i>

                            <form action="csubmission?cid=${contest.id}&pid=${problema.id}" method="post" class="form-inline text-center alert" >
                                <div class="form-group">
                                    <label for="cboLanguage">Language</label>
                                    <select name="linguagem" class="form-control">
                                        <option value="java">Java</option>
                                        <option value="c">C</option>
                                        <option>C++</option>
                                    </select>
                                </div>
                                <br>
                                <div class="form-group">
                                    <textarea name="codigo" cols="100" rows="25" class="form-control text-center"></textarea>
                                </div>
                                <div class="text-center">
                                    <input type="submit" class="btn btn-primary btn-lg" value="Send" />
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
            <c:import url="../templates/footer.jsp"/>
        </div>
    </body>

</html>