package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cinema {
    private String nome;
    //private Sala[] salas; //para dar certo o sort
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
        this.salas.add(sala);

        //Sala[] sala = new Sala[3]
        //sala[0] = new Sala(id, capacidade, null, null)
        //Arrays.sort(sala);
        sc.close();
        return true;
    }

    public boolean adicionaFilme() {
        System.out.print("Nome do Filme: ");
        String nome = sc.nextLine();
        System.out.print("Código: ");
        String codigo_filme = sc.nextLine();
        System.out.print("Gênero: ");
        String genero = sc.nextLine();
        System.out.print("3D ou 2D: ");
        String modalidade = sc.nextLine();
        System.out.print("Dublado ou legendado: ");
        String idioma = sc.nextLine();
        System.out.print("Sinopse: ");
        String sinopse = sc.nextLine();
        System.out.print("Duração do filme: ");
        int duracao = sc.nextInt();
        Filme filme = new Filme(codigo_filme, nome, genero, modalidade, idioma, sinopse, duracao);
        filmes.add(filme);
        return true;
    }

    public boolean inserirFilmeNaSala(int id_sala, int codigo_filme) {
        Sala sala = salas.get(id_sala - 1);
        Filme filme = filmes.get(codigo_filme);
        sala.setFilme(filme);
        return true;
    }

}
