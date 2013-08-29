<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solr Crud - Resultado da Pesquisa</title>
</head>
<body>
	<a href="../view/">Voltar</a>
	<h1>Resultado da Busca</h1>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Apelido</th>
				<!-- 
				<th>Interesses</th>
				 -->
				<th>Version</th>
				<th>Editar</th>
			</tr>
		</thead>
		<tbody>
			${listagem}
		</tbody>
	</table>
</body>
</html>