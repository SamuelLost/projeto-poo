package models;

public class Filme {
	private int codigo;
	private String nome;
	private String genero;
	private String modalidade;
	private String idioma;
	private String sinopse;
	private int duracao;

	public Filme(int codigo, String nome, String genero, String modalidade, String idioma, String sinopse,
			int duracao) {
		this.codigo = codigo;
		this.nome = nome;
		this.genero = genero;
		this.modalidade = modalidade;
		this.idioma = idioma;
		this.sinopse = sinopse;
		this.duracao = duracao;
	}

	public Filme(String codigo_filme, String nome2, String genero2, String modalidade2, String idioma2, String sinopse2,
            int duracao2) {
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
		return null;
	}

}
