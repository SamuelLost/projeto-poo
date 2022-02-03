package models;

public class Cliente extends Pessoa {
    /**
     * Construtor para a classe
     * @param nome - nome do cliente
     * @param cpf - cpf do cliente
     * @param idade - idade do cliente
     */
    public Cliente(String nome, String cpf, short idade) {
        super(nome, cpf, idade);
    }
    
}
