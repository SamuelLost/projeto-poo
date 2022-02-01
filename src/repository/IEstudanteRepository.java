package repository;

import java.util.List;

import models.Estudante;
/**
 * Interface responsável por definir as operações que serão realizadas
 * na persistência dos dados
 */
public interface IEstudanteRepository {

    /**
     * Método que realiza uma buscar de um estudante pela Sigla e que está atrelado
     * a um determinado CPF.
     * 
     * @param sigla Sigla que será utilizada na busca
     * @param cpf   CPF que será atrelado na busca
     * @return Retorna uma instância de <code>Estudante</code> se encontrar no
     *         arquivo e caso contrário,
     *         retorna <code>null<code>
     */
    public Estudante findBySigla(String sigla, String cpf);

    /**
     * Método que realiza uma busca de todas as sigla de universidade que possuem
     * convênio com o cinema.
     * 
     * @return Retorna uma <code>List<String></code> se encontrar no
     *         arquivo e caso contrário,
     *         retorna <code>null<code>
     */
    public List<String> findAllSiglas();

    /**
     * Método que insere um estudante no arquivo.
     * @param estudante Instância de Estudante que será inserida no arquivo
     * @return Retorna uma <code>True<String></code> deu tudo certo adicionar no
     *         arquivo e caso contrário,
     *         retorna <code>false<code>
     */
    public boolean addEstudante(Estudante estudante);
}
