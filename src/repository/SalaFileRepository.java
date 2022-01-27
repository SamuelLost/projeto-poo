package repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import models.Filme;
import models.Ingresso;
import models.Sala;

public class SalaFileRepository implements ISalaRepository {

    public static final String FILENAME = "src/database/salas.txt";
    public static final String FILENAME_INGRESSO = "src/database/ingresso.txt";

    IFilmeRepository filmeRepository = new FilmeFileRepository();
    FilmeFileRepository filmesRepository = new FilmeFileRepository();

    IClienteRepository clienteRepository = new ClienteFileRepository();
    ClienteFileRepository clientesRepository = new ClienteFileRepository();
    
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

    @Override
    public List<Sala> getAllSalas() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            List<Sala> salas = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");
                int salaId = Integer.parseInt(dados[0]);
                String capacidade = dados[1];
                String filmeCodigo = dados[2];

                if(Integer.parseInt(filmeCodigo) != 0){
                    Filme filme = filmesRepository.findByCodigo(Integer.parseInt(filmeCodigo));
    
                    salas.add(new Sala(salaId, Integer.parseInt(capacidade), filme));
                }else {
                    salas.add(new Sala(salaId, Integer.parseInt(capacidade), null));
                }

                line = br.readLine();
            }

            return salas;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean updateSala(Sala sala) {

        boolean removedSala = this.removeSala(sala.getId());
        
        if(removedSala){
            this.addSala(sala);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeSala(int id) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            List<Sala> salas = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");
                String salaId = dados[0];
                String capacidade = dados[1];
                String filmeCodigo = dados[2];
                
                if(Integer.parseInt(salaId) != id) {
                    
                    if(Integer.parseInt(filmeCodigo) != 0){
                        Filme filme = filmesRepository.findByCodigo(Integer.parseInt(filmeCodigo));
                        salas.add(new Sala(Integer.parseInt(salaId), Integer.parseInt(capacidade), filme));
                    }else{
                        salas.add(new Sala(Integer.parseInt(salaId), Integer.parseInt(capacidade), null));
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
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Sala findById(int id) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");

                String salaId = dados[0];

                if(Integer.parseInt(salaId) == id){
                    String capacidade = dados[1];
                    String filmeCodigo = dados[2];

                    if(Integer.parseInt(filmeCodigo) != 0){
                        Filme filme = filmesRepository.findByCodigo(Integer.parseInt(filmeCodigo));
                        return new Sala(id, Integer.parseInt(capacidade), filme);
                    }else{
                        return new Sala(id, Integer.parseInt(capacidade), null);
                    }
                }

                line = br.readLine();
            }

            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean comprarIngresso(Ingresso ingresso, int numCadeira) {
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
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
