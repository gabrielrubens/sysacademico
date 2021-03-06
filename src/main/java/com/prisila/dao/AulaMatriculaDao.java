package com.prisila.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

import com.prisila.modelo.entidade.AulaMatricula;
import com.prisila.modelo.entidade.Matricula;

@Component
public class AulaMatriculaDao extends Dao<AulaMatricula> {
	
	public AulaMatriculaDao(Session session) {
		super(session);
	}
	
	public List<AulaMatricula> buscarAulas(Matricula matricula){
		return super.buscarTodos(Restrictions.eq("matricula.id", matricula.getId()));
	}
	
	/**
	 * Usa a <u>matricula</u> e <u>aula</u> para carregar o registro de aulaMatricula
	 * @param aulaMatricula
	 * @return
	 */
	public AulaMatricula carrega(AulaMatricula aulaMatricula){
		adicionarCriterion(Restrictions.eq("matricula.id", aulaMatricula.getMatricula().getId()));
		adicionarCriterion(Restrictions.eq("aula.id", aulaMatricula.getAula().getId()));
		
		return super.buscarUm();
	}
	
}
