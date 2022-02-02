package models;

public class Cliente extends Pessoa implements Comparable<Cliente> {
    private String nome;
    private String cpf;
    private short idade;

    /**
     * Construtor para a classe
     * @param nome - nome do cliente
     * @param cpf - cpf do cliente
     * @param idade - idade do cliente
     */
    public Cliente(String nome, String cpf, short idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

    // /**
    //  * GETTERS e SETTERS
    //  */
    // public String getNome() {
    //     return nome;
    // }

    // public void setNome(String nome) {
    //     this.nome = nome;
    // }

    // public String getCpf() {
    //     return cpf;
    // }

    // public void setCpf(String cpf) {
    //     this.cpf = cpf;
    // }

    // public short getIdade() {
    //     return idade;
    // }

    // public void setIdade(short idade) {
    //     this.idade = idade;
    // }

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
     * de objetos Clientes. A comparação e ordenação é feita a partir 
     * de seus nomes usando o próprio compareTo() da classe String.
     *  
     * @param o - objeto cliente
     * @return um <code>integer</code> representando a comparação. 
     * 0 - se for igual;
     * menor que 0 se o nome do objeto for menor lexicograficamente que a passada por argumento;
     * maior que 0 se o nome do objeto for maior lexicograficamente que a passada por argumento.
     */
    @Override
    public int compareTo(Cliente o) {
        return this.nome.compareTo(o.getNome());
    }

}
