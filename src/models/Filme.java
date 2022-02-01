package models;

public class Filme implements Comparable<Filme> {
	private int codigo;
	private String nome;
	private String genero;
	private int minIdade;
	private String modalidade;
	private String idioma;
	private String sinopse;
	private int duracao;

	/**
     * Construtor para a classe herdeira
     * @param nome - nome do do filme
     * @param genero - genero do filme
     * @param minIdade - classificação indicativa
	 * @param modalidade - modalidade do filme: 3D ou 2D
	 * @param idioma - legendado ou dublado
	 * @param sinopse - sinopse do filme
	 * @param duracao - duração em minutos do filme
     */
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

	/**
	 * GETTERS e SETTERS
	 */
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

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getIdioma() {
		return idioma;
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

	/**
	 * Imprimindo as informações acerca do
	 */
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

	/**
     * Override do método compareTo() da interface Comparable.
     * Esse método é sobrescrito para que possa ser possível a ordenação
     * de objetos Filmes. A comparação e ordenação é feita a partir 
     * de seus códigos usando o próprio compare() da classe <code>Integer</code>.
     *  
     * @param o - objeto filme
     * @return um <code>integer</code> representando a comparação. 
     * 0 - se for igual;
     * menor que 0 se o código do objeto for menor numericamente que a passada por argumento;
     * maior que 0 se o código do objeto for maior numericamente que a passada por argumento.
     */
	@Override
	public int compareTo(Filme o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.codigo, o.getCodigo());
	}
}
