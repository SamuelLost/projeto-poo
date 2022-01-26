import java.util.Scanner;

import models.Cinema;
import repository.FilmeFileRepository;
import repository.IFilmeRepository;
import repository.ISalaRepository;
import repository.SalaFileRepository;
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
        SalaServices salaServices = new SalaServices(salaRepository);
        
        short op;
        Scanner sc = new Scanner(System.in);
        System.out.print(
                "==========================================================\n"
                        + "Bem-vindo ao" + cinema.getNome() + ", localizado em " + cinema.getCidade() + "\n");

        System.out.println("É administrador? (S/N)");
        Character ch = sc.nextLine().charAt(0);
        boolean isAdmin = false;
        if (ch.equals('S')) {
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
                        Console.clear();
                        salaServices.adicionaSala();
                        op = sc.nextShort();
                        break;
                    case 2:
                        // cadastrar filme
                        filmeServices.addFilme();
                        op = sc.nextShort();
                        break;
                    case 3:
                        System.out.println("Remover Sala");
                        op = sc.nextShort();
                        break;
                    case 4:
                        // Remove filme
                        Console.clear();
                        filmeServices.removeFilme();
                        op = sc.nextShort();
                        break;
                    case 5:
                        System.out.println("Remover Cliente");
                        op = sc.nextShort();
                        break;
                    case 6:
                        // atualizar dados do filme
                        filmeServices.updateFilme();    
                        op = sc.nextShort();
                        break;
                    case 7:
                        System.out.print("Digite o id da sala: ");
                        int id_sala = sc.nextInt();
                        // cinema.adicionaFilme(id_sala);
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
                    case 5:
                        // listar filme
                        Console.clear();
                        filmeServices.getAllFilmes();
                        op = sc.nextShort();
                        break;
                    default:
                        System.out.println("Opção inválida");
                        op = sc.nextShort();
                        break;
                }
            } while (op != 0);
        }

        sc.close();

    }
}
