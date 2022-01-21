import java.util.Scanner;

import models.Cinema;
import models.Sala;
import utils.Console;
import views.Menu;

public class App {
    public static void main(String[] args) throws Exception {
        Cinema cinema = new Cinema("Cinema Monólitos", "Quixadá-CE");
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
            if (cpf.equals("123456789")) {
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
                        cinema.adicionaSala();
                        op = sc.nextShort();
                        break;
                    case 2:

                        op = sc.nextShort();
                        break;
                    case 3:
                        System.out.println("Remover Sala");
                        op = sc.nextShort();
                        break;
                    case 4:
                        System.out.println("Remover Filme");
                        op = sc.nextShort();
                        break;
                    case 5:
                        System.out.println("Remover Cliente");
                        op = sc.nextShort();
                        break;
                    case 6:
                        System.out.print("Digite o id da sala: ");
                        int id_sala = sc.nextInt();
                        // cinema.adicionaFilme(id_sala);
                        op = sc.nextShort();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        op = sc.nextShort();
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            } while (op != 0);

        } else {
            Menu.menuCliente();
        }

    }
}
