package repository;

import java.util.List;

import models.Cliente;
/**
 * Interface responsável por definir as operações que serão realizadas
 * na persistência dos dados
 */
public interface IClienteRepository {
    /**
     * Método que insere uma instância de cliente ou estudante no arquivo
     * clientes.txt
     * 
     * @param cliente Cliente a ser adicionado no arquivo.
     * @return Retorna <code>True<code> caso deu tudo certo inserir no arquivo
     *         e <code>False<code> caso contrário.
     */
    boolean addCliente(Cliente cliente);

    /**
     * Método que busca todos os registros no arquivo clientes.txt
     * 
     * @return Retorna uma <code>List<Cliente><code> ou null.
     */
    List<Cliente> getAllClientes();

    /**
     * Método atualiza as informações de cliente no arquivo clientes.txt
     * 
     * @param cliente Uma instância de Cliente com as novas informações.
     * @return Retorna <code>True<code> caso deu tudo certo atualizar no arquivo
     *         e <code>False<code> caso contrário.
     */
    boolean updateCliente(Cliente cliente);

    /**
     * Método que remove um cliente do arquivo clientes.txt
     * 
     * @param cpf Cpf do cliente que deseja remover do arquivo
     * @return Retorna <code>True<code> caso deu tudo certo remover do arquivo
     *         e <code>False<code> caso contrário.
     */
    boolean removeCliente(String cpf);

    /**
     * Método que realiza uma busca de um cliente pelo CPF no arquivo clientes.txt
     * 
     * @param cpf Cpf do cliente que deseja buscar no arquivo
     * @return Retorna uma instância de <code>Cliente<code> caso deu tudo certo fazer
     *         a busca no arquivo e <code>null<code> caso contrário.
     */
    Cliente findByCpf(String cpf);
}
