/**
 * Declaração da classe FilmeFileRepository que implementa a interface IFilmeRepository 
 * responsável por definir quais operações poderão ser realizadas. 
 */

package repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Filme;

/**
 * Classe responsável por realizar a persistência dos dados no arquivo
 * seguindo as operações da interface.
 */
public class FilmeFileRepository implements IFilmeRepository {

    // Constante para o caminho do arquivo
    private static final String FILENAME = "src/database/filmes.txt";

    /**
     * Método que insere um filme no arquivo.
     * 
     * @param filme Instância de Filme que será inserida no arquivo
     * @return Retorna uma <code>True<String><code> deu tudo certo adicionar no
     *         arquivo e caso contrário,
     *         retorna <code>false<code>
     */
    @Override
    public boolean addFilme(Filme filme) {
        try (
                FileWriter filmeFile = new FileWriter(FILENAME, true);
                PrintWriter filmeWriter = new PrintWriter(filmeFile);) {
            String linha = String.format("%d,%s,%s,%s,%d,%s,%s,%d", filme.getCodigo(), filme.getNome(),
                    filme.getGenero(), filme.getModalidade(), filme.getMinIdade(), filme.getIdioma(),
                    filme.getSinopse(),
                    filme.getDuracao());
            filmeWriter.println(linha);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Método que realiza uma busca de todos os filmes no arquivo.
     * 
     * @return Retorna uma <code>List<Filme><code> se encontrar no
     *         arquivo e caso contrário,
     *         retorna <code>null<code>
     */
    @Override
    public List<Filme> getAllFilmes() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            List<Filme> filmes = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");
                int codigo = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String genero = dados[2];
                String modalidade = dados[3];
                String minIdade = dados[4];
                String idioma = dados[5];
                String sinopse = dados[6];
                String duracao = dados[7];

                filmes.add(new Filme(codigo, nome, genero, Integer.parseInt(minIdade), modalidade, idioma, sinopse,
                        Integer.parseInt(duracao)));
                line = br.readLine();
            }
            if(!filmes.isEmpty()) {
                Collections.sort(filmes);
            }
            return filmes;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Método atualiza as informações de um filme no arquivo filmes.txt
     * 
     * @param filme Uma instância de Filme com as novas informações.
     * @return Retorna <code>True<code> caso deu tudo certo atualizar no arquivo
     *         e <code>False<code> caso contrário.
     */
    @Override
    public boolean updateFilme(Filme filme) {

        boolean removedFilme = this.removeFilme(filme.getCodigo());

        if (removedFilme) {
            this.addFilme(filme);
            System.out.println("Filme atualizado com sucesso!");
            return true;
        }
        return false;
    }

    /**
     * Método que remove um filme do arquivo filmes.txt
     * 
     * @param codigo Cpf do cliente que deseja remover do arquivo
     * @return Retorna <code>True<code> caso deu tudo certo remover do arquivo
     *         e <code>False<code> caso contrário.
     */
    @Override
    public boolean removeFilme(int codigo) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            List<Filme> filmes = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");
                String codigoFilme = dados[0];
                String nome = dados[1];
                String genero = dados[2];
                String modalidade = dados[3];
                String minIdade = dados[4];
                String idioma = dados[5];
                String sinopse = dados[6];
                String duracao = dados[7];

                if (Integer.parseInt(codigoFilme) != codigo) {
                    filmes.add(new Filme(Integer.parseInt(codigoFilme), nome, genero, Integer.parseInt(minIdade),
                            modalidade, idioma, sinopse, Integer.parseInt(duracao)));
                }

                line = br.readLine();
            }

            File file = new File(FILENAME);

            Boolean deletedFile = file.delete();

            if (deletedFile) {
                for (Filme filme : filmes) {
                    this.addFilme(filme);
                }
            } else {
                System.out.println("Não foi possível deletar o filme");
            }
            return true;

        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Método que realiza uma busca de um filme pelo codigo no arquivo filmes.txt
     * 
     * @param codigo codigo do filme que deseja buscar no arquivo
     * @return Retorna uma instância de <code>Filme<code> caso deu tudo certo fazer
     *         a busca no arquivo e <code>null<code> caso contrário.
     */
    @Override
    public Filme findByCodigo(int codigo) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");

                int codigoFilme = Integer.parseInt(dados[0]);

                if (codigo == codigoFilme) {
                    String nome = dados[1];
                    String genero = dados[2];
                    String modalidade = dados[3];
                    String minIdade = dados[4];
                    String idioma = dados[5];
                    String sinopse = dados[6];
                    String duracao = dados[7];

                    return new Filme(codigo, nome, genero, Integer.parseInt(minIdade), modalidade, idioma, sinopse,
                            Integer.parseInt(duracao));
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

}
