package models;

/**
 * Classe que representa um Estudante que também é um
 * cliente, por isso Estudante herda de Cliente, pois apresenta
 * apenas 2 atributos diferentes, matrícula e sigla.
 */
public class Estudante extends Cliente {

	private String matricula; // Matrícula do estudante
	private String siglaFaculdade; // Sigla da faculdade do estudante

	/**
     * Construtor para a classe herdeira
     * @param nome - nome do cliente
     * @param cpf - cpf do cliente
     * @param idade - idade do cliente
	 * @param matricula - matrícula do estudante
	 * @param siglaFaculdade - sigla da faculdade
     */
	public Estudante(String nome, String cpf, short idade, String matricula, String siglaFaculdade) {
		super(nome, cpf, idade);
		setMatricula(matricula);
		setSiglaFaculdade(siglaFaculdade);

	}

	/**
	 * GETTERS e SETTERS
	 */
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSiglaFaculdade() {
		return siglaFaculdade;
	}

	public void setSiglaFaculdade(String siglaFaculdade) {
		this.siglaFaculdade = siglaFaculdade;
	}

	/**
	 * Imprimindo as informações dos estudantes, aproveitando
	 * o toString() da super classe
	 */
    @Override
    public String toString() {
        String out = super.toString();
        out += "\nMatrícula: " + getMatricula() 
            + "\nFaculdade: " + getSiglaFaculdade();
        return out;
    }
}