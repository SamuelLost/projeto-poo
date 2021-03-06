package views;
//Classe criada para conter apenas os métodos que imprimem os menus
public class Menu {

    /**
     * Método estático que realiza a impressão do menu administrativo
     */
    public static void menuAdmin() {
        System.out.println("======================ADMINISTRATIVO======================");
        System.out.println("1 - Cadastrar Sala");
        System.out.println("2 - Cadastrar Filmes");
        System.out.println("3 - Remover Sala");
        System.out.println("4 - Remover Filme");
        System.out.println("5 - Remover Cliente");
        System.out.println("6 - Atualizar dados do filme");
        System.out.println("7 - Adicionar filme a uma sala");
        System.out.println("8 - Voltar");
        System.out.println("0 - Sair");
    }

    /**
     * Método estático que realiza a impressão do menu administrativo
     */
    public static void menuCliente() {
        System.out.println("==========================================================");
        System.out.println("1 - Realizar Cadastro");
        System.out.println("2 - Comprar ingresso");
        System.out.println("3 - Atualizar dados do cadastro");
        System.out.println("4 - Listar salas");
        System.out.println("5 - Listar filmes");
        System.out.println("6 - Voltar");
        System.err.println("0 - Sair");
    }
}
