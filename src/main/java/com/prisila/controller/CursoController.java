package com.prisila.controller;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

import com.prisila.dao.CursoDao;
import com.prisila.modelo.entidade.Curso;
import com.prisila.util.Mensagem;
import com.prisila.util.Mensagem.TipoMensagem;

@Resource
public class CursoController extends Controller {
	
	private final CursoDao cursoDao;
	private final Result result;
	private Validator validator;
	
	public CursoController(CursoDao dao, Result result, Validator validator) {
		this.cursoDao = dao;
		this.result = result;
		this.validator = validator;
	}
	
	@Get
	@Path("/cursos/adicionar")
	public void adicionar() {
	}
	
	@Post
	@Path("/cursos/adicionar")
	public void adicionar(Curso curso) {
		validator.validate(curso);
		validator.onErrorRedirectTo(this).adicionar();
		
		cursoDao.salvar(curso);
		result.redirectTo(this).listar();
	}
	
	@Get
	@Path("/cursos/{id}")
	public Curso editar(Long id) {
		return cursoDao.carrega(id);
	}
	
	@Put
	@Path("/cursos/{curso.id}")
	public void alterar(Curso curso) {
		validator.validate(curso);
		validator.onErrorRedirectTo(this).adicionar();
		
		cursoDao.atualiza(curso);
		result.redirectTo(this).listar();
	}
	
	@Get
	@Path("/cursos/listar")
	public List<Curso> listar() {
		return cursoDao.listaTudo();
	}
	
	@Delete
	@Path("/cursos/{id}")
	public void deletar(Long id) {
		try {
			cursoDao.deletar(id);
			setMensagem(result, new Mensagem(TipoMensagem.SUCCESS, "Curso deletado com sucesso"));
		} catch(Exception e) {
			setMensagem(result, new Mensagem(TipoMensagem.ERROR, "Não foi possível deletar o curso"));
		}
		result.redirectTo(this).listar();
	}	
}