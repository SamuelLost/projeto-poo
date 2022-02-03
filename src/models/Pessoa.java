package models;

//Classe abstrata para uma pessoa
public abstract class Pessoa implements Comparable<Pessoa> {
    private String nome; // nome da pessoa
    private String cpf; // cpf da pessoa
    private short idade; // idade da pessoa

    public Pessoa(String nome, String cpf, short idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
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
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public short getIdade() {
        return idade;
    }
    public void setIdade(short idade) {
        this.idade = idade;
    }

    /**
     * Imprimindo informações do cliente
     */
    @Override
    public String toString() {
        String out = "==========================\n";
        out += "Nome: " + this.nome + "\n";
        out += "Idade: " + this.idade + "\n";
        out += "CPF: " + this.cpf;
        return out;
    }

    /**
     * Override do método compareTo() da interface Comparable.
     * Esse método é sobrescrito para que possa ser possível a ordenação
     * de objetos Pessoas. A comparação e ordenação é feita a partir 
     * de seus nomes usando o compareToIgnoreCase() da classe String.
     *  
     * @param o - objeto pessoa
     * @return um <code>integer<code> representando a comparação. 
     * 0 - se for igual;
     * menor que 0 se o nome do objeto for menor lexicograficamente que a passada por argumento;
     * maior que 0 se o nome do objeto for maior lexicograficamente que a passada por argumento.
     */
    @Override
    public int compareTo(Pessoa o) {
        return this.getNome().compareToIgnoreCase(o.getNome());
    }
}
