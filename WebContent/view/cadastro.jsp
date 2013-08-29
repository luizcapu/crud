<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solr Crud - Cadastro</title>
</head>
<body>
	<a href="../view/">Voltar</a>

	<form name="frmCad" id="frmCad" action="MainServlet" method="post">
		<input type="hidden" name="action" id="action" value="postUser" />
		ID:
		<br/>
		<input type="text" name="id" id="id" value="${param.id}" />
		<br/>
		Nome:
		<br/>
		<input type="text" name="nome" id="nome" value="${param.nome}" />
		<br/>
		Apelido:
		<br/>
		<input type="text" name="apelido" id="apelido" value="${param.apelido}" />
		<br/>
		Interesses:
		<br/> 
		<textarea name="interesses" id="interesses" style="width: 350px; height: 100px;">${param.interesses}</textarea>
		<br/><br/>
		<button type="submit">Gravar</button>
		<button type="button" onclick="javascript: if(confirm('Excluir registro ?')) {document.frmCad.action.value='delUser'; document.frmCad.submit();}">Excluir</button>		
	</form>

</body>
</html>