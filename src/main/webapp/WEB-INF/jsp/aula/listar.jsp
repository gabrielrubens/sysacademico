<%@include file="../taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Aluno: ${matriculaSessao.matricula.aluno.nome }</h3>
	
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Curso</th>
				<th>Data</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${aulaMatriculaList }" var="aulaMatricula">
				<tr>
					<td>${aulaMatricula.matricula.curso.nome }</td>
					<td><fmt:formatDate value="${aulaMatricula.aula.timestamp.time }" pattern="dd/MM/yyyy 'às' HH:mm (EEEE)" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>