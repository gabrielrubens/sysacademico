package com.prisila.controller.filter;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

import com.prisila.annotation.Admin;
import com.prisila.annotation.Publico;
import com.prisila.controller.UsuarioController;
import com.prisila.modelo.entidade.UsuarioLogado;

@Intercepts
public class LoginInterceptor implements Interceptor {
	
	private final UsuarioLogado usuarioLogado;
	private final Result result;
	
	public LoginInterceptor(UsuarioLogado usuario, Result result) {
		this.usuarioLogado = usuario;
		this.result = result;
	}
	
	@Override
	public boolean accepts(ResourceMethod method) {
		boolean isAcessoPermitido = true;
		if (method.containsAnnotation(Admin.class)) {
			isAcessoPermitido = usuarioLogado.isLogado() && usuarioLogado.isAdmin();
		} else if (!method.containsAnnotation(Publico.class)) {
			isAcessoPermitido = usuarioLogado.isLogado();
		}
		return !isAcessoPermitido;
	}
	
	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance)
			throws InterceptionException {
		if(method.getMethod().getAnnotation(Admin.class) != null) {
			result.redirectTo("/");
		} else {
			Path pathAnnotation = method.getMethod().getAnnotation(Path.class);
			if(pathAnnotation != null) {
				String[] s = pathAnnotation.value();
				if (s != null && s.length > 0) {
					// TODO pegar a verdadeira url que o usuario tentou acessar
					// usuarioLogado.setUrlAposLogin(s[0]);
				}
				result.redirectTo(UsuarioController.class).login();
			}
		}
	}
}