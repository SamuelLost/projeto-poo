package models;

public class Filme {
	private int codigo;
	private String nome;
	private String genero;
	private int minIdade;
	private String modalidade;
	private String idioma;
	private String sinopse;
	private int duracao;

	public Filme(int codigo, String nome, String genero, int minIdade , String modalidade, String idioma, String sinopse,
			int duracao) {
		this.codigo = codigo;
		this.nome = nome;
		this.genero = genero;
		this.minIdade = minIdade;
		this.modalidade = modalidade;
		this.idioma = idioma;
		this.sinopse = sinopse;
		this.duracao = duracao;
	}


	public int getMinIdade() {
		return minIdade;
	}

	public void setMinIdade(int minIdade) {
		this.minIdade = minIdade;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	@Override
	public String toString() {
		String out = "==========================\n";
		out += "Codigo: " + codigo + "\n";
		out += "Nome: " + nome + "\n";
		out += "Genero: " + genero + "\n";
		out += "Idade mínima: " + minIdade + "\n";
		out += "Modalidade: " + modalidade + "\n";
		out += "Idioma: " + idioma + "\n";
		out += "Sinopse: " + sinopse + "\n";
		out += "Duracao: " + duracao + "\n";
		out += "==========================";
		return out;
	}
}
