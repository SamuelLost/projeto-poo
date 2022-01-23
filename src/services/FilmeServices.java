package services;

import java.util.List;
import java.util.Scanner;

import models.Filme;
import repository.IFilmeRepository;
import utils.Console;

public class FilmeServices {

    Scanner sc = new Scanner(System.in);
    
    private IFilmeRepository filmesRepository;
    
    public FilmeServices(IFilmeRepository filmesRepository) {
        this.filmesRepository = filmesRepository;
    }   

    public boolean adicionaFilme(){
        System.out.print("Código: ");
        String codigo_filme = sc.nextLine();
        System.out.print("Nome do Filme: ");
        String nome = sc.nextLine();
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
        this.filmesRepository.addFilme(
                new Filme(Integer.parseInt(codigo_filme), nome, genero, modalidade, idioma, sinopse, duracao));
        return true;
    }

    public void getAllFilmes() {
        List<Filme> filmes = this.filmesRepository.getAllFilmes();

        for (Filme filme : filmes) {
            System.out.println(filme.toString());
        }
    }

    public void removeFilme() {
        System.out.print("Digite o código do filme: ");
        int codigo_filme = sc.nextInt();

        Boolean removedFilme = this.filmesRepository.removeFilme(codigo_filme);

        if(removedFilme){
            System.out.println("Filme removido com sucesso!");
        } else {
            System.out.println("Filme não encontrado!");
        }
    }

    public void updateFilme(){

        Boolean flag = false;

        System.out.println("Digite o código do filme: ");
        int codigo_filme = sc.nextInt();
        Console.clear();
        System.out.println("Qual informação deseja alterar?");
        System.out.println("1 - Nome do filme");
        System.out.println("2 - Gênero do filme");
        System.out.println("3 - Modalidade do filme");
        System.out.println("4 - Idioma do filme");
        System.out.println("5 - Sinopse do filme");
        System.out.println("6 - Duração do filme");

        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                System.out.println("Digite o novo nome do filme: ");
                String nome = sc.nextLine();
                Filme filme = this.filmesRepository.findByCodigo(codigo_filme);

                if(filme != null && nome != null){
                    filme.setNome(nome);
                    Boolean updatedFilme = this.filmesRepository.updateFilme(filme);

                    if(updatedFilme){
                        System.out.println("Filme atualizado com sucesso!");
                    } else {
                        System.out.println("Não foi possível atualizar o filme!");
                    }
                } else {
                    System.out.println("Filme não encontrado!");
                }
                break;
            case 2:
                
                break;
            case 3:
                
                break;
            case 4:
                
                break;
            case 5:
                
                break;
            case 6:
                
                break;
        
            default:
                break;
        }
    }
}
