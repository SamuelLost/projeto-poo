package repository;

import models.Estudante;

public interface IEstudanteRepository {
    public Estudante findByMatricula(String matricula);
}
