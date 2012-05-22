<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="<c:url value="/usuario/adicionar"/>" method="POST">
	<fieldset>
	
	<legend>Adicionar Usuario</legend>
	
	<label for="nome">Nome:</label>
	<input id="nome" type="text" name="usuario.nome" value="${usuario.nome }"/>
	
	<label for="login">Login:</label>
	<input id="login" name="usuario.login" value="${usuario.login}" />
	
	<label for="senha">Senha:</label>
	<input id="senha" type="text" name="usuario.senha" value="${usuario.senha}"/>
	
	<button type="submit">Enviar</button>
	</fieldset>
</form>
</body>
</html>