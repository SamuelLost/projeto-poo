package models;

public class Estudante extends Cliente {

	private String matricula; // Matrícula do estudante
	private String siglaFaculdade; // Sigla da faculdade do estudante

	public Estudante(String nome, String cpf, short idade, String matricula, String siglaFaculdade) {
		super(nome, cpf, idade);
		setMatricula(matricula);
		setSiglaFaculdade(siglaFaculdade);

	}

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

    @Override
    public String toString() {
        String out = super.toString();
        out += "\nMatrícula: " + getMatricula() 
            + "\nFaculdade: " + getSiglaFaculdade();
        return out;
    }
}