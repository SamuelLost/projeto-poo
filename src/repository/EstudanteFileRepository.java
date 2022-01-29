package repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import models.Estudante;

public class EstudanteFileRepository implements IEstudanteRepository{
    public static final String FILENAME = "src/database/clientes.txt"; 
    public static final String FILENAME_SIGLAS = "src/database/siglas.txt"; 

    @Override
    public Estudante findBySigla(String sigla, String cpf) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line = br.readLine();
            // nome, cpf,idade, matricula, siglaFaculdade
            while (line != null) {
                String[] dados = line.split(",");

                if(dados.length == 5){

                    String siglaUniversidade = dados[4];
                    String cpfFile = dados[1];
    
                    if(siglaUniversidade.equals(sigla) && cpfFile.equals(cpf)){
                        String nome = dados[0];
                        String idade = dados[2];
                        String matricula = dados[3];
                        return new Estudante(nome, cpfFile, Short.parseShort(idade), matricula, siglaUniversidade);
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
    public List<String> findAllSiglas() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME_SIGLAS))) {
            List<String> siglas = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                siglas.add(line);
                line = br.readLine();
            }

            return siglas;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean addEstudante(Estudante estudante) {
        try(
                FileWriter estudanteFile = new FileWriter(FILENAME, true);
                PrintWriter estudanteWrite = new PrintWriter(estudanteFile);
        ) {
            String linha = String.format("%s,%s,%d,%s,%s",
                estudante.getNome(), estudante.getCpf(), estudante.getIdade(),
                estudante.getMatricula(), estudante.getSiglaFaculdade()
            );
            estudanteWrite.println(linha);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
}
