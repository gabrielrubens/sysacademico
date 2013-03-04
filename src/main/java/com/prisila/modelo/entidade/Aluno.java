package com.prisila.modelo.entidade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Past;

@Entity
public class Aluno {
	
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty( message = "{aluno.nome.vazio}" )
	private String nome;
	
	@NotNull( message = "{aluno.data.vazio}")
	@Past( message = "{aluno.data.passado}" )
	private Date dataNascimento;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "aluno_responsavel", joinColumns = { @JoinColumn(name = "aluno_id") }, inverseJoinColumns = @JoinColumn(name = "responsavel_id"))
	private List<Responsavel> listaResponsavel;
	@OneToMany(mappedBy="aluno")
	private List<Matricula> listaMatricula;
	
	public Aluno() {
		this.listaResponsavel = new ArrayList<Responsavel>();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public List<Responsavel> getListaResponsavel() {
		return Collections.unmodifiableList(listaResponsavel);
	}
	
	public void adicionaVinculo(Responsavel responsavel) {
		this.listaResponsavel.add(responsavel);
	}
	
	public void setListaResponsavel(List<Responsavel> listaResponsavel) {
		this.listaResponsavel = listaResponsavel;
	}
	
	public String getDiaAniversario() {
		Calendar data = Calendar.getInstance();
		data.setTime(dataNascimento);
		return String.valueOf(data.get(Calendar.DAY_OF_MONTH));
	}

	public List<Matricula> getListaMatricula() {
		return Collections.unmodifiableList(listaMatricula);
	}

	public void setListaMatricula(List<Matricula> listaMatricula) {
		this.listaMatricula = listaMatricula;
	}
	
}
