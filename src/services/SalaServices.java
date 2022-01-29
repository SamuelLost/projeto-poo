package services;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// import models.Cinema;
import models.Cliente;
import models.Estudante;
import models.Filme;
import models.Ingresso;
import models.Sala;
import repository.IClienteRepository;
import repository.IEstudanteRepository;
import repository.IFilmeRepository;
import repository.ISalaRepository;

public class SalaServices {

    public static final float VALOR_INGRESSO_MEIA = 10;
    public static final float VALOR_INGRESSO_INTEIRA = 20;

    private ISalaRepository salasRepository;
    private IClienteRepository clientesRepository;
    private IEstudanteRepository estudantesRepository;
    private IFilmeRepository filmesRepository;

    Scanner sc = new Scanner(System.in);

    public SalaServices(
            ISalaRepository salasRepository,
            IClienteRepository clienteRepository,
            IEstudanteRepository estudanteRepository,
            IFilmeRepository filmeRepository) {
        this.salasRepository = salasRepository;
        this.clientesRepository = clienteRepository;
        this.estudantesRepository = estudanteRepository;
        this.filmesRepository = filmeRepository;
    }

    public void adicionaSala() {
        System.out.print("Digite o id da sala: ");
        String salaId = sc.nextLine();

        // Buscar sala pelo id
        Sala salaAlreadyExist = salasRepository.findById(Integer.parseInt(salaId));

        if (salaAlreadyExist != null) {
            System.out.println("A sala com id " + salaId + " já existe.");
            return;
        }

        // System.out.print("Digite a capacidade da sala: ");
        // String capacidade = sc.nextLine();

        Sala sala = new Sala(Integer.parseInt(salaId));
        // cinema.addSalaCinema(sala); //Adicionando em memória
        boolean successCreatedSala = this.salasRepository.addSala(sala);

        if (successCreatedSala) {
            System.out.println("Sala adicionada com sucesso!");
        } else {
            System.out.println("Não foi possível adicionar a sala!");
        }
    }

    public void comprarIngresso() {
        System.out.print("Digite o id do ingresso: ");
        String ingressoId = sc.nextLine();

        List<Sala> salas = this.salasRepository.getAllSalas();
        // List<Sala> salas = cinema.getSalas();
        System.out.println("==============================");
        for (Sala sala : salas) {
            if (sala.getFilme() != null) {
                System.out.println(String.format("Sala %d - %s", sala.getId(), sala.getFilme().getNome()));
            }
        }

        System.out.print("Digite o número da sala: ");
        String salaId = sc.nextLine();

        Sala sala = this.salasRepository.findById(Integer.parseInt(salaId));
        // Sala sala = salas.get(Integer.parseInt(salaId)-1);

        if (sala == null) {
            System.out.println("Sala não encontrada!");
            return;
        }

        System.out.print("Digite o CPF do cliente: ");
        String cpf = sc.nextLine();

        Cliente cliente = this.clientesRepository.findByCpf(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            System.out.print("Digite 6 para voltar ao menu principal: ");
            return;
        }

        // Listar cadeiras ocupadas
        List<Integer> cadeirasOcupadas = this.salasRepository.getAllCadeirasOcupadas(Integer.parseInt(salaId));
        printSala(cadeirasOcupadas);
        // String[][] teste = criaMatriz();
        // for (int i = 0; i < teste.length; i++) {
        // for (int j = 0; j < teste[i].length; j++) {
        // System.out.print(teste[i][j] + " ");
        // }
        // System.out.println();
        // }
        if (cadeirasOcupadas != null) {
            System.out.println("Número das cadeiras ocupadas: " + cadeirasOcupadas);
        } else {
            System.out.println("Pode escolher qualquer cadeira válida de 1 até " + sala.getCapacidade());

        }

        // printSala(sala);

        System.out.print("Digite o número da cadeira: ");
        int cadeira = sc.nextInt();
        sc.nextLine();
        // boolean cadeiraEstaOcupada = sala.adicionarPessoa(cliente, cadeira);
        // printSala(sala);
        // Fazer o método para inserir o cliente na sala
        // Fazer uma buscar pelo numero da cadeira
        boolean cadeiraEstaOcupada = this.salasRepository.findByNumCadeira((cadeira));

        if (cadeiraEstaOcupada) {
            System.out.println("Cadeira ocupada!");
            return;
        }

        System.out.print("Você é estudante? (S/N)");
        String estudanteOp = sc.nextLine();

        float valor = 0;

        if (estudanteOp.toLowerCase().equals("s")) {

            List<String> siglas = this.estudantesRepository.findAllSiglas();

            System.out.println("==================");
            for (String sigla : siglas) {
                System.out.println(sigla);
            }

            System.out.println("Digite a sigla da sua universidade: ");
            String siglaUniversidade = sc.nextLine();

            Estudante isEstudante = this.estudantesRepository.findBySigla(siglaUniversidade.toUpperCase());

            if (isEstudante == null) {
                System.out.println("Você não é estudante!");
                return;
            }

            System.out.println("O valor da seu ingresso é R$10,00");
            valor = VALOR_INGRESSO_MEIA;
        } else {
            System.out.println("O valor da sua ingresso é R$20,00");
            valor = VALOR_INGRESSO_INTEIRA;
        }

        boolean successCreateIngresso = this.salasRepository.comprarIngresso(
                new Ingresso(Integer.parseInt(ingressoId), cliente, sala, valor), cadeira);

        if (successCreateIngresso) {
            System.out.println("Ingresso reservado com sucesso!");
        } else {
            System.out.println("Não foi possível reservar o ingresso!");
        }
    }

    public void printSala(List<Integer> assentosOcup) {
        String[][] cadeiras = criaMatriz();
        //int aux = 0; 
        // Descobrindo a coluna e a linha para colocar o "x"
        for (int aux2 : assentosOcup) {
            if(aux2 < 10) {
                int linha = aux2%10;
                cadeiras[1][linha] = "x";
            } else {
                if(aux2%10 == 0) {
                    int linha = aux2/10;
                    cadeiras[linha][10] = "x";
                } else {
                    int linha = aux2/10;
                    int coluna = aux2%10;
                    cadeiras[linha+1][coluna] = "x";
                }
            } 
        }
        // for (int i = 1; i < cadeiras.length; i++) {
        //     for (int j = 1; j < cadeiras[i].length; j++) {
        //         int a;
        //         if (assentosOcup.size() > aux) {
        //             a = assentosOcup.get(aux);
        //             if (Integer.parseInt(cadeiras[i][j]) == a) {
        //                 cadeiras[i][j] = "x";
        //                 aux++;
        //             }
        //         }
        //         // else {
        //         // aux = 0;
        //         // if (cadeiras[i][j].contains(String.valueOf(aux))) {
        //         // cadeiras[i][j] = "\033[31m x\033[0m";
        //         // aux++;
        //         // }
        //         // }

        //     }
        //     aux = 0;
        // }

        //Imprimindo a matriz
        for (int i = 0; i < cadeiras.length; i++) {
            for (int j = 0; j < cadeiras[i].length; j++) {
                if ((j > 0 && i > 0)) {
                    if (cadeiras[i][j].equals("x")) {
                        System.out.print(" \033[31m" + cadeiras[i][j] + "\033[0m ");
                    } else {
                        if ((Integer.parseInt(cadeiras[i][j]) < 10))
                            if (i > 0 && j > 0)
                                System.out.print(" \033[32m" + cadeiras[i][j] + "\033[0m ");
                            else
                                System.out.print(" " + cadeiras[i][j] + " ");
                        else {
                            System.out.print("\033[32m" + cadeiras[i][j] + "\033[0m ");
                        }
                    }
                } else if (j < 10) { //Para imprimir as bordas-colunas até o 9 com espaço antes
                    System.out.print(" " + cadeiras[i][j] + " ");
                } else { //Para imprimir a bordas-coluna 10 sem espaço antes
                    System.out.print(cadeiras[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    private String[][] criaMatriz() {
        String[][] cadeiras = new String[9][11];
        int aux = 1;
        for (int i = 0; i < cadeiras.length; i++) {
            for (int j = 0; j < cadeiras[0].length; j++) {
                if (j == 0 && i == 0)
                    cadeiras[i][j] = " ";
                else if (i == 0)
                    cadeiras[i][j] = String.valueOf(j);
                else if (j == 0)
                    cadeiras[i][j] = String.valueOf(i);
                else {
                    cadeiras[i][j] = String.format("%d", aux++);
                }
            }
        }
        return cadeiras;
    }

    public void adicionaFilmeNaSala() {
        System.out.print("Digite o id da sala: ");
        String salaId = sc.nextLine();
        List<Filme> filmes = this.filmesRepository.getAllFilmes();

        System.out.println("=================================");
        for (Filme filme : filmes) {
            System.out.println(String.format("%d - %s", filme.getCodigo(), filme.getNome()));
        }
        System.out.print("Digite o codigo do filme: ");
        String filmeCodigo = sc.nextLine();

        Filme filme = filmesRepository.findByCodigo(Integer.parseInt(filmeCodigo));

        if (filme == null) {
            System.out.println("Filme não encontrado!");
            return;
        }

        Sala sala = this.salasRepository.findById(Integer.parseInt(salaId));

        if (sala == null) {
            System.out.println("Sala não encontrada!");
            return;
        }

        sala.setFilme(filme);
        boolean successUpdateSala = this.salasRepository.updateSala(sala);

        // List<Sala> salas = cinema.getSalas();
        // salas.add(Integer.parseInt(salaId)-1, sala); //sei la
        // cinema.setSalas(salas);

        if (successUpdateSala) {
            System.out.println("Filme adicionado com sucesso!");
        } else {
            System.out.println("Não foi possível adicionar o filme!");
        }
    }

    public void getAllSalas() {
        List<Sala> salas = this.salasRepository.getAllSalas();

        Collections.sort(salas);
        // salas.sort(null);

        for (Sala sala : salas) {
            System.out.print(sala);
        }

    }

    public void removeSala() {

        List<Sala> salas = this.salasRepository.getAllSalas();

        System.out.println("=================================");
        for (Sala sala : salas) {
            System.out.print(sala);
        }

        System.out.print("Digite o id da sala: ");
        int salaId = sc.nextInt();

        Boolean removedSala = this.salasRepository.removeSala(salaId);

        if (removedSala) {
            System.out.println("Sala removida com sucesso!");
        } else {
            System.out.println("Não foi possível remover a sala!");
        }
    }

}