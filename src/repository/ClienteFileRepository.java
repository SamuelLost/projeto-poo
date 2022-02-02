/**
 * Declaração da classe ClienteFileRepository que implementa a interface IClienteRepository 
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

import models.Cliente;
import models.Estudante;

/**
 * Classe responsável por realizar a persistência dos dados no arquivo
 * seguindo as operações da interface.
 */
public class ClienteFileRepository implements IClienteRepository {

    // Constante para o caminho do arquivo
    public static final String FILENAME = "src/database/clientes.txt";

    /**
     * Método que insere uma instância de cliente ou estudante no arquivo
     * clientes.txt
     * 
     * @param cliente Cliente a ser adicionado no arquivo.
     * @return Retorna <code>True<code> caso deu tudo certo inserir no arquivo
     *         e <code>False<code> caso contrário.
     */
    @Override
    public boolean addCliente(Cliente cliente) {
        try (
                FileWriter filmeFile = new FileWriter(FILENAME, true);
                PrintWriter filmeWriter = new PrintWriter(filmeFile);) {
            if (cliente instanceof Estudante) {
                Estudante a = (Estudante) cliente;
                String linha = String.format("%s,%s,%d,%s,%s", cliente.getNome(), cliente.getCpf(), cliente.getIdade(),
                        a.getMatricula(), a.getSiglaFaculdade());
                filmeWriter.println(linha);
                return true;
            } else {
                String linha = String.format("%s,%s,%d", cliente.getNome(), cliente.getCpf(), cliente.getIdade());
                filmeWriter.println(linha);
                return true;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    /**
     * Método que busca todos os registros no arquivo clientes.txt
     * 
     * @return Retorna uma <code>List<Cliente><code> ou null.
     */
    @Override
    public List<Cliente> getAllClientes() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            List<Cliente> clientes = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");
                if (dados.length == 5) {
                    String nome = dados[0];
                    String cpf = dados[1];
                    short idade = Short.parseShort(dados[2]);
                    String matricula = dados[3];
                    String sigla = dados[4];

                    clientes.add(new Estudante(nome, cpf, idade, matricula, sigla));
                } else {
                    String nome = dados[0];
                    String cpf = dados[1];
                    short idade = Short.parseShort(dados[2]);

                    clientes.add(new Cliente(nome, cpf, idade));
                }
                line = br.readLine();
            }
            Collections.sort(clientes);
            return clientes;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Método atualiza as informações de cliente no arquivo clientes.txt
     * 
     * @param cliente Uma instância de Cliente com as novas informações.
     * @return Retorna <code>True<code> caso deu tudo certo atualizar no arquivo
     *         e <code>False<code> caso contrário.
     */
    @Override
    public boolean updateCliente(Cliente cliente) {
        boolean removedFilme = this.removeCliente(cliente.getCpf());

        if (removedFilme) {
            this.addCliente(cliente);
            System.out.println("Cliente atualizado com sucesso!");
            return true;
        }
        return false;
    }

    /**
     * Método que remove um cliente do arquivo clientes.txt
     * 
     * @param cpf Cpf do cliente que deseja remover do arquivo
     * @return Retorna <code>True<code> caso deu tudo certo remover do arquivo
     *         e <code>False<code> caso contrário.
     */
    @Override
    public boolean removeCliente(String cpf) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            List<Cliente> clientes = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");
                if (dados.length < 5) {
                    String nome = dados[0];
                    String cpfAux = dados[1];
                    String idade = dados[2];
                    if (!cpfAux.equals(cpf)) {
                        clientes.add(new Cliente(nome, cpfAux, Short.parseShort(idade)));
                    }
                } else {
                    String nome = dados[0];
                    String cpfAux = dados[1];
                    String idade = dados[2];
                    String matricula = dados[3];
                    String sigla = dados[4];
                    if (!cpfAux.equals(cpf)) {
                        clientes.add(new Estudante(nome, cpfAux, Short.parseShort(idade), matricula, sigla));
                    }
                }

                line = br.readLine();
            }

            File file = new File(FILENAME);

            Boolean deletedFile = file.delete();

            if (deletedFile) {
                for (Cliente cliente : clientes) {
                    this.addCliente(cliente);
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
     * Método que realiza uma busca de um cliente pelo CPF no arquivo clientes.txt
     * 
     * @param cpf Cpf do cliente que deseja buscar no arquivo
     * @return Retorna uma instância de <code>Cliente<code> caso deu tudo certo fazer
     *         a busca no arquivo e <code>null<code> caso contrário.
     */
    @Override
    public Cliente findByCpf(String cpf) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");

                String cpfCliente = dados[1];

                if (cpf.equals(cpfCliente)) {
                    String nome = dados[0];
                    String idade = dados[2];
                    if (dados.length < 5)
                        return new Cliente(nome, cpfCliente, Short.parseShort(idade));
                    else {
                        String matricula = dados[3];
                        String siglaFaculdade = dados[4];
                        return new Estudante(nome, cpf, Short.parseShort(idade), matricula, siglaFaculdade);
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

}
