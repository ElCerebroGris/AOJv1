<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
        <link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>
        <script type="text/javascript" src="js/jquery.validate.min.js"></script>
        <script type="text/javascript" href="js/bootstrap.min.js"></script>


    </head>
    <body>
        <c:import url="../templates/header.jsp"/>

        <div class="container">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h3 class="text-center">Administration panel: Manage Problem</h3> 
                    <sf:form modelAttribute="problema" id="formulario" action="add_problem" enctype="multipart/form-data">
                        <div class="row">

                            <div class="form-group col-xs-12">
                                <label class="col-xs-3">PID</label>
                                <div class="col-xs-8">
                                    <sf:input id="pid" path="id"  class="form-control" readonly="true" value="0" type="text" />
                                </div>
                            </div>
                            <div class="form-group col-xs-12">
                                <label class="col-xs-3">Name</label>
                                <div class="col-xs-8">
                                    <sf:input id="pnome" path="nome" class="form-control"  type="text"/>
                                </div>
                                <span class="glyphicon glyphicon-asterisk"></span>
                            </div>
                            <div class="form-group col-xs-12">
                                <label class="col-xs-3">Time(MS)</label>
                                <div class="col-xs-8">
                                    <sf:input id="tempo" path="tempo" class="form-control"  type="number" />
                                </div>
                            </div>
                            <div class="form-group col-xs-12">
                                <label class="col-xs-3">Category</label>
                                <div class="col-xs-8">
                                    <sf:select path="category" class="form-control">
                                        <sf:option value="Ad-Hoc" />
                                        <sf:option value="Math" />
                                        <sf:option value="Graph" />
                                        <sf:option value="Sorting and Searching" />
                                        <sf:option value="Game Theory" />
                                    </sf:select>
                                </div>
                            </div>
                        </div>
                        
                        <div class="panel panel-default">
                            <div class="panel-heading">Description</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <sf:textarea path="texto" rows="5" class="form-control fixed" />
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">Input expecification</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <sf:textarea path="expecificacaoEntrada" wrap="hard" 
                                                 rows="5" class="form-control fixed" />
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">Output expecification</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <sf:textarea path="expecificacaoSaida" wrap="hard" rows="5" class="form-control fixed" />
                                </div>
                            </div>
                        </div>

                        <div class="panel panel-default">
                            <div class="panel-heading">Input example</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <sf:textarea path="exemploEntrada" wrap="hard" rows="5" 
                                                 class="form-control fixed" id="sample1" />
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">Output example</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <sf:textarea path="exemploSaida" rows="5" class="form-control fixed" />
                                </div>
                            </div>
                        </div>

                        <div class="panel panel-default">
                            <div class="panel-heading">File(s) input</div>
                            <div class="panel-body">
                                <input type="file" name="input[]" multiple="multiple">  
                            </div>
                        </div>

                        <div class="panel panel-default">
                            <div class="panel-heading">File(s) output</div>
                            <div class="panel-body">
                                <input type="file"  name="output[]" multiple="multiple">  
                            </div>
                        </div>

                        <div class="text-right">
                            <input type="submit" class="btn btn-primary" value="Create"/>
                            <input type="submit" class="btn btn-primary" value="Close"/>
                        </div>
                        <div class="pull-right">
                            <a href="#" title="Back to Top"><i class="glyphicon glyphicon-menu-up"></i></a>
                        </div>
                    </sf:form>
                </div>  
            </div>
        </div>

        <!--Codigo JSP para colocar o footer-->    
        <c:import url="../templates/footer.jsp"/>

        <!--Codigo JavaScript para funcionar a busca de fixeiro-->  
        <script type="text/javascript">
            // Fake file upload
            document.getElementById('fake-file-button-browse').addEventListener('click', function () {
                document.getElementById('files-input-upload').click();
            });

            document.getElementById('files-input-upload').addEventListener('change', function () {
                document.getElementById('fake-file-input-name').value = this.value;

                document.getElementById('fake-file-button-upload').removeAttribute('disabled');
            });
        </script>

        <script type="text/javascript">
            // Fake file upload
            document.getElementById('fake-file-button-browse2').addEventListener('click', function () {
                document.getElementById('files-input-upload2').click();
            });

            document.getElementById('files-input-upload2').addEventListener('change', function () {
                document.getElementById('fake-file-input-name2').value = this.value;

                document.getElementById('fake-file-button-upload2').removeAttribute('disabled');
            });
        </script>


    </body>


</html>