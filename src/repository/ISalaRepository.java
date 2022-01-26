package repository;

import java.util.List;

import models.Sala;

public interface ISalaRepository {
    void addSala(Sala sala);

    List<Sala> getAllSalas();

    boolean updateSala(Sala sala);

    boolean removeSala(int id);

    Sala findById(int id);
}
