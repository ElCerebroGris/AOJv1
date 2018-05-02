<%-- 
    Document   : edit_user
    Created on : 2/abr/2018, 23:06:54
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Edit User</title>
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
            <form name="myForm" action="<c:url value="edit_user"/>" method="post">
                <div class="row alert">
                    <div class="col-md-4">
                        <c:import url="templates/menu_esquerdo.jsp"/>
                    </div>
                    <div class="col-md-8"> 
                        <h2 class="text-center">Edit profile</h2>  
                        <div class="form-group">
                            <label for="txtUsername">Username*</label>
                            <input name="login" type="text" class="form-control" value="${user.login}" 
                                   id="txtUsername" required minlength="8" maxlength="128" disabled="disabled">
                        </div>
                        <div class="form-group">
                            <label for="txtFirstName">First name*</label>
                            <input name="first_name" type="text" class="form-control" id="txtFirstname" 
                                   value="${user.first_name}">
                        </div>
                        <div class="form-group">
                            <label for="txtLastName">Last name*</label>
                            <input name="last_name" type="text" class="form-control" id="txtLastname" 
                                   value="${user.last_name}">
                        </div>
                        <div class="form-group">
                            <label for="cboGender">Gender</label>
                            <select name="gender" class="form-control" disabled="disabled">
                                <option value="M">${user.gender}</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="date">Date of birth</label>
                            <input id="date" type="date" name="birthday" class="form-control" value="${user.birthday}">
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
                            <input type="email" name="email" class="form-control" id="txtEmail" value="${user.email}">
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

