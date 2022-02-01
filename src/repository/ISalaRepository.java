package repository;

import java.util.List;

import models.Ingresso;
import models.Sala;
/**
 * Interface responsável por definir as operações que serão realizadas
 * na persistência dos dados
 */
public interface ISalaRepository {

    /**
     * Método que insere uma instância de Sala no arquivo salas.txt
     * 
     * @param sala Sala a ser adicionado no arquivo.
     * @return Retorna <code>True</code> caso deu tudo certo inserir no arquivo
     *         e <code>False</code> caso contrário.
     */
    boolean addSala(Sala sala);

    /**
     * Método que busca todos os registros no arquivo salas.txt
     * 
     * @return Retorna uma <code>List<Sala></code> ou null.
     */
    List<Sala> getAllSalas();

    /**
     * Método atualiza as informações de uma sala no arquivo salas.txt
     * 
     * @param sala Uma instância de Sala com as novas informações.
     * @return Retorna <code>True</code> caso deu tudo certo atualizar no arquivo
     *         e <code>False</code> caso contrário.
     */
    boolean updateSala(Sala sala);

    /**
     * Método que remove uma sala do arquivo salas.txt
     * 
     * @param id id da sala que deseja remover do arquivo
     * @return Retorna <code>True</code> caso deu tudo certo remover do arquivo
     *         e <code>False</code> caso contrário.
     */
    boolean removeSala(int id);

    /**
     * Método que realiza uma busca de uma sala pelo id no arquivo salas.txt
     * 
     * @param id id da sala que deseja buscar no arquivo
     * @return Retorna uma instância de <code>Sala</code> caso deu tudo certo fazer
     *         a busca no arquivo e <code>null</code> caso contrário.
     */
    Sala findById(int id);

    /**
     * Método que verifica se essa cadeira já está presente no arquivo ingressos.txt
     * 
     * @param numCadeira número da cadeira que deseja verificar
     * @return Retorna <code>True</code> caso deu tudo certo a verificação
     *         e <code>False</code> caso contrário.
     */
    Boolean findByNumCadeira(int numCadeira);

    /**
     * Método responsável pela compra de ingressos no cinema
     * 
     * @param ingresso uma instância de Ingresso
     * @param numCadeira cadeira que o cliente deseja reservar
     * @return Retorna <code>True</code> caso deu tudo certo a compra do ingresso 
     *         e salvar no arquivo e <code>False</code> caso contrário.
     */
    Boolean comprarIngresso(Ingresso ingresso, int numCadeira);

    /**
     * Método que realiza uma busca de todas as cadeiras ocupadas em uma determinada sala
     * 
     * @param salaId id da sala que deseja verifica a disponibilidade
     * @return Retorna <code>List<Integer></code> caso tenha cadeiras ocupadas
     *         e <code>null</code> caso contrário.
     */
    List<Integer> getAllCadeirasOcupadas(int salaId);
}
