package models;


public class Cinema {

    private String nome; // Nome para o cinema
    private String cidade; // Cidade que o cinema está localizado

    /**
     * Construtor para a classe
     * @param nome - nome do cinema
     * @param cidade - cidade do cinema
     */
    public Cinema(String nome, String cidade) {
        this.nome = nome;
        this.cidade = cidade;
    }

    /**
     * GETTERS e SETTERS
     */
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Imprimindo informações do cinema
     */
    @Override
    public String toString() {
        return "==========================================================\n"
        + "Bem-vindo ao " + this.getNome() + ", localizado em " + this.getCidade() + "\n";
    }

    
}
