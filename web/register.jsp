<%-- 
    Document   : register
    Created on : 27/09/2017, 19:35:53
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>AOJ-Register</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link href="css/style.css" rel="stylesheet">
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>

        <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>
        <script>
            $(function () {
                $("#date").datepicker();
            });
        </script>

    </head>
    <body>        

        <div class="container">

            <div class="row alert">
                <c:import url="templates/header.jsp"/>

                <div class="col-md-4">
                    <c:import url="templates/menu_esquerdo.jsp"/>
                </div>
                <div class="col-md-8"> 


                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <p class="panel-title">Register User</p>
                        </div>
                        <div class="panel-body">
                            <sf:form modelAttribute="usuario" name="myForm" action="registrar">
                                 
                                <div class="form-group">
                                    <label for="txtUsername">Username*</label>
                                    <sf:input path="login" type="text" class="form-control" placeholder="Username"/>
                                </div>
                                <div class="form-group">
                                    <label for="txtFirstName">First name*</label>
                                    <input name="first_name" type="text" class="form-control" 
                                           placeholder="First name" id="txtFirstname">
                                </div>
                                <div class="form-group">
                                    <label for="txtLastName">Last name*</label>
                                    <sf:input path="last_name" type="text" class="form-control" placeholder="Last name" />
                                </div>
                                <div class="form-group">
                                    <label for="cboGender">Gender</label>
                                    <sf:select path="gender" class="form-control">
                                        <sf:option value="M" />
                                        <sf:option value="F" />
                                    </sf:select>
                                </div>
                                <div class="form-group">
                                    <label for="date">Date of birth</label>
                                    <sf:input id="date" type="date" path="birthday" class="form-control" 
                                              placeholder="Date of birth" />
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
                                        <sf:option 
                                            value="ISUTIC (Instituto Superior de Tecnologia de Informação e Comunicação)"/>
                                        <sf:option value="UCI (Universidad de las Ciencias Informáticas)" />
                                    </sf:select>
                                </div>
                                <div class="form-group">
                                    <label for="txtemail">E-mail*</label>
                                    <sf:input type="email" path="email" class="form-control" />
                                </div>
                                <div class="form-group">
                                    <label for="cboLanguage">Default GUI Language</label>
                                    <sf:select path="gui_language" class="form-control">
                                        <sf:option value="English" />
                                        <sf:option value="Spanish" />
                                        <sf:option value="Portuguese" />
                                    </sf:select>
                                </div>
                                <div class="form-group">
                                    <label for="txtPassword">Password*</label>
                                    <sf:input path="password" type="password" class="form-control" 
                                              placeholder="***" id="txtPassword" />
                                </div>
                                <div class="form-group">
                                    <label for="txtConfirmPassword">Confirm Password*</label>
                                    <sf:input path="confirm_password" type="password" class="form-control" 
                                              placeholder="***" id="txtConfirmPassword" />
                                </div>
                                <div class="form-group">
                                    <label for="cboCountry">Security question</label>
                                    <sf:select path="secure_question" class="form-control">
                                        <sf:option value="Name Mother?" />
                                        <sf:option value="Favorite Pet?" />
                                    </sf:select>

                                    <label for="cboCountry">Security answer</label>
                                    <sf:input path="secure_answer" type="password" class="form-control" 
                                              placeholder="answer" id="txtConfirmPassword" />
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input name="check" class="form-check-input" type="checkbox" value="">
                                        I agree with the <a>AOJ Terms of Service.</a>
                                    </label>
                                </div>
                                <div class="text-right">
                                    <input type="submit" class="btn btn-primary" value="Create"/>
                                    <input type="submit" class="btn btn-primary" value="Reset"/>
                                </div>
                            </sf:form>
                        </div>
                    </div>
                </div>

            </div>
            <c:import url="templates/footer.jsp"/>

        </div>
    </body>

</html>
