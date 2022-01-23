package repository;

import java.util.List;

import models.Filme;

public interface IFilmeRepository {
    void addFilme(Filme filme);

    List<Filme> getAllFilmes();

    boolean updateFilme(Filme filme);

    boolean removeFilme(int codigo);

    Filme findByCodigo(int codigo);
}
