<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
	<div class="row">
		<div class="span8">
			<h2>Cadastrar novo usuário</h2>
			<tag:validationalert />
			
			<c:if test="${not empty errors}">
				<div class="alert alert-block alert-error">
					<h4>Corrija os seguintes erros</h4>
					<ul>
						<c:forEach items="${errors}" var="error">
							<li>${error.message}</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			
			<form action="<c:url value="/usuarios/adicionar" />" method="POST" class="form-horizontal">
				<div class="control-group">
					<div class="control-label">Nome completo</div>
					<div class="controls">
						<input type="text" maxlength="50" name="usuario.nome" placeholder="nome completo" class="input-xlarge" value="${usuario.nome}" />
					</div>
				</div>
				<div class="control-group">
					<div class="control-label">Nome de usuário</div>
					<div class="controls">
						<input type="text" maxlength="50" class="input-large" placeholder="somente letras e números" name="usuario.login"
							value="${usuario.login}" />
					</div>
				</div>
				
				<div class="control-group">
					<div class="control-label">É administrador?</div>
					<div class="controls">
						<input type="checkbox" name="usuario.admin" <c:if test="${not empty usuario.admin && usuario.admin == true}">checked="checked"</c:if> />
						<span class="help-text">
							O usuário administrador tem permissão de gerenciar o acesso de outros usuários
						</span>
					</div>
				</div>

				<div class="control-group">
					<div class="control-label">Senha</div>
					<div class="controls">
						<input type="password" maxlength="50" class="input-medium" name="usuario.senha" placeholder="mínimo de 8 caracteres" /> <span class="help-block">
							<span class="label label-info">Dica</span> não use datas de aniversário ou nomes de pessoas relacionadas </span>
					</div>
				</div>
				<div class="control-group">
					<div class="control-label">Confirmação da senha</div>
					<div class="controls">
						<input type="password" maxlength="50" class="input-medium" name="confirmacaoSenha" placeholder="igual à acima" /> <span class="help-block">Repita
							a senha para evitar erros de digitação</span>
					</div>
				</div>
				<div class="form-actions">
					<button class="btn btn-primary pull-left" type="submit">Cadastrar</button>
					<a class="btn pull-right" href="<c:url value="/usuarios/listar" />">voltar</a>
				</div>
			</form>
		</div>

		<div class="span4">
			<div class="well voceSabiaQue textJustify">
				<h3>Você sabia que...</h3>
				<p>
					As senhas utilizadas por todos os usuários deste sistema são encriptadas usando um método chamado de <strong>hashing</strong>.
				</p>
				<p>
					O <strong>hashing</strong> consiste em um algoritmo que transforma a senha em uma sequência de caracteres única,
					que não é gerada por nenhuma outra senha.
				</p>
				<p>Assim, mesmo que um invasor ganhe acesso ao banco de dados e visualize as senhas, elas estarão
					criptografadas, impossibilitando o uso para crimes como falsificação de identidade.</p>
			</div>
		</div>
	</div>
</body>
</html>