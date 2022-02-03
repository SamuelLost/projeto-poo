/**
 * Declaração da classe EstudanteFileRepository que implementa a interface IEstudanteRepository 
 * responsável por definir quais operações poderão ser realizadas. 
 */
package repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Estudante;

/**
 * Classe responsável por realizar a persistência dos dados no arquivo
 * seguindo as operações da interface.
 */
public class EstudanteFileRepository implements IEstudanteRepository {

    // Constantes para os caminho dos arquivos
    public static final String FILENAME = "src/database/clientes.txt";
    public static final String FILENAME_SIGLAS = "src/database/siglas.txt";

    /**
     * Método que realiza uma buscar de um estudante pela Sigla e que está atrelado
     * a um determinado CPF.
     * 
     * @param sigla Sigla que será utilizada na busca
     * @param cpf   CPF que será atrelado na busca
     * @return Retorna uma instância de <code>Estudante<code> se encontrar no
     *         arquivo e caso contrário,
     *         retorna <code>null<code>
     */
    @Override
    public Estudante findBySigla(String sigla, String cpf) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line = br.readLine();
            // nome, cpf,idade, matricula, siglaFaculdade
            while (line != null) {
                String[] dados = line.split(",");

                if (dados.length == 5) {

                    String siglaUniversidade = dados[4];
                    String cpfFile = dados[1];

                    if (siglaUniversidade.equals(sigla) && cpfFile.equals(cpf)) {
                        String nome = dados[0];
                        String idade = dados[2];
                        String matricula = dados[3];
                        return new Estudante(nome, cpfFile, Short.parseShort(idade), matricula, siglaUniversidade);
                    }
                }

                line = br.readLine();
            }

            return null;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Método que realiza uma busca de todas as sigla de universidade que possuem
     * convênio com o cinema.
     * 
     * @return Retorna uma <code>List<String><code> se encontrar no
     *         arquivo e caso contrário,
     *         retorna <code>null<code>
     */
    @Override
    public List<String> findAllSiglas() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME_SIGLAS))) {
            List<String> siglas = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                siglas.add(line);
                line = br.readLine();
            }
            if(!siglas.isEmpty()) {
                Collections.sort(siglas);
            }
            return siglas;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Método que insere um estudante no arquivo.
     * @param estudante Instância de Estudante que será inserida no arquivo
     * @return Retorna uma <code>True<String><code> deu tudo certo adicionar no
     *         arquivo e caso contrário,
     *         retorna <code>false<code>
     */
    @Override
    public boolean addEstudante(Estudante estudante) {
        try (
                FileWriter estudanteFile = new FileWriter(FILENAME, true);
                PrintWriter estudanteWrite = new PrintWriter(estudanteFile);) {
            String linha = String.format("%s,%s,%d,%s,%s",
                    estudante.getNome(), estudante.getCpf(), estudante.getIdade(),
                    estudante.getMatricula(), estudante.getSiglaFaculdade());
            estudanteWrite.println(linha);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
