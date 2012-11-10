package com.prisila.controller;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.prisila.dao.AlunoDao;
import com.prisila.dao.ResponsavelDao;
import com.prisila.modelo.entidade.Aluno;
import com.prisila.modelo.entidade.Responsavel;

@Resource
public class ResponsavelController extends Controller {
	
	private final ResponsavelDao dao;
	private final AlunoDao alunoDao;
	private final Result result;
	private List<Aluno> alunoList;
	
	public ResponsavelController(ResponsavelDao dao, AlunoDao alunoDao, Result result) {
		this.dao = dao;
		this.alunoDao = alunoDao;
		this.result = result;
	}
	
	@Get
	@Path("/responsaveis/adicionar")
	public void adicionar() {
		incluirListaNaResult();
	}
	
	@Post
	@Path("/responsaveis/adicionar")
	public void adicionar(Responsavel responsavel) {
		dao.salvar(responsavel);
		for (Aluno aluno : responsavel.getListaAluno()) {
			aluno = alunoDao.carrega(aluno.getId());
			aluno.adicionaVinculo(responsavel);
			alunoDao.atualiza(aluno);
		}
		result.redirectTo(AlunoController.class).cadastrar();
	}
	
	@Get
	@Path("/responsaveis/{id}")
	public Responsavel editar(Long id) {
		incluirListaNaResult();
		return dao.carrega(id);
	}
	
	@Put
	@Path("/responsaveis/{responsavel.id}")
	public void alterar(Responsavel responsavel) {
		dao.atualiza(responsavel);
		for (Aluno aluno : responsavel.getListaAluno()) {
			aluno = alunoDao.carrega(aluno.getId());
			aluno.adicionaVinculo(responsavel);
			alunoDao.atualiza(aluno);
		}
		result.redirectTo(this).listar();
	}
	
	@Get
	@Path("/responsaveis/listar")
	public List<Responsavel> listar() {
		return dao.listaTudo();
	}
	
	@Delete
	@Path("/responsaveis/{id}")
	public void deletar(Long id) {
		dao.deletar(id);
		result.redirectTo(this).listar();
	}
	
	private void incluirListaNaResult() {
		alunoList = alunoDao.listaTudo();
		result.include("alunoList", alunoList);
	}
}
