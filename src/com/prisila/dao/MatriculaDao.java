package com.prisila.dao;



import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.prisila.modelo.entidade.Matricula;


import br.com.caelum.vraptor.ioc.Component;

@Component
public class MatriculaDao extends Dao<Matricula> {
	
	public MatriculaDao(Session session) {
		super(session);
	}
	
	public MatriculaDao buscarPorAluno(String nomeAluno) {
		adicionaCriteria(getCriteria().createAlias("aluno", "a", Criteria.INNER_JOIN,
				Restrictions.like("a.nome", "%".concat(nomeAluno).concat("%"))));
		return this;
	}
}
