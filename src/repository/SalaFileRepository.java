/**
 * Declaração da classe SalaFileRepository que implementa a interface ISalaRepository 
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
import java.util.Locale;

import models.Filme;
import models.Ingresso;
import models.Sala;
/**
 * Classe responsável por realizar a persistência dos dados no arquivo
 * seguindo as operações da interface.
 */
public class SalaFileRepository implements ISalaRepository {

    // Constante para os caminhos dos arquivos
    public static final String FILENAME = "src/database/salas.txt";
    public static final String FILENAME_INGRESSO = "src/database/ingresso.txt";

    // Repositório de filmes
    IFilmeRepository filmeRepository = new FilmeFileRepository();
    FilmeFileRepository filmesRepository = new FilmeFileRepository();

    // Repositório de clientes
    IClienteRepository clienteRepository = new ClienteFileRepository();
    ClienteFileRepository clientesRepository = new ClienteFileRepository();
    
    /**
     * Método que insere uma instância de Sala no arquivo salas.txt
     * 
     * @param sala Sala a ser adicionado no arquivo.
     * @return Retorna <code>True</code> caso deu tudo certo inserir no arquivo
     *         e <code>False</code> caso contrário.
     */
    @Override
    public boolean addSala(Sala sala) {
        try(
                FileWriter salaFile = new FileWriter(FILENAME, true);
                PrintWriter salaWriter = new PrintWriter(salaFile);
        ) {
            if(sala.getFilme() != null){
                String linha = String.format("%d,%d,%d", sala.getId(), sala.getCapacidade(), sala.getFilme().getCodigo());
                salaWriter.println(linha);
            }
            else{
                String linha = String.format("%d,%d,%d", sala.getId(), sala.getCapacidade(), 0);
                salaWriter.println(linha);
            }
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
    }

    /**
     * Método que busca todos os registros no arquivo salas.txt
     * 
     * @return Retorna uma <code>List<Sala></code> ou null.
     */
    @Override
    public List<Sala> getAllSalas() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            List<Sala> salas = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");
                int salaId = Integer.parseInt(dados[0]);
                // String capacidade = dados[1];
                String filmeCodigo = dados[2];

                if(Integer.parseInt(filmeCodigo) != 0){
                    Filme filme = filmesRepository.findByCodigo(Integer.parseInt(filmeCodigo));
    
                    salas.add(new Sala(salaId, filme));
                }else {
                    salas.add(new Sala(salaId, null));
                }

                line = br.readLine();
            }

            return salas;
        }catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Método atualiza as informações de uma sala no arquivo salas.txt
     * 
     * @param sala Uma instância de Sala com as novas informações.
     * @return Retorna <code>True</code> caso deu tudo certo atualizar no arquivo
     *         e <code>False</code> caso contrário.
     */
    @Override
    public boolean updateSala(Sala sala) {

        boolean removedSala = this.removeSala(sala.getId());
        
        if(removedSala){
            this.addSala(sala);
            return true;
        }
        return false;
    }

    /**
     * Método que remove uma sala do arquivo salas.txt
     * 
     * @param id id da sala que deseja remover do arquivo
     * @return Retorna <code>True</code> caso deu tudo certo remover do arquivo
     *         e <code>False</code> caso contrário.
     */
    @Override
    public boolean removeSala(int id) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            List<Sala> salas = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");
                String salaId = dados[0];
                // String capacidade = dados[1];
                String filmeCodigo = dados[2];
                
                if(Integer.parseInt(salaId) != id) {
                    
                    if(Integer.parseInt(filmeCodigo) != 0){
                        Filme filme = filmesRepository.findByCodigo(Integer.parseInt(filmeCodigo));
                        salas.add(new Sala(Integer.parseInt(salaId), filme));
                    }else{
                        salas.add(new Sala(Integer.parseInt(salaId), null));
                    }

                }
                
                line = br.readLine();
            }

            File file = new File(FILENAME);

            Boolean deletedFile = file.delete();

            if(deletedFile){
                for(Sala sala: salas) this.addSala(sala);
            }else {
                System.out.println("Não foi possível deletar a sala");
                return false;
            }
            return true;
        }catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Método que realiza uma busca de uma sala pelo id no arquivo salas.txt
     * 
     * @param id id da sala que deseja buscar no arquivo
     * @return Retorna uma instância de <code>Sala</code> caso deu tudo certo fazer
     *         a busca no arquivo e <code>null</code> caso contrário.
     */
    @Override
    public Sala findById(int id) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");

                String salaId = dados[0];

                if(Integer.parseInt(salaId) == id){
                    // String capacidade = dados[1];
                    String filmeCodigo = dados[2];

                    if(Integer.parseInt(filmeCodigo) != 0){
                        Filme filme = filmesRepository.findByCodigo(Integer.parseInt(filmeCodigo));
                        return new Sala(id, filme);
                    }else{
                        return new Sala(id, null);
                    }
                }

                line = br.readLine();
            }

            return null;
        }catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Método responsável pela compra de ingressos no cinema
     * 
     * @param ingresso uma instância de Ingresso
     * @param numCadeira cadeira que o cliente deseja reservar
     * @return Retorna <code>True</code> caso deu tudo certo a compra do ingresso 
     *         e salvar no arquivo e <code>False</code> caso contrário.
     */
    @Override
    public Boolean comprarIngresso(Ingresso ingresso, int numCadeira) {

        Locale.setDefault(Locale.US);

        try(
                FileWriter filmeFile = new FileWriter(FILENAME_INGRESSO, true);
                PrintWriter filmeWriter = new PrintWriter(filmeFile);
        ) {
            String linha = String.format("%d,%d,%s,%d,%.2f", ingresso.getCodigo(), ingresso.getSala().getId(), ingresso.getCliente().getCpf(), numCadeira, ingresso.getValor());
            filmeWriter.println(linha);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Método que verifica se essa cadeira já está presente no arquivo ingressos.txt
     * 
     * @param numCadeira número da cadeira que deseja verificar
     * @return Retorna <code>True</code> caso deu tudo certo a verificação
     *         e <code>False</code> caso contrário.
     */
    @Override
    public Boolean findByNumCadeira(int numCadeira) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME_INGRESSO))) {
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");

                String numCadeiraFile = dados[3];

                if(Integer.parseInt(numCadeiraFile) == numCadeira){
                    return true;
                }

                line = br.readLine();
            }

            return false;
        }catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Método que realiza uma busca de todas as cadeiras ocupadas em uma determinada sala
     * 
     * @param salaId id da sala que deseja verifica a disponibilidade
     * @return Retorna <code>List<Integer></code> caso tenha cadeiras ocupadas
     *         e <code>null</code> caso contrário.
     */
    @Override
    public List<Integer> getAllCadeirasOcupadas(int salaId){

        // id_ingresso, id_da_sala, cpf_do_cliente, numero_cadeira, preco_do_ingresso
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME_INGRESSO))) {
            String line = br.readLine();
            List<Integer> cadeirasOcupadas = new ArrayList<>();

            while (line != null) {
                String[] dados = line.split(",");
                String salaIdFile = dados[1];

                if(Integer.parseInt(salaIdFile) == salaId){
                    String numCadeiraFile = dados[3];
                    cadeirasOcupadas.add(Integer.parseInt(numCadeiraFile));
                }

                line = br.readLine();
            }
            
            if(!cadeirasOcupadas.isEmpty()){
                Collections.sort(cadeirasOcupadas);
                return cadeirasOcupadas;
            }else{
                return null;
            }
        }catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
