package com.prisila.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

import com.prisila.modelo.entidade.Aluno;
import com.prisila.modelo.entidade.Curso;
import com.prisila.modelo.entidade.Matricula;
import com.prisila.modelo.entidade.Responsavel;

@Component
public class MatriculaDao extends Dao<Matricula> {
	
	private AlunoDao alunoDao;
	
	public MatriculaDao(Session session, AlunoDao alunoDao) {
		super(session);
		this.alunoDao = alunoDao;
	}
	
	public List<Matricula> buscarPorNomeAluno(String nomeAluno) {
		return buscarPorAlunos(alunoDao.buscarPorNome(nomeAluno));
	}
	
	public List<Matricula> buscarPorAlunos(List<Aluno> alunos) {
		if (alunos == null || alunos.isEmpty()) {
			return new ArrayList<Matricula>(0);
		}
		List<Long> ids = new ArrayList<Long>(0);
		for (Aluno aluno : alunos) {
			ids.add(aluno.getId());
		}
		getCriteria().createAlias("aluno", "a", Criteria.INNER_JOIN, Restrictions.in("a.id", ids));
		return buscarTodos();
	}
	
	private MatriculaDao buscarPorAluno(Aluno aluno){
		adicionarCriterion(Restrictions.eq("aluno.id", aluno.getId()));
		return this;
	}
	
	private MatriculaDao buscarPorResponsavel(Responsavel responsavel){
		adicionarCriterion(Restrictions.eq("responsavel.id", responsavel.getId()));
		return this;
	}
	
	private MatriculaDao buscarPorCurso(Curso curso){
		adicionarCriterion(Restrictions.eq("curso.id", curso.getId()));
		return this;
	}
	
	public Matricula buscarPorAlunoResponsavelCurso(Matricula matricula){
		return buscarPorAluno(matricula.getAluno())
				.buscarPorResponsavel(matricula.getResponsavel())
				.buscarPorCurso(matricula.getCurso())
				.buscarUm();
	}
	
	@Override
	public void salvar(Matricula matricula){
		Calendar data = Calendar.getInstance();
		
		matricula.setAtivo(true);
		matricula.setData(data);
		
		super.salvar(matricula);
	}
	
	public void inativar(Long id){
		Matricula matricula = super.carrega(id);
		matricula.setAtivo(false);
		super.atualiza(matricula);
	}
	
	public void ativar(Long id){
		Matricula matricula = super.carrega(id);
		matricula.setAtivo(true);
		super.atualiza(matricula);
	}
}
