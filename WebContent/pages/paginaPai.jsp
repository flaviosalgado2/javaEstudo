<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<h1>Página pai load jQuery</h1>
	<input type="button" value="Carregar Página" onclick="carregar();">

	<div id="mostrarPaginaFilha"></div>
</body>
<script type="text/javascript">
	function carregar() {

		$("#mostrarPaginaFilha").load('paginaFilha.jsp');

	}
</script>
</html>