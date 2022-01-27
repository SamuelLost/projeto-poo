package repository;

import java.util.List;

import models.Estudante;

public interface IEstudanteRepository {
    public Estudante findBySigla(String sigla);
    public List<String> findAllSiglas();
    public boolean addEstudante(Estudante estudante);
}
