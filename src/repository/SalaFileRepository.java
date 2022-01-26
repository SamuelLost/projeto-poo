package repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import models.Cliente;
import models.Filme;
import models.Sala;

public class SalaFileRepository implements ISalaRepository{

    public static final String FILENAME = "src/database/salas.txt";

    IFilmeRepository filmeRepository = new FilmeFileRepository();
    FilmeFileRepository filmesRepository = new FilmeFileRepository();

    IClienteRepository clienteRepository = new ClienteFileRepository();
    ClienteFileRepository clientesRepository = new ClienteFileRepository();

    @Override
    public void addSala(Sala sala) {
        try(
                FileWriter filmeFile = new FileWriter(FILENAME, true);
                PrintWriter filmeWriter = new PrintWriter(filmeFile);
        ) {
            String linha = String.format("%d,%d,%d,%s", sala.getId(), sala.getCapacidade(), 0, "0");
            filmeWriter.println(linha);
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
                String clienteIdentifier = dados[3];

                Filme filme = filmesRepository.findByCodigo(Integer.parseInt(filmeCodigo));
                Cliente cliente = clientesRepository.findByCpf(clienteIdentifier);

                // salas.add(new Sala(salaId, Integer.parseInt(capacidade), filme, cliente));
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeSala(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Sala findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
