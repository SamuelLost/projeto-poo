package models;

//Classe abstrata para uma pessoa
public abstract class Pessoa {
    private String nome; // nome da pessoa
    private String cpf; // cpf da pessoa
    private short idade; // idade da pessoa

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

    //Método abstrato que vai ser sobrescrito
    public abstract String toString();
}
