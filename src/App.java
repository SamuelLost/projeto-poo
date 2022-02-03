import java.util.Scanner;

import exceptions.ClienteServicesException;
import exceptions.FilmeServicesException;
import exceptions.SalaServicesException;

import models.Cinema;

import repository.AdmValidation;
import repository.ClienteFileRepository;
import repository.EstudanteFileRepository;
import repository.FilmeFileRepository;
import repository.IClienteRepository;
import repository.IEstudanteRepository;
import repository.IFilmeRepository;
import repository.ISalaRepository;
import repository.SalaFileRepository;

import services.ClienteServices;
import services.FilmeServices;
import services.SalaServices;

import utils.Console;

import views.Menu;

public class App {

    private IFilmeRepository filmeRepository;
    private FilmeServices filmeServices;

    private ISalaRepository salaRepository;
    private SalaServices salaServices;

    private IClienteRepository clienteRepository;
    private ClienteServices clienteServices;
    
    private IEstudanteRepository estudanteRepository;


    public App() {

        // Injeção de dependência
        filmeRepository = new FilmeFileRepository();
        filmeServices = new FilmeServices(filmeRepository);
        salaRepository = new SalaFileRepository();

        estudanteRepository = new EstudanteFileRepository();
        clienteRepository = new ClienteFileRepository();
        clienteServices = new ClienteServices(clienteRepository, estudanteRepository);

        salaServices = new SalaServices(
                salaRepository,
                clienteRepository,
                estudanteRepository,
                filmeRepository);
    }
    @SuppressWarnings("resource") // Só para a warning do Scanner nunca fechado sumir
    public String selecionaOperacaoAdmin() throws SalaServicesException, FilmeServicesException {

        Scanner sc = new Scanner(System.in);

        String op = null;
        do {
            op = sc.nextLine(); // Escolhendo a opção do menu
        } while (op.equals("\n") || op.equals("") || op.isEmpty());

        Console.clear();

        switch (op) {
            case "1":
                // Cadastrar sala
                op = "CadastrarSala";
                //Chama o método na classe de serviços da sala
                salaServices.adicionaSala();
                break;
            case "2":
                // Cadastrar filme
                op = "CadastrarFilme";
                //Chama o método na classe de serviços do filme
                filmeServices.addFilme();
                break;
            case "3":
                // Remover sala
                op = "RemoverSala";
                //Chama o método na classe de serviços da sala
                salaServices.removeSala();
                break;
            case "4":
                // Remove filme
                op = "RemoveFilme";
                //Chama o método na classe de serviços do filme
                filmeServices.removeFilme();
                break;
            case "5":
                // Remove cliente
                op = "RemoveCliente";
                //Chama o método na classe de serviços do cliente
                clienteServices.removeCliente();
                break;
            case "6":
                // atualizar dados do filme
                op = "AtualizaFilme";
                //Chama o método na classe de serviços do filme
                filmeServices.updateFilme();
                break;
            case "7":
                // adicionar filme a uma sala
                op = "AdicionaFilmaNaSala";
                //Chama o método na classe de serviços da sala
                salaServices.adicionaFilmeNaSala();
                break;
            case "8":
                // Voltar ao menu principal
                op = "Voltar";
                return op;
            case "0":
                System.out.println("Aplicação finalizada");
                op = "exit"; //Define op como exit
                return op;
            default:
                System.out.println("Opção inválida, digite novamente!");
                return op;
        }

        System.out.println("Aperte Enter para voltar ao menu...");
        sc.nextLine();
        Console.clear();
        return op;

    }

    @SuppressWarnings("resource") // Só para a warning do Scanner nunca fechado sumir
    public String selecionaOperacaoCliente() throws SalaServicesException, ClienteServicesException {
        String op = null;

        Scanner sc = new Scanner(System.in);
        op = sc.nextLine();

        Console.clear();

        switch (op) {
            case "1":
                //Cadastra um cliente 
                op = "CadastraCliente";
                //Chama o método na classe de serviços do cliente
                clienteServices.addCliente();
                break;
            case "2":
                // Comprar ingresso
                op = "ComprarIngresso";
                //Chama o método na classe de serviços da sala
                salaServices.comprarIngresso();
                break;
            case "3":
                // Atualizar dados do cadastro
                op = "AtualizarCadastroCliente";
                //Chama o método na classe de serviços do cliente
                clienteServices.updateCliente();
                break;
            case "4":
                // listar salas
                op = "ListarSalas";
                //Chama o método na classe de serviços da sala
                salaServices.getAllSalas();
                break;
            case "5":
                // listar filme
                op = "ListarFilme";
                //Chama o método na classe de serviços do filme
                filmeServices.getAllFilmes();
                break;
            case "6":
                // Voltar ao Menu principal
                op = "Voltar";
                return op;
            case "0":
                System.out.println("Aplicação finalizada");
                op = "exit"; //Define op como exit
                return op;
            default:
                System.out.println("Opção inválida, digite novamente!");
                return op;
        }

        System.out.println("Digite Enter para retornar ao menu");
        sc.nextLine();
        Console.clear();
        return op;
    }

    @SuppressWarnings("resource") // Só para a warning do Scanner nunca fechado sumir
    public static void main(String[] args) throws Exception {

        boolean isAdmin = false;

        Scanner sc = new Scanner(System.in);

        //Cria o cinema
        Cinema cinema = new Cinema("Cinema Monólitos", "Quixadá-CE");

        //Instancia a aplicação e os objetos contidos na classe
        App application = new App();

        // resultado que sai do do-while
        String result = "exit";

        do {
            // Imprime mensagem de boas-vindas e informações
            System.out.print(cinema);

            // Pergunta se a pessoa é administrador
            System.out.println("É administrador? (S/N)");
            Character ch = sc.nextLine().charAt(0);

            // Se tiver dito que é adm, o CPF é pedido para poder
            // fazer a validação.
            if (String.valueOf(ch).toLowerCase().equals("s")) {
                System.out.print("Digite seu CPF: ");
                String cpf = sc.nextLine();
                // Chama o método para validar o adm
                if (AdmValidation.validation(cpf)) {
                    isAdmin = true;
                } else {
                    System.out.println("Você não é administrador");
                }
            }

            Console.clear();

            try {
                //Se a pessoa for adm, o menu administrativo é mostrado
                if (isAdmin) {
                    Menu.menuAdmin();
                    //Cada operação retorna uma string
                    result = application.selecionaOperacaoAdmin();
                } else {
                    //Como a pessoa não é adm, o menu convencional é mostrado
                    Menu.menuCliente();
                    //Cada operação retorna uma string
                    result = application.selecionaOperacaoCliente();
                }
            } catch (SalaServicesException e) {
                System.out.println(e.getMessage());
            } catch (FilmeServicesException e) {
                System.out.println(e.getMessage());
            } catch (ClienteServicesException e) {
                System.out.println(e.getMessage());
            }
            isAdmin = false;
            //O while só quebra quando as operações retornarem "exit"
            //significando que a aplicação acabou
        } while (!result.equals("exit"));

    }
}
