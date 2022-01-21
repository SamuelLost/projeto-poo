package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cinema {
    private String nome;
    private List<Sala> salas;
    private List<Filme> filmes;
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

    public boolean adicionaSala() {
        if (salas.size() == 3) {
            return false;
        }

        System.out.println("Digite o id da sala: ");
        int id_sala = sc.nextInt();
        System.out.println("Digite a capacidade da sala: ");
        int capacidade = sc.nextInt();
        Sala sala = new Sala(id_sala, capacidade, null, null);
        this.salas.add(id_sala - 1, sala);
        sc.close();
        return true;
    }

    public boolean adicionaFilme() {
        String codigo_filme = sc.nextLine();
        String nome = sc.nextLine();
        String genero = sc.nextLine();
        String modalidade = sc.nextLine();
        String idioma = sc.nextLine();
        String sinopse = sc.nextLine();
        int duracao = sc.nextInt();
        return true;
    }

    public boolean inserirFilmeNaSala(int id_sala, int codigo_filme) {
        Sala sala = salas.get(id_sala - 1);
        Filme filme = filmes.get(codigo_filme);
        sala.setFilme(filme);
        return true;
    }

}
