<%-- 
    Document   : index
    Created on : 27/09/2017, 00:12:04
    Author     : Zamba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html lang="en">
    <head>
        <title></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script type="text/javascript" href="js/bootstrap.min.js"></script>
    </head>

    <body class="">
        <c:import url="templates/header.jsp"/>
        <div class="container-fluid">
            <div class="container">

                <div class="row">

                    <div class="col-sm-4">
                        <c:import url="templates/menu_esquerdo.jsp"/>
                    </div>

                    <div class="col-md-8">
                        <div class="row">

                            <div class="col-md-12">
                                <c:if test="${online eq true}">
                                    <div class="form-group ss">
                                        <form action="<c:url value="/comentar"/>" method="post">
                                            <textarea name="texto" class="form-control" id="txtarea" rows="5" id="comment" 
                                                      style="max-width:100%;max-height:95px;" placeholder="Ask here..."></textarea>
                                            <input type="submit" class="btn btn-primary pull-right exc" id="btt" value="Share" />
                                        </form>
                                    </div>
                                </c:if>
                                <br><br>

                                <table>
                                    <tr class="bg-primary">
                                        <th class="text-center">#</th>
                                    </tr>
                                    <c:forEach items="${mural}" var="m">
                                        <tr>
                                            
                                                <div class="media fst">
                                                    <div class="media-left">
                                                        <img src="img/img_avatar.png" class="media-object" style="width:60px">
                                                    </div>
                                                    <div class="media-body">
                                                        <h4 class="media-heading">${m.usuario} <span class="text-muted small small small">posted on ${m.data}</span></h4>
                                                        <p class="h4"><strong>${m.texto}</strong></p>

                                                    </div>

                                                </div>
                                            
                                        </tr>

                                    </c:forEach>
                                </table>

                            </div>

                            <nav>
                                <ul class="pager">
                                    <li><a href="#">Previous</a></li>
                                    <li><a href="#">Next</a></li>
                                </ul>
                            </nav>


                        </div>

                    </div>

                </div>
            </div>

            <div class="row">
                <c:import url="templates/footer.jsp"/>
            </div>

    </body>
</html>
