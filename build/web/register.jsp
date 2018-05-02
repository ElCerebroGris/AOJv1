<%-- 
    Document   : register
    Created on : 27/09/2017, 19:35:53
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link href="css/style.css" rel="stylesheet">
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>

        <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>
        <script>
            $(function () {
                $("#date").datepicker();
            });
        </script>

    </head>
    <body>        
        <c:import url="templates/header.jsp"/>
        <div class="container">
            <form name="myForm" action="<c:url value="registrar"/>" method="post">
                <div class="row alert">
                    <div class="col-md-3">

                    </div>
                    <div class="col-md-6 offset-md-3"> 
                        <h2 class="text-center">Register User Account</h2>  
                        <div class="form-group">
                            <label for="txtUsername">Username*</label>
                            <input name="login" type="text" class="form-control" placeholder="Username" 
                                   id="txtUsername" required minlength="8" maxlength="128">
                        </div>
                        <div class="form-group">
                            <label for="txtFirstName">First name*</label>
                            <input name="first_name" type="text" class="form-control" 
                                   placeholder="First name" id="txtFirstname" required>
                        </div>
                        <div class="form-group">
                            <label for="txtLastName">Last name*</label>
                            <input name="last_name" type="text" class="form-control" 
                                   placeholder="Last name" id="txtLastname" required>
                        </div>
                        <div class="form-group">
                            <label for="cboGender">Gender</label>
                            <select name="gender" class="form-control">
                                <option value="M">Male</option>
                                <option value="F">Female</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="date">Date of birth</label>
                            <input id="date" type="date" name="birthday" class="form-control" placeholder="Date of birth">
                        </div>
                        <div class="form-group">
                            <label for="cboCountry">Country</label>
                            <select name="country" class="form-control">
                                <option>Angola</option>
                                <option>Cuba</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="cboInstitution">Institution</label>
                            <select name="institution" class="form-control">
                                <option>ISUTIC (Instituto Superior de Tecnologia de Informação e Comunicação)</option>
                                <option>UCI (Universidad de las Ciencias Informáticas)</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="txtemail">E-mail*</label>
                            <input type="email" name="email" class="form-control" 
                                   placeholder="example@name.com" id="txtEmail" required>
                        </div>
                        <div class="form-group">
                            <label for="cboLanguage">Default GUI Language</label>
                            <select name="gui_language" class="form-control">
                                <option>English</option>
                                <option>Spanish</option>
                                <option>Portuguese</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="txtPassword">Password*</label>
                            <input name="password" type="password" class="form-control" 
                                   placeholder="***" id="txtPassword" minlength="8">
                        </div>
                        <div class="form-group">
                            <label for="txtConfirmPassword">Confirm Password*</label>
                            <input name="password2" type="password" class="form-control" 
                                   placeholder="***" id="txtConfirmPassword" minlength="8">
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" type="checkbox" value="" required>
                                I agree with the <a>AOJ Terms of Service.</a>
                            </label>
                        </div>
                        <div class="text-right">
                            <input type="submit" class="btn btn-primary" value="Create"/>
                            <input type="submit" class="btn btn-primary" value="Reset"/>
                        </div>

                    </div>
                </div>
            </form>
        </div>
    </body>
    <c:import url="templates/footer.jsp"/>
</html>
