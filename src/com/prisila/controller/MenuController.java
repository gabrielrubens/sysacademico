package com.prisila.controller;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.prisila.dao.AlunoDao;
import com.prisila.modelo.entidade.Aluno;

@Resource
public class MenuController extends Controller {
	
	private final AlunoDao alunoDao;
	private final Result result;
	
	public MenuController(AlunoDao alunoDao, Result result) {
		this.alunoDao = alunoDao;
		this.result = result;
	}
	
	@Get
	@Path("/")
	public void inicio() {
		List<Aluno> alunosList = alunoDao.listaAniversariantes();
		result.include("alunosList", alunosList);
	}
	
}
