package repository;

import java.util.List;

import models.Ingresso;
import models.Sala;

public interface ISalaRepository {
    boolean addSala(Sala sala);

    List<Sala> getAllSalas();

    boolean updateSala(Sala sala);

    boolean removeSala(int id);

    Sala findById(int id);

    Boolean findByNumCadeira(int numCadeira);

    Boolean comprarIngresso(Ingresso ingresso, int numCadeira);

    List<Integer> getAllCadeirasOcupadas(int salaId);
}
