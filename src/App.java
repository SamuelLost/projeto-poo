import java.util.Scanner;

import exceptions.ClienteServicesException;
import exceptions.FilmeServicesException;
import exceptions.SalaServicesException;
import models.Cinema;
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

    IFilmeRepository filmeRepository;
    FilmeServices filmeServices;
    ISalaRepository salaRepository;

    IEstudanteRepository estudanteRepository;
    IClienteRepository clienteRepository;
    ClienteServices clienteServices;

    SalaServices salaServices;

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
                salaServices.adicionaSala();
                break;
            case "2":
                // Cadastrar filme
                op = "CadastrarFilme";
                filmeServices.addFilme();
                break;
            case "3":
                // Remover sala
                op = "RemoverSala";
                salaServices.removeSala();
                break;
            case "4":
                // Remove filme
                op = "RemoveFilme";
                filmeServices.removeFilme();
                break;
            case "5":
                // Remove cliente
                op = "RemoveCliente";
                clienteServices.removeCliente();
                break;
            case "6":
                // atualizar dados do filme
                op = "AtualizaFilme";
                filmeServices.updateFilme();
                break;
            case "7":
                // adicionar filme a uma sala
                op = "AdicionaFilmaNaSala";
                salaServices.adicionaFilmeNaSala();
                break;
            case "8":
                // Voltar ao menu principal
                Menu.menuAdmin();
                // op = sc.nextShort();
                break;
            case "0":
                System.out.println("Saindo...");
                op = "exit";
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

    public String selecionaOperacaoCliente() throws SalaServicesException, ClienteServicesException {
        String op = null;

        Scanner sc = new Scanner(System.in);
        op = sc.nextLine();
        
        Console.clear();

        switch (op) {
            case "1":
                op = "CadastraCliente";
                clienteServices.addCliente();
                break;
            case "2":
                // Comprar ingresso
                op = "ComprarIngresso";
                salaServices.comprarIngresso();
                // System.out.println("Digite 6 para voltar ao menu");
                break;
            case "3":
                // Atualizar dados do cadastro
                op = "AtualizarCadastroCliente";
                // salaServices.comprarIngresso();
                System.out.println("Falta implementar");
                break;
            case "4":
                // listar salas
                op = "ListarSalas";
                salaServices.getAllSalas();
                break;
            case "5":
                // listar filme
                op = "ListarFilme";
                filmeServices.getAllFilmes();
                break;
            case "6":
                // Voltar para o menu
                Menu.menuCliente();
                break;
            case "0":
                System.out.println("Aplicação finalizada");
                op = "exit";
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

    public static void main(String[] args) throws Exception {

        boolean isAdmin = false;

        Scanner sc = new Scanner(System.in);

        Cinema cinema = new Cinema("Cinema Monólitos", "Quixadá-CE");

        App application = new App();

        String result = "exit";

        do {
            System.out.print(
                    "==========================================================\n"
                            + "Bem-vindo ao " + cinema.getNome() + ", localizado em " + cinema.getCidade() + "\n");
            System.out.println("É administrador? (S/N)");
            Character ch = sc.nextLine().charAt(0);

            if (String.valueOf(ch).toLowerCase().equals("s")) {
                System.out.print("Digite seu CPF: ");
                String cpf = sc.nextLine();
                if (cpf.equals("123")) {
                    isAdmin = true;
                }
            }

            Console.clear();
            
            try {
                if (isAdmin) {
                    Menu.menuAdmin();
                    result = application.selecionaOperacaoAdmin();
                } else {
                    Menu.menuCliente();
                    result = application.selecionaOperacaoCliente();
                }
            } catch (SalaServicesException e) {
                System.out.println(e.getMessage());
            }catch (FilmeServicesException e) {
                System.out.println(e.getMessage());
            }catch (ClienteServicesException e) {
                System.out.println(e.getMessage());
            }

        } while (!result.equals("exit"));

    }
}
