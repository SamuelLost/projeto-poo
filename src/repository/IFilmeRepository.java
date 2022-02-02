package repository;

import java.util.List;

import models.Filme;
/**
 * Interface responsável por definir as operações que serão realizadas
 * na persistência dos dados
 */
public interface IFilmeRepository {

    /**
     * Método que insere um filme no arquivo.
     * 
     * @param filme Instância de Filme que será inserida no arquivo
     * @return Retorna uma <code>True<String><code> deu tudo certo adicionar no
     *         arquivo e caso contrário,
     *         retorna <code>false<code>
     */
    boolean addFilme(Filme filme);

    /**
     * Método que realiza uma busca de todos os filmes no arquivo.
     * 
     * @return Retorna uma <code>List<Filme><code> se encontrar no
     *         arquivo e caso contrário,
     *         retorna <code>null<code>
     */
    List<Filme> getAllFilmes();

    /**
     * Método atualiza as informações de um filme no arquivo filmes.txt
     * 
     * @param filme Uma instância de Filme com as novas informações.
     * @return Retorna <code>True<code> caso deu tudo certo atualizar no arquivo
     *         e <code>False<code> caso contrário.
     */
    boolean updateFilme(Filme filme);

    /**
     * Método que remove um filme do arquivo filmes.txt
     * 
     * @param codigo Cpf do cliente que deseja remover do arquivo
     * @return Retorna <code>True<code> caso deu tudo certo remover do arquivo
     *         e <code>False<code> caso contrário.
     */
    boolean removeFilme(int codigo);

    /**
     * Método que realiza uma busca de um filme pelo codigo no arquivo filmes.txt
     * 
     * @param codigo codigo do filme que deseja buscar no arquivo
     * @return Retorna uma instância de <code>Filme<code> caso deu tudo certo fazer
     *         a busca no arquivo e <code>null<code> caso contrário.
     */
    Filme findByCodigo(int codigo);
}
