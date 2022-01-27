package models;

public abstract class Pessoa /*implements Comparable<Pessoa>*/ {
    private String nome;
    private String cpf;
    private short idade;

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

    public abstract String toString();

    // @Override
    // public int compareTo(Pessoa o) {
    //     // TODO Auto-generated method stub
    //     return this.nome.compareTo(o.getNome());
    // }
}
