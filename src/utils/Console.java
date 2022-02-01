package utils;

/**
 * Classe criada apenas para o método de "limpar" o terminal
 */
public class Console {
    /**
     * O método estático clear utiliza código de escape ANSI para fazer 
     * a limpeza do terminal. Isso é usado para facilitar um pouco
     * a visualização na hora de executar o programa. 
     *  
     * \033[H - move o cursor para o topo esquerdo do terminal
     * \033[2J - limpa do início do cursor até o final da tela, depois
     * retorna o cursor para o início da linha
     *
     * System.out.flush() é usado para limpar o fluxo de saída
     */
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
