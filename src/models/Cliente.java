package models;

public class Cliente extends Pessoa implements Comparable<Cliente> {
    private String nome;
    private String cpf;
    private short idade;

    public Cliente(String nome, String cpf, short idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

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

    @Override
    public String toString() {
        String out = "==========================\n";
        out += "Nome: " + this.nome + "\n";
        out += "Idade: " + this.idade + "\n";
        out += "CPF: " + this.cpf;
        return out;
    }

    @Override
    public int compareTo(Cliente o) {
        // TODO Auto-generated method stub
        return this.nome.compareTo(o.getNome());
    }

}
