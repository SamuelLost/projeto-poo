import java.util.Scanner;

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
    public static void main(String[] args) throws Exception {
        Cinema cinema = new Cinema("Cinema Monólitos", "Quixadá-CE");

        // Injeção de dependência
        IFilmeRepository filmeRepository = new FilmeFileRepository();
        FilmeServices filmeServices = new FilmeServices(filmeRepository);
        ISalaRepository salaRepository = new SalaFileRepository();
        
        IEstudanteRepository estudanteRepository = new EstudanteFileRepository();
        IClienteRepository clienteRepository = new ClienteFileRepository();
        ClienteServices clienteServices = new ClienteServices(clienteRepository, estudanteRepository);
        
        SalaServices salaServices = new SalaServices(
            salaRepository,
            clienteRepository,
            estudanteRepository,
            filmeRepository
        );
        
        short op;
        Scanner sc = new Scanner(System.in);
        System.out.print(
                "==========================================================\n"
                        + "Bem-vindo ao " + cinema.getNome() + ", localizado em " + cinema.getCidade() + "\n");

        System.out.println("É administrador? (S/N)");
        Character ch = sc.nextLine().charAt(0);
        boolean isAdmin = false;
        if (ch.equals('S') || ch.equals('s')) {
            System.out.print("Digite seu CPF: ");
            String cpf = sc.nextLine();
            if (cpf.equals("123")) {
                isAdmin = true;
            }
            // aux = verificaCPF(cpf);
        }
        if (isAdmin) {
            Console.clear();

            Menu.menuAdmin();

            op = sc.nextShort();

            do {

                switch (op) {
                    case 1:
                        // Cadastrar sala
                        Console.clear();
                        salaServices.adicionaSala();
                        op = sc.nextShort();
                        break;
                    case 2:
                        // Cadastrar filme
                        filmeServices.addFilme();
                        op = sc.nextShort();
                        break;
                    case 3:
                        // Remover sala
                        Console.clear();
                        salaServices.removeSala();
                        op = sc.nextShort();
                        break;
                    case 4:
                        // Remove filme
                        Console.clear();
                        filmeServices.removeFilme();
                        op = sc.nextShort();
                        break;
                    case 5:
                        // Remove cliente
                        Console.clear();
                        clienteServices.removeCliente();
                        op = sc.nextShort();
                        break;
                    case 6:
                        // atualizar dados do filme
                        Console.clear();
                        filmeServices.updateFilme();    
                        op = sc.nextShort();
                        break;
                    case 7:
                        // adicionar filme a uma sala
                        Console.clear();
                        salaServices.adicionaFilmeNaSala();
                        op = sc.nextShort();
                        break;
                    case 8:
                        // Voltar ao menu principal
                        Console.clear();
                        Menu.menuAdmin();
                        op = sc.nextShort();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida, digite novamente!");
                        op = sc.nextShort();
                        break;
                }
            } while (op != 0);

        } else {
            Console.clear();
            Menu.menuCliente();
            op = sc.nextShort();

            do {

                switch (op) {
                    case 1:
                        Console.clear();
                        clienteServices.addCliente();
                        op = sc.nextShort();
                        break;
                    case 2:
                        // Comprar ingresso
                        Console.clear();
                        salaServices.comprarIngresso();
                        op = sc.nextShort();
                        break;
                    case 3:
                        // Atualizar dados do cadastro
                        Console.clear();
                        // salaServices.comprarIngresso();
                        System.out.println("Falta implementar");
                        op = sc.nextShort();
                        break;
                    case 4:
                        // listar salas
                        Console.clear();
                        salaServices.getAllSalas();
                        op = sc.nextShort();
                        break;
                    case 5:
                        // listar filme
                        Console.clear();
                        filmeServices.getAllFilmes();
                        op = sc.nextShort();
                        break;
                    case 6:
                        // Voltar para o menu
                        Console.clear();
                        Menu.menuCliente();
                        op = sc.nextShort();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida, digite novamente!");
                        op = sc.nextShort();
                        break;
                }
            } while (op != 0);
        }

        sc.close();

    }
}
