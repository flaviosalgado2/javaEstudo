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
	<h3>Capturando exceções com jquery</h3>
	<input type="text" value="teste adasas" id="txtvalor">
	<input type="button" onclick="testarExcecao();" value="testar exceção">
</body>
<script type="text/javascript">
	function testarExcecao() {
		var valorInformado = $('#txtvalor').val();

		$.ajax({
			method : "POST",
			url : "capturarExcecao", //para a servlet
			data : {
				valorParam : valorInformado
			}
		}).done(function(response) {//sempre capta o retorno
			alert("Sucesso" + response);
		//fazer algo se der errado
		}).fail(function(xhr, status, errorThrown) {//resposta erro - algum problema ocorreu
			alert("Errro: " + xhr.responseText);
		});

	}
</script>
</html>