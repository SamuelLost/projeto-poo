package services;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import exceptions.SalaServicesException;
import models.Cliente;
import models.Estudante;
import models.Filme;
import models.Ingresso;
import models.Sala;
import repository.IClienteRepository;
import repository.IEstudanteRepository;
import repository.IFilmeRepository;
import repository.ISalaRepository;
/**
 * Classe de serviços relacionados a entidade de cliente
 */
public class SalaServices {

    private final float VALOR_INGRESSO_MEIA = 10;
    private final float VALOR_INGRESSO_INTEIRA = 20;

    private ISalaRepository salasRepository;
    private IClienteRepository clientesRepository;
    private IEstudanteRepository estudantesRepository;
    private IFilmeRepository filmesRepository;

    Scanner sc = new Scanner(System.in);

    /**
     * Construtor SalaServices
     * 
     * @param salasRepository Repositório para persistir os dados da sala
     * @param clienteRepository Repositório para persistir os dados do cliente
     * @param estudanteRepository Repositório para persistir os dados do estudante
     * @param filmeRepository Repositório para persistir os dados dos filmes
     */
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

    /**
     * Método responsável pela criação de uma instância de Sala
     * 
     * @see repository.ISalaRepository#addSala(Sala)
     * @see repository.ISalaRepository#findById(int)
     * @throws SalaServicesException Exceção no caso de tentar criar um nova sala com um id já existente
     */
    public void adicionaSala() throws SalaServicesException {
        System.out.print("Digite o id da sala: ");
        String salaId = sc.nextLine();

        // Buscar sala pelo id
        Sala salaAlreadyExist = salasRepository.findById(Integer.parseInt(salaId));

        if (salaAlreadyExist != null) {
            throw new SalaServicesException("A sala com id " + salaId + " já existe.");
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

    /**
     * Método de serviço para a compra de um ingresso
     * 
     * @see repository.ISalaRepository#getAllSalas()
     * @see repository.ISalaRepository#findById(int)
     * @see repository.ISalaRepository#getAllCadeirasOcupadas(int)
     * @see repository.ISalaRepository#findById(int)
     * @see repository.ISalaRepository#comprarIngresso(Ingresso, int)
     * @see repository.IEstudanteRepository#findAllSiglas()
     * @see repository.IEstudanteRepository#findBySigla(String, String)
     * @see repository.IClienteRepository#findByCpf(String)
     * 
     * @throws SalaServicesException 
     *  - Exceção no caso do usuário selecionar uma sala que não existe.
     *  - Exceção no caso do usuário entrar com um CPF que não está na base de dados
     *  - Exceção no caso do usuário tiver com idade inferior determinada pela classificação indicativa do filme.
     *  - Exceção no caso do usuário selecionar uma cadeira com numeração inválida
     *  -   
     */
    public void comprarIngresso() throws SalaServicesException {
        List<Sala> salas = this.salasRepository.getAllSalas();
        // List<Sala> salas = cinema.getSalas();
        System.out.println("==============================");
        for (Sala sala : salas) {
            if (sala.getFilme() != null) {
                System.out.println(String.format("Sala %d - %s - classificação indicativa: %d", sala.getId(), sala.getFilme().getNome(), sala.getFilme().getMinIdade()));
            }
        }

        System.out.print("Digite o número da sala: ");
        String salaId = sc.nextLine();

        Sala sala = this.salasRepository.findById(Integer.parseInt(salaId));
        // Sala sala = salas.get(Integer.parseInt(salaId)-1);

        if (sala == null) {
            throw new SalaServicesException("Sala não encontrada!");
        }

        System.out.print("Digite o CPF do cliente: ");
        String cpf = sc.nextLine();

        Cliente cliente = this.clientesRepository.findByCpf(cpf);

        if (cliente == null) {
            throw new SalaServicesException("Cliente não encontrado!");
        }

        if(cliente.getIdade() < sala.getFilme().getMinIdade()){
            throw new SalaServicesException("O cliente não tem idade suficiente para este filme!");
        }

        // Listar cadeiras ocupadas
        List<Integer> cadeirasOcupadas = this.salasRepository.getAllCadeirasOcupadas(Integer.parseInt(salaId));
        printSala(cadeirasOcupadas);
        
        if (cadeirasOcupadas != null) {
            System.out.println("Número das cadeiras ocupadas: " + cadeirasOcupadas);
        } else {
            System.out.println("Pode escolher qualquer cadeira válida de 1 até " + sala.getCapacidade());
        }

        // printSala(sala);

        // Deve haver um while
        System.out.print("Digite o número da cadeira: ");
        int cadeira = sc.nextInt();
        sc.nextLine();

        if (cadeira > sala.getCapacidade() || cadeira < 1) {
            throw new SalaServicesException("Cadeira inválida!");
        }

        boolean cadeiraEstaOcupada = this.salasRepository.findByNumCadeira((cadeira));

        if (cadeiraEstaOcupada) {
            throw new SalaServicesException("Cadeira ocupada!");
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

            Estudante isEstudante = this.estudantesRepository.findBySigla(siglaUniversidade.toUpperCase(), cliente.getCpf());

            if (isEstudante == null) {
                throw new SalaServicesException("Você não está vinculado a nenhuma universidade que possuí convênio!");
            }
            
            System.out.println("O valor da seu ingresso é R$10,00");
            valor = VALOR_INGRESSO_MEIA;

        } else {
            System.out.println("O valor da sua ingresso é R$20,00");
            valor = VALOR_INGRESSO_INTEIRA;
        }

        int ingressoId = cliente.getCpf().hashCode();
        
        ingressoId = Math.abs(ingressoId);
        
        System.out.println("HASH: " + ingressoId);
        
        boolean successCreateIngresso = this.salasRepository.comprarIngresso(
                new Ingresso((ingressoId), cliente, sala, valor), cadeira);

        if (successCreateIngresso) {
            System.out.println("Ingresso reservado com sucesso!");
        } else {
            System.out.println("Não foi possível reservar o ingresso!");
        }
    }

    /**
     * O método a seguir faz a impressão da matriz de poltronas disponíveis
     * e ocupadas. O método recebe uma matriz de 80 assentos numeradas pelo método criaMatriz().
     * @see #criaMatriz()
     * @param assentosOcup - uma lista de inteiro contendo o número dos assentos ocupados
     *
     * O método percorre a lista de assentos caso ela não seja nula observado as cadeiras
     * para que seja marcado um "x" na cadeira que esteja ocupada dentro da matriz.
     * Temos 3 casos a ser analisado: quando o assento é menor que 10, quando é igual a 10
     * e quando é maior que 10.
     *
     * Primeiro é preciso analisar que na matriz a coluna 1 contém números que terminam com 1,
     * a coluna 2 contém os números que terminam com 2 e assim por diante até a coluna 10 que contém
     * os números que terminam com 0.
     *
     * 1º caso: Assentos menores que 10. Cada assento vai ficar na sua coluna, ou seja, o assento 1
     * na coluna 1, assento 2 na coluna 2 até o assento 9 e coluna 9, todos na primeira linha da 
     * matriz. Para fazer o acesso dessa posição na matriz calculo o resto da divisão por 10
     * que seria o próprio assento com a linha 1 fixa e acesso a posição setando um "x" nela,
     * indicando que está ocupada.
     *
     * 2º caso: Assentos divisíveis por 10. Assentos assim já foi visto que ficarão na última coluna,
     * a décima. Para isso, é preciso quebrar o número em dois pois o primeiro algarismo corresponde
     * a linha que ele se encontra, por exemplo, o assento 10 tá na linha 1, assento 20 na linha 2 até o assento 80,
     * para isso basta fazer uma divisão inteira por 10 e o primeiro algarismo é coletado.
     * Agora basta acessar a posição na matriz com a coluna fixa em 10 e marcar o "x" no assento.
     *
     * 3º caso: Assento maiores que 10 e não divisíveis por 10. Esse é o caso mais especial, mas tem
     * a lógica parecida com os anteriores. Nesse caso temos números do 11 ao 79 salvo os divisíveis
     * por 10. Novamente quebrando o número em 2, temos o primeiro algarismo representando a linha
     * e o segundo representando a coluna. Porém, o primeiro algarismo não representa de fato a linha
     * em que o assento se encontra devido a configuração da nossa matriz (@see #criaMatriz()), ao invés
     * disso, representa uma linha anterior, por isso precisamos pegar a próxima. Pois o número 11 não 
     * está na linha 1, mas sim na 2, assim como o 79 não está na linha 7 e sim na linha 8.
     * A linha é calculada com uma divisão inteira por 10 e a coluna é calculada pelo resto da divisão
     * por 10. Para acessar a posição basta fazer: cadeiras[linha+1][coluna] que já pega a linha correta
     * de acordo com a explicação acima. Acessando a posição, basta marcar como ocupada.
     *
     * Para a impressão, é utilizado 2 for's, a posição que contém "x" está ocupada então imprime o
     * caractere em vermelho, a posição que não contém "x" imprime o número da poltrona em verde,
     * significando que está livre para ser escolhida.
     */
    public void printSala(List<Integer> assentosOcup) {
        String[][] cadeiras = criaMatriz();

        // int aux = 0;
        // Descobrindo a coluna e a linha para colocar o "x"
        if (assentosOcup != null) {
            for (int cadeira : assentosOcup) {
                if (cadeira < 10) {
                    int coluna = cadeira % 10;
                    cadeiras[1][coluna] = "x";
                } else {
                    if (cadeira % 10 == 0) {
                        int linha = cadeira / 10;
                        cadeiras[linha][10] = "x";
                    } else {
                        int linha = cadeira / 10;
                        int coluna = cadeira % 10;
                        cadeiras[linha + 1][coluna] = "x";
                    }
                }
            }
        }

        for (int i = 0; i < cadeiras.length; i++) {
            for (int j = 0; j < cadeiras[i].length; j++) {
                if ((j > 0 && i > 0)) {
                    if (cadeiras[i][j].equals("x")) {
                        //\033[31m - cor vermelha
                        //\033[0m - fim do código ANSI
                        System.out.print(" \033[31m" + cadeiras[i][j] + "\033[0m ");
                    } else {
                        //Se for menor que 10, para imprimir um espaço antes
                        if ((Integer.parseInt(cadeiras[i][j]) < 10))
                            if (i > 0 && j > 0)
                                //\033[31m - cor verde
                                //\033[0m - fim do código ANSI
                                System.out.print(" \033[32m" + cadeiras[i][j] + "\033[0m ");
                            else
                                //Imprimindo um espaço antes para alinhar
                                System.out.print(" " + cadeiras[i][j] + " ");
                        else {
                            System.out.print("\033[32m" + cadeiras[i][j] + "\033[0m ");
                        }
                    }
                } else if (j < 10) { // Para imprimir as bordas-colunas até o 9 com espaço antes
                    System.out.print(" " + cadeiras[i][j] + " ");
                } else { // Para imprimir a bordas-coluna 10 sem espaço antes
                    System.out.print(cadeiras[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Cria uma matriz de 9 linhas e 11 colunas, onde 1 linha serve para ser
     * o guia de número de cada coluna e 1 uma coluna o guia de cada linha.
     * Além disso, a posição [0][0] é um espaço em branco para fica alinhado.
     * Portanto, a cadeiras correspondem apenas a partir da linha 1 e coluna 1,
     * pois a coluna 0 e linha 0 são os guias.
     * 
     * A variável aux é usada para setar a posição das poltrona de 1 até 80
     */
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
    
    /**
     * Método de serviço responsável por realizar as operações necessárias para inserir
     * um filme em uma sala
     * 
     * @see repository.IFilmeRepository#getAllFilmes()
     * @see repository.IFilmeRepository#findByCodigo(int)
     * @see repository.ISalaRepository#findById(int)
     * @see repository.ISalaRepository#updateSala(Sala)
     * 
     * @throws SalaServicesException
     *  - Exceção no caso de inserir um id de um filme inexistente.
     *  - Exceção no caso de inserir um id de uma sala inexistente.
     */
    public void adicionaFilmeNaSala() throws SalaServicesException {
        this.getAllSalas();
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
            throw new SalaServicesException("Filme não encotrado!");
        }

        Sala sala = this.salasRepository.findById(Integer.parseInt(salaId));

        if (sala == null) {
            throw new SalaServicesException("Sala não encontrada!");
        }

        sala.setFilme(filme);
        boolean successUpdateSala = this.salasRepository.updateSala(sala);


        if (successUpdateSala) {
            System.out.println("Filme adicionado com sucesso!");
        } else {
            System.out.println("Não foi possível adicionar o filme!");
        }
    }

    /**
     * Método de serviço responsável pela listagem de salas cadastradas
     * 
     * @see repository.ISalaRepository#getAllSalas()
     */
    public void getAllSalas() {
        List<Sala> salas = this.salasRepository.getAllSalas();

        Collections.sort(salas);
        // salas.sort(null);

        for (Sala sala : salas) {
            System.out.print(sala);
        }

    }
    
    /**
     * Método de serviço responsável pela remoção de salas cadastrada
     * 
     * @see repository.ISalaRepository#getAllSalas()
     * @see repository.ISalaRepository#removeSala(int)
     */
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