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

public class FilmeFileRepository implements IFilmeRepository {

    private static final String FILENAME = "src/database/filmes.txt";

    @Override
    public void addFilme(Filme filme) {
        try(
                FileWriter filmeFile = new FileWriter(FILENAME, true);
                PrintWriter filmeWriter = new PrintWriter(filmeFile);
        ) {
            String linha = String.format("%d,%s,%s,%s,%s,%s,%d", filme.getCodigo(), filme.getNome(),
                    filme.getGenero(), filme.getModalidade(), filme.getIdioma(), filme.getSinopse(),
                    filme.getDuracao());
            filmeWriter.println(linha);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

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
                String idioma = dados[4];
                String sinopse = dados[5];
                String duracao = dados[6];

                filmes.add(new Filme(codigo, nome, genero, modalidade, idioma, sinopse, Integer.parseInt(duracao)));
                line = br.readLine();
            }

            return filmes;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean updateFilme(Filme filme) {
        
        Boolean removedFilme = this.removeFilme(filme.getCodigo());
        
        if(removedFilme){
            this.addFilme(filme);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeFilme(int codigo) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            List<Filme> filmes = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");
                int codigoFilme = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String genero = dados[2];
                String modalidade = dados[3];
                String idioma = dados[4];
                String sinopse = dados[5];
                String duracao = dados[6];

                
                if(codigoFilme != codigo) {
                    filmes.add(new Filme(codigoFilme, nome, genero, modalidade, idioma, sinopse, Integer.parseInt(duracao)));
                }
                
                line = br.readLine();
            }

            File file = new File(FILENAME);

            file.delete();

            for(Filme filme: filmes){
                this.addFilme(filme);
            }

            return true;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Filme findByCodigo(int codigo) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line = br.readLine();

            while (line != null) {
                String[] dados = line.split(",");

                int codigoFilme = Integer.parseInt(dados[0]);

                if(codigo == codigoFilme){
                    String nome = dados[1];
                    String genero = dados[2];
                    String modalidade = dados[3];
                    String idioma = dados[4];
                    String sinopse = dados[5];
                    String duracao = dados[6];

                    return new Filme(codigo, nome, genero, modalidade, idioma, sinopse, Integer.parseInt(duracao));
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
