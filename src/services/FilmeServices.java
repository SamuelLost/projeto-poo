package services;

import java.util.List;
import java.util.Scanner;

import exceptions.FilmeServicesException;
import models.Filme;
import repository.IFilmeRepository;
import utils.Console;

/**
 * Classe de serviços relacionados a entidade de filme
 */
public class FilmeServices {

    Scanner sc = new Scanner(System.in);

    private IFilmeRepository filmesRepository;

    /**
     * Construtor FilmeServices
     * 
     * @param filmesRepository repositório de filmes
     */
    public FilmeServices(IFilmeRepository filmesRepository) {
        this.filmesRepository = filmesRepository;
    }

    /**
     * Método de serviço para adicionar um filme
     * 
     * @see repository.IFilmeRepository#addFilme(Filme)
     *
     */
    public void addFilme() {
        String codigo_filme = "";
        Filme filmeAlreadyExist = null;
        do {
            System.out.print("Código: ");
            codigo_filme = sc.nextLine();

            filmeAlreadyExist = filmesRepository.findByCodigo(Integer.parseInt(codigo_filme));

            if (filmeAlreadyExist != null) {
                System.out.println("Código " + codigo_filme + " já existe, digite novamente");
            }
        } while(filmeAlreadyExist != null);

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
        String duracao = sc.nextLine();

        boolean createdSuccessFilme = this.filmesRepository.addFilme(
                new Filme(Integer.parseInt(codigo_filme), nome, genero, Integer.parseInt(minIdade), modalidade, idioma, sinopse, Integer.parseInt(duracao)));
        
        if(createdSuccessFilme){
            System.out.println("Filme inserido com sucesso!");
        }else {
            System.out.println("Não foi possível inserir o filme!");
        }
        
    }

    /**
     * Método de serviço que obtém do repositório todos os filmes cadastrados
     * 
     * @see repository.IFilmeRepository#getAllFilmes()
     */
    public void getAllFilmes() {
        List<Filme> filmes = this.filmesRepository.getAllFilmes();

        if (filmes == null) {
            System.out.println("Nenhum filme foi encontrado!");
            return;
        } else if(filmes.isEmpty()) {
            System.out.println("Nenhum filme foi encontrado!");
            return;
        }

        // filmes.sort(null);

        for (Filme filme : filmes) {
            System.out.println(filme.toString());
        }
    }

    /**
     * Método de serviço que chama o repositório para excluir o filme da base de
     * dados
     * 
     * @see repository.IFilmeRepository#removeFilme(int)
     */
    public void removeFilme() {
        getAllFilmes();
        System.out.print("Digite o código do filme: ");
        int codigoFilme = sc.nextInt();

        boolean removedFilme = this.filmesRepository.removeFilme(codigoFilme);

        if (removedFilme) {
            System.out.println("Filme removido com sucesso!");
        } else {
            System.out.println("Não foi possível remover o filme!");
        }
    }

    /**
     * Método de serviço para atualização das informações de um filme
     * 
     * @see repository.IFilmeRepository#findByCodigo(int)
     * @see repository.IFilmeRepository#updateFilme(Filme)
     * 
     * @throws FilmeServicesException Exceção no caso de atualizar um filme com um
     *                                código inexistente.
     */
    public void updateFilme() throws FilmeServicesException {

        Filme filme = null;
        boolean updatedSuccessFilme = false;

        this.getAllFilmes();

        System.out.println("Digite o código do filme: ");
        String codigoFilme = sc.nextLine();

        filme = this.filmesRepository.findByCodigo(Integer.parseInt(codigoFilme));

        if (filme == null) {
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

    /**
     * Método utilizado para imprimir uma mensagem de sucesso ou fala de acordo com
     * a informação que foi atualizada do filme
     * 
     * @param updatedSuccessFilme Será <code>True<code> se a atualização foi
     *                            concluída com sucesso
     *                            e caso contrário será <code>False<code>
     * @param field               O campo que foi atualizado
     * @return Retorna uma mensagem de sucesso ou falha formatada
     */
    private String statusUpdatedFilme(Boolean updatedSuccessFilme, String field) {
        if (updatedSuccessFilme) {
            return "O campo " + field + " foi atualizado com sucesso!";
        } else {
            return "Não foi possível atualizar o campo " + field + "!";
        }
    }

    /**
     * Método que realiza o mapeamento da opção escolhida pelo usuário
     * relacionado ao compo que será atualizado
     * 
     * @param op Opção escolhida pelo usuário
     * @return Retorna uma mensagem
     */
    private String mapUpdatedFilmeMessage(int op) {
        if (op == 1) {
            return "Digite o novo nome do filme: ";
        } else if (op == 2) {
            return "Digite o novo gênero do filme: ";
        } else if (op == 3) {
            return "Digite a nova modalidade do filme: ";
        } else if (op == 4) {
            return "Digite a nova classificação indicativa do filme: ";
        } else if (op == 5) {
            return "Digite o novo idioma do filme: ";
        } else if (op == 6) {
            return "Digite a nova sinopse do filme: ";
        } else {
            return "Digite a nova duração do filme: ";
        }
    }
}
