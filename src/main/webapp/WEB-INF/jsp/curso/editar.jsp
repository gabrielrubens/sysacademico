<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<form action="<c:url value="/cursos/${curso.id }"/>" method="POST">
		<fieldset>
			<legend>Curso</legend>
			<label for="nome">Nome</label> 
			<input id="nome" type="text" name="curso.nome" value="${curso.nome }" />
			<label for="duracaoAula">Duração da Aula</label>
			<input id="duracaoAula" class="input-mini" type="text" name="curso.duracaoAula" value="${curso.duracaoAula }" /> <small class="muted">Em minutos</small>
		</fieldset>
		<button type="submit" class="btn" name="_method" value="PUT">Alterar</button>
		<button type="submit" class="btn btn-danger" name="_method" value="DELETE">Excluir</button>
	</form>
</body>
</html>