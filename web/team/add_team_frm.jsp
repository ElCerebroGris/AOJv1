<%-- 
    Document   : add_contest
    Created on : 07/11/2017, 23:29:11
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create contest</title>
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
                                <p class="panel-title">Add Team</p>
                            </div>
                            <div class="panel-body">
                                <sf:form id="form" commandName="team" modelAttribute="team" role="form" action="add_team">
                                    <div class="form-group">
                                        <label for="txtUsername">Username</label>
                                        <sf:input path="login" type="text" class="form-control" id="txtUsername"/>
                                        <span class="label label-danger">${erros.get(0)}</span>
                                    </div>
                                    <div class="form-group">
                                        <label for="cboCountry">Country</label>
                                        <sf:select path="country" class="form-control">
                                            <sf:option value="Angola" />
                                            <sf:option value="Cuba" />
                                        </sf:select>
                                    </div>
                                    <div class="form-group">
                                        <label for="cboInstitution">Institution</label>
                                        <sf:select path="institution" class="form-control">
                                            <sf:option value="ISUTIC (Instituto Superior de Tecnologia de Informação e 
                                                       Comunicação)" />
                                            <sf:option value="UCI (Universidad de las Ciencias Informáticas)" />
                                        </sf:select>
                                    </div>
                                    <div class="form-group">
                                        <label for="txtPassword">Password*</label>
                                        <sf:input path="password" type="password" class="form-control" id="txtPassword" />
                                        <span class="label label-danger">${erros.get(1)}</span>
                                    </div>
                                    <div class="form-group">
                                        <label for="txtConfirmPassword">Confirm Password*</label>
                                        <sf:input path="confirm_password" type="password" class="form-control" 
                                                  placeholder="***" id="txtConfirmPassword" />
                                        <span class="label label-danger">${erros.get(2)}</span>
                                    </div>
                                    <div class="form-group">
                                        <label for="nome">Team name</label>
                                        <sf:input path="team_name" id="nome" type="text" class="form-control" />
                                        <span class="label label-danger">${erros.get(3)}</span>
                                    </div>
                                    <div class="form-group">
                                        <label for="user1">1st Member</label>
                                        <sf:input path="user1" id="user1" type="text" class="form-control" />
                                        <span class="label label-danger">${erros.get(4)}</span>
                                    </div>     
                                    <div class="form-group">
                                        <label for="user2">2nd Member</label>
                                        <sf:input path="user2" id="user2" type="text" class="form-control" />
                                        <span class="label label-danger">${erros.get(5)}</span>
                                    </div> 
                                    <div class="form-group">
                                        <label for="user3">3rd Member</label>
                                        <sf:input path="user3" id="user3" type="text" class="form-control" />
                                        <span class="label label-danger">${erros.get(6)}</span>
                                    </div> 

                                    <div class="form-group">
                                        <input type="submit" class="btn btn-primary" value="Create" />
                                    </div>
                                </sf:form>
                            </div>
                        </div>

                    </div>
                </div>
                <c:import url="../templates/footer.jsp"/>
            </div>
        </div>
    </body>
</html>
