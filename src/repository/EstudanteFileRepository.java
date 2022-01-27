package repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import models.Estudante;

public class EstudanteFileRepository implements IEstudanteRepository{
    public static final String FILENAME = "src/database/estudantes.txt"; 

    @Override
    public Estudante findByMatricula(String matricula) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line = br.readLine();
            // nome, cpf,idade, matricula, siglaFaculdade
            while (line != null) {
                String[] dados = line.split(",");

                String matriculaEstudante = dados[3];

                if(matriculaEstudante.equals(matricula)){
                    String nome = dados[0];
                    String cpf = dados[1];
                    String idade = dados[2];
                    String siglaFaculdade = dados[4];
                    return new Estudante(nome, cpf, Short.parseShort(idade), matriculaEstudante, siglaFaculdade);
                }

                line = br.readLine();
            }

            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
