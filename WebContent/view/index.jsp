<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Solr Crud - Início</title>
</head>
<body>
	<form name="frmSearch" id="frmSearch" action="MainServlet" method="post">
		<input type="hidden" name="action" id="action" value="searchUser" />
		Pesquisar <input type="text" name="qTerm" id="qTerm" value="" /> pelo campo
		<select name="qField" id="qField">
			<option value="nome">Nome</option>
			<option value="apelido">Apelido</option>
			<option value="interesses">Interesses</option>
			<option value="id">ID</option>
		</select>
		<button type="submit">Pesquisar</button>
	</form>

	<br/><br/>
	<a href="cadastro.jsp">
	Novo Registro
	</a>
</body>
</html>