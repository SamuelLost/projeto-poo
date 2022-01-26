package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cinema {

    private String nome;
    private List<Sala> salas;
    private String cidade;

    Scanner sc = new Scanner(System.in);

    public Cinema(String nome, String cidade) {
        this.nome = nome;
        this.salas = new ArrayList<>();
        this.cidade = cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public boolean inserirFilmeNaSala(int id_sala, int codigo_filme) {
        Sala sala = salas.get(id_sala - 1);
        // Filme filme = filmes.get(codigo_filme);
        // sala.setFilme(filme);
        return true;
    }
}
