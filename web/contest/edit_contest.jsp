<%-- 
    Document   : edit_contest
    Created on : 07/11/2017, 23:48:51
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    </head>
    <body>
        
        <div class="alert alert-success">
            <strong>Contest created!</strong> you can edit now
        </div>
        
        <h3>Server time: </h3>
        <a href="#">Invite</a> - <a href="#">Edit contest</a> - <a href="#">Delete contest</a> - 
        <a href="#">Add problems</a>
        <br>

        <h3>Contest Name: 113 - Again (2 hour and 10 mins)</h3>

        <form action="add_contest_form" method="post">
            <label>Name:</label> <input type="text" name="cname" /> <br>
            Start date:<input type="text" name="cname" /> MM/DD/YYYY <br>
            Start time:<input type="time" name="cname" /> HH:MM <br>
            End date:<input type="text" name="cname" /> MM/DD/YYYY <br>
            End time:<input type="time" name="cname" /> HH:MM <br>
            Frozen time:<input type="number" name="cname" /> Minutes, the standing will stop on this time <br>
            <input type="submit" value="Update" />
        </form>
    </body>
</html>
