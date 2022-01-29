package services;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import exceptions.FilmeServicesException;
import models.Filme;
import repository.IFilmeRepository;
import utils.Console;

public class FilmeServices {

    Scanner sc = new Scanner(System.in);
    
    private IFilmeRepository filmesRepository;
    
    public FilmeServices(IFilmeRepository filmesRepository) {
        this.filmesRepository = filmesRepository;
    }   

    public boolean addFilme() throws InputMismatchException {
        System.out.print("Código: ");
        String codigo_filme = sc.nextLine();
        System.out.print("Nome do Filme: ");
        String nome = sc.nextLine();
        System.out.print("Gênero: ");
        String genero = sc.nextLine();
        System.out.print("Classificação indicativa: ");
        String minIdade = sc.nextLine();
        System.out.print("3D ou 2D: ");
        String modalidade = sc.nextLine();
        System.out.print("Dublado ou legendado: ");
        String idioma = sc.nextLine();
        System.out.print("Sinopse: ");
        String sinopse = sc.nextLine();
        System.out.print("Duração do filme: ");
        int duracao = sc.nextInt();
        this.filmesRepository.addFilme(
                new Filme(Integer.parseInt(codigo_filme), nome, genero, Integer.parseInt(minIdade) ,modalidade, idioma, sinopse, duracao));
        System.out.println("Filme inserido com sucesso!");
        return true;
    }

    public void getAllFilmes() {
        List<Filme> filmes = this.filmesRepository.getAllFilmes();
        
        if(filmes.isEmpty()){
            System.out.println("Nenhum filme foi encontrado!");
            return;
        }

        //filmes.sort(null);

        for (Filme filme : filmes) {
            System.out.println(filme.toString());
        }
    }

    public void removeFilme() {
        getAllFilmes();
        System.out.print("Digite o código do filme: ");
        int codigoFilme = sc.nextInt();

        boolean removedFilme = this.filmesRepository.removeFilme(codigoFilme);

        if(removedFilme){
            System.out.println("Filme removido com sucesso!");
        } else {
            System.out.println("Não foi possível remover o filme!");
        }
    }

    public void updateFilme() throws FilmeServicesException{

        Filme filme = null;
        boolean updatedSuccessFilme = false;

        this.getAllFilmes();
        
        System.out.println("Digite o código do filme: ");
        String codigoFilme = sc.nextLine();

        filme = this.filmesRepository.findByCodigo(Integer.parseInt(codigoFilme));
        
        if(filme == null){
            throw new FilmeServicesException("Filme não encontrado!");
        }

        Console.clear();
        System.out.println("Qual informação deseja alterar?");
        System.out.println("1 - Nome do filme");
        System.out.println("2 - Gênero do filme");
        System.out.println("3 - Modalidade do filme");
        System.out.println("4 - Classificação indicativa do filme");
        System.out.println("5 - Idioma do filme");
        System.out.println("6 - Sinopse do filme");
        System.out.println("7 - Duração do filme");

        String opcao = sc.nextLine();

        System.out.print(mapUpdatedFilmeMessage(Integer.parseInt(opcao)));
        String valueOp = sc.nextLine();

        switch (Integer.parseInt(opcao)) {
            case 1:
                filme.setNome(valueOp);
                updatedSuccessFilme = this.filmesRepository.updateFilme(filme);
                this.statusUpdatedFilme(updatedSuccessFilme, "Nome");
                break;
            case 2:
                filme.setGenero(valueOp);
                updatedSuccessFilme = this.filmesRepository.updateFilme(filme);
                this.statusUpdatedFilme(updatedSuccessFilme, "Gênero");
                break;
            case 3:
                filme.setModalidade(valueOp);
                updatedSuccessFilme = this.filmesRepository.updateFilme(filme);
                this.statusUpdatedFilme(updatedSuccessFilme, "Modalidade");
                break;
            case 4:
                filme.setMinIdade(Integer.parseInt(valueOp));
                updatedSuccessFilme = this.filmesRepository.updateFilme(filme);
                this.statusUpdatedFilme(updatedSuccessFilme, "Classificação indicativa");
                break;
            case 5:
                filme.setIdioma(valueOp);
                updatedSuccessFilme = this.filmesRepository.updateFilme(filme);
                this.statusUpdatedFilme(updatedSuccessFilme, "Idioma");
                break;
            case 6:
                filme.setSinopse(valueOp);
                updatedSuccessFilme = this.filmesRepository.updateFilme(filme);
                this.statusUpdatedFilme(updatedSuccessFilme, "Sinopse");
                break;
            case 7:
                filme.setDuracao(Integer.parseInt(valueOp));
                updatedSuccessFilme = this.filmesRepository.updateFilme(filme);
                this.statusUpdatedFilme(updatedSuccessFilme, "Duração");
                break;
            default:
                break;
        }
    }

    private String statusUpdatedFilme(Boolean updatedSuccessFilme, String field){
        if(updatedSuccessFilme){
            return "O campo " + field + " foi atualizado com sucesso!";
        } else {
            return "Não foi possível atualizar o campo " + field + "!";
        }
    }

    private String mapUpdatedFilmeMessage(int op){
        if(op == 1){
            return "Digite o novo nome do filme: ";
        }else if(op == 2){
            return "Digite o novo gênero do filme: ";
        }else if(op == 3){
            return "Digite a nova modalidade do filme: ";
        }else if(op == 4){
            return "Digite a nova classificação indicativa do filme: ";
        }else if(op == 5){
            return "Digite o novo idioma do filme: ";
        }else if(op == 6){
            return "Digite a nova sinopse do filme: ";
        }else{
            return "Digite a nova duração do filme: ";
        }
    }
}
