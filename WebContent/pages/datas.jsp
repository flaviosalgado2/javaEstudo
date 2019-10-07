<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Datas</title>
</head>

<body>
	<form action="calcularDataFinal" method="post">

		<label>Data Inicial</label> 
		<input id="data" name="data"> 
		
		<label>Tempo em horas</label> 
		<input id="tempo" type="text" name="tempo"> 
		
		<input type="submit" value="Calcular">

	</form>
		
	<br/>
	<br/>
	
	<label>Data Final</label>
	<input type="text" id="dataFinal" name="dataFinal" readonly="readonly" value="${dataFinal}" >
	
	<label>Total de dias</label>
	<input type="text" id="dias" name="dias" readonly="readonly" value="${dias}" >
	
</body>

<script type="text/javascript">

$(function(){
	$('#data').datepicker({dateFormat: 'dd/mm/yy'});
});

</script>

</html>