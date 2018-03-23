$(function () {
    $('.tlt').textillate();
})


function MudarEstilo()
{
	var div= $("#divDanger");
	var inp= $("#inputDanger");

    if (inp.val()=="Elisa")
    {
    	div.attr("class","form-group has-success");
		inp.attr("class","form-control form-control-success");
    }
    else
    {
    	div.attr("class","form-group has-danger");
		inp.attr("class","form-control form-control-danger");
		inp.val("Erro")	;
    }
	

}