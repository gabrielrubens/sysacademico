package modelo.constante;

public enum TipoAula {
	PRATICA_INDIVIDUAL("Pr�tica Individual"),
	PRATICA_GRUPO("Pr�tica em Grupo"),
	TEORIA_GRUPO("Teoria em Grupo");
	
	private final String nome;
	
	TipoAula(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return nome;
	}
}
